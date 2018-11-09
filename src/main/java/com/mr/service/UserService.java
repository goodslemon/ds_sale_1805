package com.mr.service;

import com.mr.model.TMallUserAccount;

/**
 * Created by Wy on 2018/11/5.
 */
public interface UserService {
    TMallUserAccount selectUserByNamePs(String userName, String passWord);
}
