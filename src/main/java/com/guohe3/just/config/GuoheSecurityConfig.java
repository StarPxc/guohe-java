package com.guohe3.just.config;

import com.guohe3.just.craw.CrawService;
import com.guohe3.just.security.filter.LoginJUSTFilter;
import com.guohe3.just.service.StudentService;
import com.guohe3.just.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * @author 浦希成
 * 2018/10/28
 */
@EnableWebSecurity
public class GuoheSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler guoHeAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler guoHeAuthenticationFailureHandler;
    @Autowired
    private UserService userService;
    @Autowired
    private CrawService crawService;
    @Autowired
    private StudentService studentService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new LoginJUSTFilter(userService,crawService,studentService),UsernamePasswordAuthenticationFilter.class)

                .csrf().disable()
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/loginProcess")
                .successHandler(guoHeAuthenticationSuccessHandler)
                .failureHandler(guoHeAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/loginProcess","/login.html")
                .permitAll()
                .anyRequest()
                .authenticated();

    }





}
