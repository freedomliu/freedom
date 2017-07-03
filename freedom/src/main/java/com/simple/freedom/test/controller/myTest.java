package com.simple.freedom.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.freedom.test.beans.User;
import com.simple.freedom.test.service.IUserService;

@Controller
@RequestMapping("/user")
public class myTest {
	@Autowired
	IUserService userService;
	
	@RequestMapping("/insertTest")
	@ResponseBody
	public void insertTest()
	{
		throw new RuntimeException("111");
		/*User user=new User();
		user.setId(14);
		user.setUsername("lxt");
		userService.insert(user);*/
	}
	
	@RequestMapping("/test1")
	@ResponseBody
	public void test1()
	{
		System.out.println("过滤器拦截测试");
	}
	
	@RequestMapping("/login.do")
	public String login()
	{
		System.out.println("过滤器拦截测试");
		return "index";
	}
}
