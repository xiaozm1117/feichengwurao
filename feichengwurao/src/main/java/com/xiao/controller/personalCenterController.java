package com.xiao.controller;

import com.xiao.Result;
import com.xiao.feign.PersonalCenterFeign;
import com.xiao.other.UserThreadLocal;
import com.xiao.pojo.PersonalData;
import com.xiao.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class personalCenterController {
    @Resource
    private PersonalCenterFeign personalCenterFeign;

    @RequestMapping("/personalData")
    public String personalData(){

        return  "personalData";
    }
    @RequestMapping("/personalDataEditor")
    public String personalDataEditor(){

        return  "personalDataEditor";
    }

    @RequestMapping("/doPersonalData")
    @ResponseBody
    public Result personalData(String ticket){
        try {
            PersonalData personalData =personalCenterFeign.personalData(ticket);

            return new Result(personalData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result("fail");

    }

    @RequestMapping("/personalDataUpdate")
    @ResponseBody
    public Result personalDataUpdate(PersonalData personalData){
        User user=UserThreadLocal.get();
        try {
            personalData.setUserId(user.getId());
            personalCenterFeign.personalDataUpdate(personalData);
            return new Result("ok");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result("fail");

    }


}
