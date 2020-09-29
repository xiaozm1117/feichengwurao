package com.xiao.service;

import com.xiao.pojo.PersonalData;

import java.io.IOException;

public interface PersonalCenterService {


    PersonalData personalData(String ticket) throws IOException;

    void personalDataUpdate(PersonalData personalData);
}
