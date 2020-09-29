package com.xiao.mapper;

import com.xiao.pojo.User;

import java.util.List;

public interface LoginRegisterMapper {

    int zhuce(User user);

    int checkname(String name);

    List<User> denglu(User user);

    void initPersonalData(User user);

}
