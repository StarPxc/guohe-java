package com.guohe3.just.service;


import com.guohe3.just.DO.User;
import com.guohe3.just.vo.StudentVO;

import java.io.IOException;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @Package test.springboot.user.service
 * @date 2018/6/10  12:10
 * @Description: TODO
 */
public interface UserService {


    User findUser(String username, String password);

    void addUser(User user);

    StudentVO findUserByUsernameDetail(String s);

    User findUserByUsername(String s);
}
