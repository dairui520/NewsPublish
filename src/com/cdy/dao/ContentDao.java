package com.cdy.dao;

import java.util.List;

import com.cdy.POJO.Content;
import com.cdy.javabean.PageBean;

public interface ContentDao {
	/**
	 * 加载Content实例
	 * @param id 需要加载的Content实例的主键值
	 * @return 返回加载的Content实例
	 */ 
	Content get(Integer id);
	 
	/**
	 * 保存Content实例
	 * @param Content 需要保存的Content实例
	 * @return 刚刚保存的Content实例的标识属性值
	 */	
	Integer save(Content content);
	 
	/**
	 * 修改Content实例
	 * @param Content 需要修改的Content实例
	 */
	void update(Content content);
	
	/**
	 * 删除Content实例
	 * @param id 需要删除的Content实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 删除Content实例
	 * @param Content 需要删除的Content实例
	 */
	void delete(Content content);
	
	/**
	 * 根据新闻标题查找Content
	 * @param name 查询的文章标题
	 * @return 指定用户名对应的全部Content
	 */
	List<Content> findByTitle(String title);
	
	/**
	 * 查询全部Content实例
	 * @return 全部Content实例
	 */
	public List findAllContent();
	
	/**
	 * 查询数据表中Content实例的总数
	 * @return 数据表中Content实例的总数
	 */
	long getContentNumber(String hql);
	
	/**
	 * 分页查询
	 * @param hql 查询语句
	 * @param offset 第一条记录的索引
	 * @param pageSize 每页的数目
	 * @return
	 */
	List findByPage(final String hql, final int offset, final int pageSize);
	
	
	/**
	 * 分页查询
	 */
	List<Content> getItemsContents(PageBean pager);
}
