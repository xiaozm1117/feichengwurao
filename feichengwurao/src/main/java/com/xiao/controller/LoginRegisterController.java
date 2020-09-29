package com.xiao.controller;

import com.xiao.Result;
import com.xiao.feign.LoginRegisterFeign;
import com.xiao.pojo.User;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginRegisterController {
    @Resource
    private LoginRegisterFeign loginRegisterFeign;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/zhuce")
    public String zhuce() {
        return "zhuce";
    }

    @RequestMapping("/denglu")
    public String denglu() {
        return "denglu";
    }

    @RequestMapping("/doZhuce")
    @ResponseBody
    public Result dozhuce(User user) {
        try{
            loginRegisterFeign.zhuce(user);
        } catch (Exception e){
            e.printStackTrace();
            return new Result("fail");
        }
        return new Result("ok");


    }

    @RequestMapping("/checkname")
    @ResponseBody
    public Result checkname(String name) {
        try {
            loginRegisterFeign.checkname(name);
            return new Result("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result("fail");
    }

 @RequestMapping("/doDenglu")
    @ResponseBody
    public Result denglu(User user, HttpServletResponse response) {
        try {
            String token = loginRegisterFeign.denglu(user);

            Cookie cookie = new Cookie("TICKET", token);
            cookie.setPath("/"); //表示在浏览器根目录生效
            cookie.setMaxAge(3600 * 24 * 7); //设定生命周期 单位秒
            response.addCookie(cookie);
            return new Result("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result("fail");

    }




}
	 

