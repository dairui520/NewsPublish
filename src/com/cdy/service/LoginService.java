package com.cdy.service;

import com.cdy.POJO.Admin;

public interface LoginService {

	// ��½
	boolean signIn(Admin admin);
	
	//ע��
	boolean signUp(Admin admin );
	
	//�����û������ض���
	Admin getByUsername(String username);
	
	//�����û���Ϣ
	void update(Admin admin);
	
	// �ж��Ƿ�ע������û���Ψһ
	boolean isRegister(String username);
}
