package com.simple.freedom.common.aop;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.simple.freedom.beans.UserBean;

/**
 * 单点登录
 * @author liuxiangtao90
 *
 */
@WebListener()
public class SingleSignOn implements HttpSessionListener{

	// 在线人数
	private  static int count;
	
	private static Map<String,HttpSession> sessionManage=Collections.synchronizedMap(new HashMap<String,HttpSession>());
	
	public Map<String, HttpSession> getSessionManage() {
		return sessionManage;
	}

	public int getCount()
	{
		return count;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		SingleSignOn.count++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		SingleSignOn.count--;
		if(arg0.getSession().getAttribute(SysVariable.USERSESSION)!=null)
		{
			sessionManage.remove((((UserBean)arg0.getSession().getAttribute(SysVariable.USERSESSION)).getUsername()));
			arg0.getSession().removeAttribute(SysVariable.USERSESSION);
		}
	}

	public static void sessionUserCreated(String userName,HttpSession sesion)
	{
		// 用户已经登录状态 则其session中移除用户信息 则拦截器中判断用户为未登录状态 则跳转到首页
		if(SingleSignOn.sessionManage.containsKey(userName))
		{
			SingleSignOn.sessionManage.get(userName).removeAttribute(SysVariable.USERSESSION);
			SingleSignOn.sessionManage.get(userName).setAttribute(SysVariable.MSG, "您的账号已在其他设备登录");
			//SingleSignOn.sessionManage.get(userName).invalidate();
		}
	    SingleSignOn.sessionManage.put(userName, sesion);
	}
}
