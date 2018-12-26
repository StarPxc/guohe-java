package com.guohe3.just.security.handler;

import com.guohe3.just.common.utils.RestUtil;
import com.guohe3.just.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 浦希成
 * 2018/10/19
 */
@Component("GuoHeAuthenticationSuccessHandler")
//public class ImoocAuthenticationSuccessHandler implements AuthenticationSuccessHandler  自定义一般实现这个接口，也可以去继承默认的处理器，如下

public class GuoHeAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    String type = "json";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        //如果是json格式的
        if ("json".equals(type)) {

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(RestUtil.success("登录成功", userService.findUserByUsernameDetail(request.getParameter("username")))));
        } else {
            //不是json进行页面的跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
