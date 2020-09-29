package com.xiao.mapper;

import com.xiao.pojo.PersonalData;
import com.xiao.pojo.User;

public interface PersonalCenterMapper {

    PersonalData personalData(User user);

    void personalDataUpdate(PersonalData personalData);
}
