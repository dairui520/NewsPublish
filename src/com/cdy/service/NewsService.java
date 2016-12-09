package com.cdy.service;

import java.util.List;

import com.cdy.POJO.Content;
import com.cdy.javabean.PageBean;

public interface NewsService {

	/**
	 * �������
	 * @param content
	 */
	Integer sava(Content content);
	
	/**
	 * ����
	 * @param content
	 */
	void update(Content content);
	
	/**
	 * ɾ��
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * ����ID ��ȡ����
	 * @param content
	 */
	Content get(Integer id);
	
	/**
	 * ��ȡ���ż���
	 * @return
	 */
	List<Content> getItems();
	
	/**
	 * ��ҳ��ѯ
	 */
	List<Content> getPageItems(String hql, int offset, int pageSize);
	
	
	/**
	 * ��ҳ��ѯ
	 */
	List<Content> getItemsContents(PageBean pager);
	
	/**
	 * ��ȡ��¼����
	 */
	long getContentNumber(String hql);
}
