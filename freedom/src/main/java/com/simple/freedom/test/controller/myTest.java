package com.simple.freedom.test.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.freedom.common.util.CreateImageCode;
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
		System.out.println("过滤器拦截测试111111111111111111111");
	}
	
	@RequestMapping("/login.do")
	public String login()
	{
		System.out.println("过滤器拦截测试111");
		return "index";
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/getImageCode.do")
	public void getImageCode(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		  response.setContentType("image/jpeg"); //禁止图像缓存。
		  response.setHeader("Pragma", "no-cache");
		  response.setHeader("Cache-Control", "no-cache");
		  response.setDateHeader("Expires", 0);
		  
		  
		  CreateImageCode vCode = new CreateImageCode(100,30,5,10);
		  request.getSession().setAttribute("code", vCode.getCode());
		  vCode.write(response.getOutputStream()); 
	}
}
