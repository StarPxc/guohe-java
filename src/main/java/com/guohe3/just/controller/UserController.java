package com.guohe3.just.controller;

import com.guohe3.just.DO.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.guohe3.just.common.resp.ApiResult;
import com.guohe3.just.common.utils.RestUtil;
import com.guohe3.just.service.UserService;
import com.guohe3.just.vo.QueryCondition;

import java.io.IOException;
import java.util.List;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @date 2018/6/10  12:16

 */
@RestController
@RequestMapping("/user")
@Api(value = "用户接口", description = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("vpn")
    public ApiResult loginViaVpn(@RequestParam String username,@RequestParam String password) throws IOException {
        return RestUtil.success("查询成功",userService.loginViaVpn(username,password));
    }
    @PostMapping("normal")
    public ApiResult loginViaNormal(@RequestParam String username,@RequestParam String password) throws IOException {
        return RestUtil.success("查询成功",userService.loginViaNormal(username,password));
    }
}
