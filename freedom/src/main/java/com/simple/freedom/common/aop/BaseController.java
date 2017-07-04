package com.simple.freedom.common.aop;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {
	
	public ModelAndView getMV()
	{
		return new ModelAndView();
	}
}
