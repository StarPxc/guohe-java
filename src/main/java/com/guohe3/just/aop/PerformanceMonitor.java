package com.guohe3.just.aop;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @Package test.springboot.aop
 * @date 2018/6/10  21:55
 * @Description: TODO 性能监控类 超过1秒就会输出
 */
@Slf4j
@Aspect
@Component
public class PerformanceMonitor {
    @Pointcut("execution(public * com.guohe3.just.controller..*(..))")
    private void controllerLayer() {
    }

    @Around("controllerLayer()")
    public Object monitorElapsedTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Timing the method in controller layer
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        // Log the elapsed time
        double elapsedTime = stopWatch.getTime() / 1000;
        Signature signature = proceedingJoinPoint.getSignature();
        String infoString = "[" + signature.toShortString() + "][Elapsed time: " + elapsedTime + " s]";
        if (elapsedTime > 1) {
            log.error(infoString + "[Note that it's time consuming!]");
        } else {
            log.info(infoString);
        }
        return result;
    }
}
