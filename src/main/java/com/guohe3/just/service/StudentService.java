package com.guohe3.just.service;

import com.guohe3.just.DO.Course;
import com.guohe3.just.DO.Score;
import com.guohe3.just.DO.Student;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 浦希成
 * 2018/10/24
 */
public interface StudentService {
    /**
     * 获取成绩
     * @param username 用户名
     * @param password 密码
     * @return list
     * @throws IOException
     */
    List<Score> getScoreAll(String username, String password) throws IOException;

    /**
     * 添加学生
     * @param student Student
     * @return Integer
     */
    Integer addStudent(Student student);

    /**
     * 获取学生信息
     * @param username 用户名
     * @param password 密码
     * @return Student
     * @throws IOException
     */
    Student getStudentInfo(String username,String password) throws IOException;

    /**
     * 获取课表
     * @param username 用户名
     * @param password 密码
     * @param semester 学期
     * @return list
     * @throws IOException
     */
    List<Map<String, String>> getSchoolTimetable(String username, String password, String semester) throws IOException;

    /**
     * 获取绩点
     * @param username 用户名
     * @param password 密码
     * @return  list
     */
    List<Map<String, String>>  getJidian(String username, String password) throws IOException;
}
