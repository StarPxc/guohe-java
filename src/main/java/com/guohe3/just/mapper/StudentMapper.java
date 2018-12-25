package com.guohe3.just.mapper;

import com.guohe3.just.DO.Student;
import com.guohe3.just.DO.User;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    User findUser(String username, String password);
}