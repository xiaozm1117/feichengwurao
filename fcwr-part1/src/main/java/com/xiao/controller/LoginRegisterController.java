package com.xiao.controller;

import com.xiao.pojo.User;
import com.xiao.service.LoginRegisterService;
import com.xiao.service.LoginRegisterServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@ResponseBody
public class LoginRegisterController {

	@Resource
	private LoginRegisterService loginRegisterService;
	@RequestMapping("/zhuce")
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void zhuce(@RequestBody User user){

		loginRegisterService.zhuce(user);
	}
	@RequestMapping("/checkname")
	public void checkname(@RequestParam("name") String name){

		loginRegisterService.checkname(name);
    }

	@RequestMapping("/denglu")
	public String denglu(@RequestBody User user){
		return loginRegisterService.denglu(user);
	}


}
