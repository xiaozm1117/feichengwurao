package com.xiao.service;

import com.xiao.pojo.User;

import java.io.IOException;

public interface LoginRegisterService {
    public void zhuce(User user);
    void checkname(String name);

    String denglu(User user);


}
