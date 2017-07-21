package com.simple.freedom.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理
 * @author Administrator
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver {  
	  
    @Override  
    public ModelAndView resolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) { 
    	ModelAndView mv= new ModelAndView();
    	mv.setViewName("sys/exception");
    	mv.addObject("exception",ex);
    	Logger logger = Logger.getLogger(handler.getClass());
    	logger.error(ex);
        return mv;  
    }  
  
}
