package com.guohe3.just.DO;

import lombok.Data;


@Data
public class User {
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
     * 昵称
     */

    private String nickname;
    /**
     * 出生日期
     */

    private String birthday;
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
     * 详情记录id
     */

    private Integer detailInfoId;
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





}