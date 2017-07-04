package com.simple.freedom.beans;

import java.io.Serializable;
/**
 * 用户实体类
 * @author liuxiangtao90
 *
 */
public class UserBean implements Serializable{

	private static final long serialVersionUID = 8399063764886377819L;
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
