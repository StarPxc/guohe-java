package com.guohe3.just.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guohe3.just.DO.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author pxc
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 根据用户名和密码查找学生
     *
     * @param username 用户名
     * @param password 密码
     * @return Student
     */
    @Select("select * from student where username=#{username} and password = #{password}")
    Student selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}