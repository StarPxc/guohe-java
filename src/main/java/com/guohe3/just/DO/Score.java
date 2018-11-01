package com.guohe3.just.DO;

import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/24
 */
@Data
public class Score {


    /**
     * alternative_course_name :
     * alternative_course_number :
     * course_attribute : 必修
     * course_name : 电工电子技术
     * course_nature : 其他
     * course_num : 03040011b
     * credit : 3
     * examination_method : 考查
     * mark_of_score :
     * order_num : 1
     * score : 98
     * start_semester : 2015-2016-1
     * total_hours : 48
     */

    private String alternativeCourseName;
    private String alternativeCourseNumber;
    private String courseAttribute;
    private String courseName;
    private String courseNature;
    private String courseNum;
    private String credit;
    private String examinationMethod;
    private String markOfScore;
    private String orderNum;
    private String score;
    private String startSemester;
    private String totalHours;
    private Integer studentId;



}
