package com.cdy.service;

import com.cdy.POJO.Admin;

public interface LoginService {

	// 登陆
	boolean signIn(Admin admin);
	
	//注册
	boolean signUp(Admin admin );
	
	//根据用户名返回对象
	Admin getByUsername(String username);
	
	//更新用户信息
	void update(Admin admin);
	
	// 判断是否注册过，用户名唯一
	boolean isRegister(String username);
}
