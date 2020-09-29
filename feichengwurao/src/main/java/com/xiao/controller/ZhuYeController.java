package com.xiao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao.Result;
import com.xiao.feign.ZhuYeFeign;
import com.xiao.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ZhuYeController {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/zhuYe")
    public String zhuYe(){

        return "zhuYe";
    }
    @RequestMapping("/personalCenter")
    public String personalData(){

        return "personalCenter";
    }


    @RequestMapping("/checkDenglu")
    @ResponseBody
    public Result checkDenglu(String ticket){
            String userJson= (String) redisTemplate.opsForValue().get(ticket);
            if(!StringUtils.isEmpty(userJson)){
                try {

                    User user= objectMapper.readValue(userJson,User.class);
                    return  new Result(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return new Result("fail");
    }

    @RequestMapping("/dengchu")

    public String dengchu(HttpServletRequest request, HttpServletResponse response){
        //获取cookie数据
        Cookie[] cookies  = request.getCookies();
        for (Cookie cookie : cookies) {
            if("TICKET".equals(cookie.getName())){
                String ticket = cookie.getValue();
                redisTemplate.delete(ticket);
                break; //跳出循环
            }
        }
        //清空Cooke数据
        Cookie cookie = new Cookie("TICKET", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);//立即删除
        response.addCookie(cookie);

        //重定向到系统首页
        return "redirect:/zhuYe.ftl";
    }


}
