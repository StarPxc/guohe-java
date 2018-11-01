package com.guohe3.just.service.impl;

import com.guohe3.just.DO.Student;
import com.guohe3.just.DO.User;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.execption.CustomException;
import com.guohe3.just.mapper.StudentMapper;
import com.guohe3.just.mapper.UserMapper;
import com.guohe3.just.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guohe3.just.vo.StudentVO;


/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @date 2018/6/10  12:15
 */
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public User findUser(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username,password);
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User findUserByUsername(String s) {
        return userMapper.selectUserByUsername(s);
    }

    @Override
    public StudentVO findUserByUsernameDetail(String s) {
        User user=userMapper.selectUserByUsername(s);
        if (user==null){
            throw new CustomException(ResultEnum.NO_USER);
        }
        StudentVO studentVO=new StudentVO();

        Student student=studentMapper.selectByPrimaryKey(user.getDetailInfoId());
        if (student==null){
            throw new CustomException(ResultEnum.NO_USER);
        }
        BeanUtils.copyProperties(user,studentVO);
        BeanUtils.copyProperties(student,studentVO);


        return studentVO;
    }
}
