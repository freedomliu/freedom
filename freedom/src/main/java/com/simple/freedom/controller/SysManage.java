package com.simple.freedom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simple.freedom.common.aop.BaseController;

@Controller
@RequestMapping("/sys")
public class SysManage extends BaseController{
	
	/**
	 * 页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping("/forwardPage.do")
	public ModelAndView forwardPage(HttpServletRequest request)
	{
		String page= request.getParameter("pageName")+"";
		ModelAndView mv=getMV();
		mv.setViewName(page);
		return mv;
	}
}
