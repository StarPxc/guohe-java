package com.guohe3.just.controller;

import com.guohe3.just.common.resp.ApiResult;
import com.guohe3.just.common.utils.RestUtil;
import com.guohe3.just.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author 浦希成
 * 2018/10/24
 */
@Api(value = "学生接口", description = "学生接口")
@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("getScoreAll")
    @ApiOperation(value = "获取用户所有成绩列表")
    public ApiResult getScoreAll(@RequestParam String username, @RequestParam String password) throws IOException {
        return RestUtil.success("查询成功", studentService.getScoreAll(username, password));
    }
    @ApiOperation(value = "获取课表")
    @PostMapping("getSchoolTimetable")
    public ApiResult getSchoolTimetable(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String semester) throws IOException {
        if (semester == null) {
            semester = "2017-2018-2";
        }
        return RestUtil.success("查询成功", studentService.getSchoolTimetable(username, password, semester));
    }
    @ApiOperation(value = "获取绩点")
    @PostMapping("getJidian")
    public ApiResult getJidian(@RequestParam String username, @RequestParam String password) throws IOException {
        return RestUtil.success("查询成功", studentService.getJidian(username, password));
    }

    @ApiOperation(value = "获取用户信息，暂时命名为login")
    @PostMapping("login")
    public ApiResult getStudentInfo(@RequestParam String username, @RequestParam String password) throws IOException {
        return RestUtil.success("查询成功", studentService.login(username, password));
    }


}
