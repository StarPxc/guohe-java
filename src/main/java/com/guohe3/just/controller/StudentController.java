package com.guohe3.just.controller;

import com.guohe3.just.common.resp.ApiResult;
import com.guohe3.just.common.utils.RestUtil;
import com.guohe3.just.security.entity.GuoHeUserDetailsImpl;
import com.guohe3.just.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author 浦希成
 * 2018/10/24
 */
@Api(value = "学生接口", description = "学生接口")
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("getScoreAll")
    public ApiResult getScoreAll(Authentication authentication) throws IOException {
        GuoHeUserDetailsImpl userDetails= (GuoHeUserDetailsImpl) authentication.getPrincipal();
        return RestUtil.success("查询成功",studentService.getScoreAll(userDetails.getUsername(),userDetails.getPassword()));
    }

}
