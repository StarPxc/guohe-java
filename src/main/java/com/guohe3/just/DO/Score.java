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

    private String alternative_course_name;
    private String alternative_course_number;
    private String course_attribute;
    private String course_name;
    private String course_nature;
    private String course_num;
    private String credit;
    private String examination_method;
    private String mark_of_score;
    private String order_num;
    private String score;
    private String start_semester;
    private String total_hours;

    @Override
    public String toString() {
        return "Score{" +
                "alternative_course_name='" + alternative_course_name + '\'' +
                ", alternative_course_number='" + alternative_course_number + '\'' +
                ", course_attribute='" + course_attribute + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_nature='" + course_nature + '\'' +
                ", course_num='" + course_num + '\'' +
                ", credit='" + credit + '\'' +
                ", examination_method='" + examination_method + '\'' +
                ", mark_of_score='" + mark_of_score + '\'' +
                ", order_num='" + order_num + '\'' +
                ", score='" + score + '\'' +
                ", start_semester='" + start_semester + '\'' +
                ", total_hours='" + total_hours + '\'' +
                '}';
    }

}
