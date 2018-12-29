package com.guohe3.just.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author pxc
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
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