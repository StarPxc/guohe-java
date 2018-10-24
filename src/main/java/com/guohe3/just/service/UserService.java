package com.guohe3.just.service;


import com.guohe3.just.DO.User;
import com.guohe3.just.vo.QueryCondition;
import com.guohe3.just.vo.UserVO;

import java.io.IOException;
import java.util.List;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @Package test.springboot.user.service
 * @date 2018/6/10  12:10
 * @Description: TODO
 */
public interface UserService {


    UserVO loginViaVpn(String username, String password) throws IOException;

    UserVO loginViaNormal(String username, String password) throws IOException;


}
