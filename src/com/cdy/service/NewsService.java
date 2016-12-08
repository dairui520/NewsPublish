package com.cdy.service;

import java.util.List;

import com.cdy.POJO.Content;
import com.cdy.javabean.PageBean;

public interface NewsService {

	/**
	 * 添加新闻
	 * @param content
	 */
	Integer sava(Content content);
	
	/**
	 * 更新
	 * @param content
	 */
	void update(Content content);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 根据ID 获取对象
	 * @param content
	 */
	Content get(Integer id);
	
	/**
	 * 获取新闻集合
	 * @return
	 */
	List<Content> getItems();
	
	/**
	 * 分页查询
	 */
	List<Content> getPageItems(String hql, int offset, int pageSize);
	
	
	/**
	 * 分页查询
	 */
	List<Content> getItemsContents(PageBean pager);
	
	/**
	 * 获取记录总数
	 */
	long getContentNumber(String hql);
}
