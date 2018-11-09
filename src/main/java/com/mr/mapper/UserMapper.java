package com.mr.mapper;

import com.mr.model.TMallUserAccount;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Wy on 2018/11/5.
 */
public interface UserMapper {
    TMallUserAccount selectUserByNamePs(@Param("userName") String userName,
                                        @Param("passWord") String passWord);
}
