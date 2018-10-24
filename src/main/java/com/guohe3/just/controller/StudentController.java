package com.guohe3.just.controller;

import com.guohe3.just.common.resp.ApiResult;
import com.guohe3.just.common.utils.RestUtil;
import com.guohe3.just.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("getScoreAll")
    public ApiResult getScoreAll(@RequestParam String username,@RequestParam String password) throws IOException {
        return RestUtil.success("查询成功",studentService.getScoreAll(username,password));
    }

}
