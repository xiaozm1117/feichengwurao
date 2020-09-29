package com.xiao.feign;

import com.xiao.pojo.PersonalData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("fcwr-part1")
public interface PersonalCenterFeign {
    @RequestMapping("/personalData")
    public PersonalData personalData(@RequestParam("ticket") String ticket);

    @RequestMapping("/personalDataUpdate")
    public void personalDataUpdate(@RequestBody PersonalData personalData) ;
}
