package com.xiao.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao.mapper.PersonalCenterMapper;
import com.xiao.pojo.PersonalData;
import com.xiao.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {

    @Resource
    private PersonalCenterMapper personalCenterMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public PersonalData personalData(String ticket) throws IOException {

        String userJson= (String) redisTemplate.opsForValue().get(ticket);
        if(!StringUtils.isEmpty(userJson)){
            try {
                User user=objectMapper.readValue(userJson, User.class);
                PersonalData personalData=personalCenterMapper.personalData(user);
                return personalData;
            } catch (Exception e) {
                throw e;
            }
        }

       return null;
    }

    @Override
    public void personalDataUpdate(PersonalData personalData) {
        try {

            personalCenterMapper.personalDataUpdate(personalData);
        } catch (Exception e) {
            throw e;
        }
    }
}
