package com.simple.freedom.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.freedom.common.aop.BaseController;
import com.simple.freedom.common.util.CreateImageCode;

/**
 * 登陆相关
 * @author liuxiangtao90
 *
 */
@Controller
@RequestMapping("/login")
public class Login extends BaseController
{
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/getImageCode.do",method=RequestMethod.GET)
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
	
	@RequestMapping("/login.do")
	public String login()
	{
		return "index";
	}
}
