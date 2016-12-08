package com.cdy.service.impl;

import java.util.ArrayList;

import com.cdy.POJO.Admin;
import com.cdy.dao.AdminDao;
import com.cdy.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private AdminDao adminDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean signIn(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.login(admin);
	}

	@Override
	public boolean signUp(Admin admin) {
		// TODO Auto-generated method stub
		 if (adminDao.save(admin)>0) {
			return true;
		}
		 return false;
	}

	@Override
	public Admin getByUsername(String username) {
		
		ArrayList<Admin> list= (ArrayList<Admin>) adminDao.findByName(username);
		return list.get(0);
	}

	@Override
	public void update(Admin admin) {
		adminDao.update(admin);
		
	}

	@Override
	public boolean isRegister(String username) {
	
		ArrayList<Admin> list= (ArrayList<Admin>) adminDao.findByName(username);
		if (list.size()>0) {
			return false;
		}
		return true;
	}
}
