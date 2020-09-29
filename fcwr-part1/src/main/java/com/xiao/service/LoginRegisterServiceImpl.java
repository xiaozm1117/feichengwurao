
package com.xiao.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao.checknameException;
import com.xiao.mapper.LoginRegisterMapper;
import com.xiao.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Resource
    private LoginRegisterMapper loginRegisterMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void zhuce(User user) {

       try {
           user.setPassword(DigestUtils.md5Hex(user.getPassword()));
           loginRegisterMapper.zhuce(user);
           loginRegisterMapper.initPersonalData(user);
       }catch (Exception e){
           throw e;

       }

    }

    @Override
    public void checkname(String name) {
        try {
            int a=loginRegisterMapper.checkname(name);
            if(a==0){
                throw new checknameException();
            }

        }catch (Exception e){
            throw e;

        }
    }

    @Override
    public String denglu(User user) {
        String token="";
        String password=user.getPassword();
        user.setPassword(DigestUtils.md5Hex(password));
        List<User> userList=loginRegisterMapper.denglu(user);

        if(userList==null || userList.isEmpty()) throw new RuntimeException();

        user=userList.get(0);

        try {
            String userJSON = objectMapper.writeValueAsString(user);
            //md5（“JT_TICKET_” + System.currentTime + username）
            token = DigestUtils.md5Hex("TICTOT_"+System.currentTimeMillis() + user.getName());
            redisTemplate.opsForValue().set(token,userJSON);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return token;
    }


}

