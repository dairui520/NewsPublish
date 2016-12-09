package com.cdy.dao;

import java.util.List;


import com.cdy.POJO.Admin;

public interface AdminDao {

	/**
	 * ����Adminʵ��
	 * @param id ��Ҫ���ص�Adminʵ��������ֵ
	 * @return ���ؼ��ص�Adminʵ��
	 */ 
	Admin get(Integer id);
	 
	/**
	 * ����Adminʵ��
	 * @param Admin ��Ҫ�����Personʵ��
	 * @return �ոձ����Personʵ���ı�ʶ����ֵ
	 */	
	Integer save(Admin admin);
	 
	/**
	 * �޸�Adminʵ��
	 * @param Admin ��Ҫ�޸ĵ�Adminʵ��
	 */
	void update(Admin admin);
	
	/**
	 * ɾ��Adminʵ��
	 * @param id ��Ҫɾ����Adminʵ���ı�ʶ����ֵ
	 */
	void delete(Integer id);
	
	/**
	 * ɾ��Adminʵ��
	 * @param Admin ��Ҫɾ����Adminʵ��
	 */
	void delete(Admin admin);
	
	/**
	 * �����û�������Admin
	 * @param name ��ѯ������
	 * @return ָ���û�����Ӧ��ȫ��Admin
	 */
	List<Admin> findByName(String name);
	
	/**
	 * ��ѯȫ��Adminʵ��
	 * @return ȫ��Adminʵ��
	 */
	public List findAllAdmin();
	
	/**
	 * ��ѯ���ݱ���Adminʵ��������
	 * @return ���ݱ���Adminʵ��������
	 */
	long getAdminNumber();
	/**
	 * ��½ �Ƿ������������
	 * @param admin
	 * @return
	 */
	boolean login(Admin admin);
}
