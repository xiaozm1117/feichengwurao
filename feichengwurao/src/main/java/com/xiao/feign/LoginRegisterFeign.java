package com.xiao.feign;

import com.xiao.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("fcwr-part1")
public interface LoginRegisterFeign {
    @RequestMapping("/zhuce")
    public void zhuce(@RequestBody User user);

    @RequestMapping("/checkname")
    public void checkname(@RequestParam("name") String name);

    @RequestMapping("/denglu")
    public String denglu(@RequestBody User user);
    @RequestMapping("/checkDenglu")
    User checkDenglu(String ticket);
}
