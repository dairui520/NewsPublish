package com.cdy.dao;

import java.util.List;


import com.cdy.POJO.Admin;

public interface AdminDao {

	/**
	 * 加载Admin实例
	 * @param id 需要加载的Admin实例的主键值
	 * @return 返回加载的Admin实例
	 */ 
	Admin get(Integer id);
	 
	/**
	 * 保存Admin实例
	 * @param Admin 需要保存的Person实例
	 * @return 刚刚保存的Person实例的标识属性值
	 */	
	Integer save(Admin admin);
	 
	/**
	 * 修改Admin实例
	 * @param Admin 需要修改的Admin实例
	 */
	void update(Admin admin);
	
	/**
	 * 删除Admin实例
	 * @param id 需要删除的Admin实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 删除Admin实例
	 * @param Admin 需要删除的Admin实例
	 */
	void delete(Admin admin);
	
	/**
	 * 根据用户名查找Admin
	 * @param name 查询的人名
	 * @return 指定用户名对应的全部Admin
	 */
	List<Admin> findByName(String name);
	
	/**
	 * 查询全部Admin实例
	 * @return 全部Admin实例
	 */
	public List findAllAdmin();
	
	/**
	 * 查询数据表中Admin实例的总数
	 * @return 数据表中Admin实例的总数
	 */
	long getAdminNumber();
	/**
	 * 登陆 是否包含这条数据
	 * @param admin
	 * @return
	 */
	boolean login(Admin admin);
}
