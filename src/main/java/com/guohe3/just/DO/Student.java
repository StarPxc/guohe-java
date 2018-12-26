package com.guohe3.just.DO;

import lombok.Data;


@Data
public class Student {
    /**
     * 自增id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名（教务系统上的）
     */
    private String name;

    /**
     * 专业
     */
    private String major;

    /**
     * 学院
     */
    private String academy;

    /**
     * 班级
     */
    private String classNum;

    /**
     * 组织
     */
    private String organization;

    /**
     * 生日
     */
    private String birthday;


}