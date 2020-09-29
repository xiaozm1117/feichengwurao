package com.xiao.feign;

import com.xiao.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("fcwr-part1")
public interface ZhuYeFeign {


}
