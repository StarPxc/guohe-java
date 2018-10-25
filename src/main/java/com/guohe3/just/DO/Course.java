package com.guohe3.just.DO;

import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/25
 * 课表
 */
@Data
public class Course {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 教师名字
     */
    private String teacherName;
    /**
     * 课程号
     */
    private String courseNum;
    /**
     * 课程名字
     */
    private String courseName;
    /**
     * 这门课要上的周次
     */
    private String weeks;
    /**
     * 星期几的课
     */
    private Integer weekNum;
    /**
     * 学年
     */
    private String semester;




}
