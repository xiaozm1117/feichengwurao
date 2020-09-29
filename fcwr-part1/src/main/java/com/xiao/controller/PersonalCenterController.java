package com.xiao.controller;


import com.xiao.pojo.PersonalData;
import com.xiao.service.PersonalCenterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@ResponseBody
public class PersonalCenterController {

    @Resource
    private PersonalCenterService personalCenterService;

    @RequestMapping("/personalData")
    public PersonalData personalData(@RequestParam("ticket") String ticket) throws IOException {
        return personalCenterService.personalData(ticket);
    }

    @RequestMapping("/personalDataUpdate")
    public void personalDataUpdate(@RequestBody PersonalData personalData){

        personalCenterService.personalDataUpdate(personalData);
    }

}
