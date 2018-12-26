package com.guohe3.just.security.filter;

import com.guohe3.just.DO.Student;
import com.guohe3.just.DO.User;
import com.guohe3.just.common.constants.Constants;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.utils.RestUtil;
import com.guohe3.just.craw.CrawService;
import com.guohe3.just.service.StudentService;
import com.guohe3.just.service.UserService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author 浦希成
 * 2018/10/28
 * 判断用户是否存在，如果不存在先去教务系统模拟登陆
 */
@Slf4j
public class LoginJUSTFilter extends GenericFilterBean {

    private UserService userService;
    private CrawService crawService;
    private StudentService studentService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public LoginJUSTFilter(UserService userService, CrawService crawService, StudentService studentService) {
        this.userService = userService;
        this.crawService = crawService;
        this.studentService = studentService;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest r = (HttpServletRequest) request;


        if (!"/loginProcess".equalsIgnoreCase(r.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            String username = r.getParameter("username");
            String password = r.getParameter("password");
            User user = userService.findUser(username, password);
            //用户已经存在直接执行放过
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                log.info("{} 第一次登录", username);
                OkHttpClient client = crawService.login(username, password);
                //登录教务处失败
                if (client == null) {
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(objectMapper.writeValueAsString(
                            RestUtil.error(ResultEnum.JWC_ACCOUNT_ERROR.getCode(),
                                    ResultEnum.JWC_ACCOUNT_ERROR.getMsg())));
                } else {

                    //从教务处获取学生信息
                    Student student = crawService.getStudentInfo(crawService.getScoreHtml(client, Constants.STUDENT_INFO));
                    student.setUsername(username);
                    student.setPassword(password);
                    Integer id = studentService.addStudent(student);
                    if (id != null) {
                        user = new User();
                        user.setPassword(password);
                        user.setUsername(username);
                        user.setLastLogin(LocalDateTime.now().toString());
                        user.setDetailInfoId(id);
                        userService.addUser(user);
                        chain.doFilter(request, response);
                    } else {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write(objectMapper.writeValueAsString(
                                RestUtil.error(ResultEnum.UNKONW_ERROR.getCode(),
                                        ResultEnum.UNKONW_ERROR.getMsg())));
                    }


                }
            }
        }

    }


}
