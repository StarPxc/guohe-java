package com.guohe3.just.service.impl;

import com.github.pagehelper.PageHelper;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.execption.CustomException;
import com.guohe3.just.craw.CrawService;
import com.guohe3.just.service.UserService;
import com.guohe3.just.DO.User;
import com.guohe3.just.mapper.UserMapper;
import com.guohe3.just.vo.QueryCondition;
import com.guohe3.just.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @date 2018/6/10  12:15
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private CrawService crawService;


    @Override
    public UserVO loginViaVpn(String username, String password) throws IOException {
        crawService.justLoginVpn(username,password);
        return null;
    }

    @Override
    public UserVO loginViaNormal(String username, String password) throws IOException {
        crawService.justLoginNormal(username,password);
        return null;
    }
}
