package com.guohe3.just.vo;


import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/31
 */
@Data
public class StudentVO {

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */

    private String nickname;

    /**
     * qq的用户标识
     */

    private String openIdQq;
    /**
     * 微信的用户标识
     */

    private String openIdWx;
    /**
     * qq头像
     */

    private String headPicQq;
    /**
     * 微信头像
     */

    private String headPicWx;
    /**
     * 角色
     */

    private String role;
    /**
     * 权限
     */

    private String authority;

    /**
     * 手机号
     */

    private String phone;
    /**
     * 登录来源
     */

    private String origin;
    /**
     * 籍贯
     */

    private String nativePlace;
    /**
     * 最后一次登录时间
     */

    private String lastLogin;


    /**
     * 姓名
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
     * 班级号
     */

    private String classNum;
    /**
     * 组织
     */

    private String organization;
    /**
     * 出生日期
     */

    private String birthday;

}
