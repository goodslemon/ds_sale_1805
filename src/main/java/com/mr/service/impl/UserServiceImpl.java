package com.mr.service.impl;

import com.mr.mapper.UserMapper;
import com.mr.model.TMallUserAccount;
import com.mr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Wy on 2018/11/5.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public TMallUserAccount selectUserByNamePs(String userName, String passWord) {
        return userMapper.selectUserByNamePs(userName,passWord);
    }
}
