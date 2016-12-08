package com.cdy.dao.impl;

import java.util.List;

import javax.enterprise.inject.New;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cdy.POJO.Admin;
import com.cdy.dao.AdminDao;

public class AdminDaoHibernate extends HibernateDaoSupport implements AdminDao {

	@Override
	public Admin get(Integer id) {
	
		return getHibernateTemplate().get(Admin.class, id); 
	}

	@Override
	public Integer save(Admin admin) {
		
		return (Integer)getHibernateTemplate().save(admin);
	}

	@Override
	public void update(Admin admin) {
		
		getHibernateTemplate().update(admin);
	}

	@Override
	public void delete(Integer id) {
		
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public void delete(Admin admin) {
		
		getHibernateTemplate().delete(admin);
	}

	@Override
	public List<Admin> findByName(String name) {
		
		return (List<Admin>)getHibernateTemplate().find("from Admin p where p.username = ?" , name);
	}


	@Override
	public long getAdminNumber() {
		
		return (Long)getHibernateTemplate().find("select count(*) from Admin as p").get(0);
	}

	@Override
	public List findAllAdmin() {
		
		return (List<Admin>)getHibernateTemplate().find("from Admin");
	}

	@Override
	public boolean login(Admin admin) {
		List<Admin> list = null;
		/*getHibernateTemplate().findByExample(admin);*/
		try {
			String hql= "from Admin where username ='"+admin.getUsername()+"' and password ='"+admin.getPassword()+"'"; 
			List<Admin> find = (List<Admin>)getHibernateTemplate().find(hql);
			list= find;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		if (list.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}

}
