package com.cdy.dao;

import java.util.List;

import com.cdy.POJO.Content;
import com.cdy.javabean.PageBean;

public interface ContentDao {
	/**
	 * ����Contentʵ��
	 * @param id ��Ҫ���ص�Contentʵ��������ֵ
	 * @return ���ؼ��ص�Contentʵ��
	 */ 
	Content get(Integer id);
	 
	/**
	 * ����Contentʵ��
	 * @param Content ��Ҫ�����Contentʵ��
	 * @return �ոձ����Contentʵ���ı�ʶ����ֵ
	 */	
	Integer save(Content content);
	 
	/**
	 * �޸�Contentʵ��
	 * @param Content ��Ҫ�޸ĵ�Contentʵ��
	 */
	void update(Content content);
	
	/**
	 * ɾ��Contentʵ��
	 * @param id ��Ҫɾ����Contentʵ���ı�ʶ����ֵ
	 */
	void delete(Integer id);
	
	/**
	 * ɾ��Contentʵ��
	 * @param Content ��Ҫɾ����Contentʵ��
	 */
	void delete(Content content);
	
	/**
	 * �������ű������Content
	 * @param name ��ѯ�����±���
	 * @return ָ���û�����Ӧ��ȫ��Content
	 */
	List<Content> findByTitle(String title);
	
	/**
	 * ��ѯȫ��Contentʵ��
	 * @return ȫ��Contentʵ��
	 */
	public List findAllContent();
	
	/**
	 * ��ѯ���ݱ���Contentʵ��������
	 * @return ���ݱ���Contentʵ��������
	 */
	long getContentNumber(String hql);
	
	/**
	 * ��ҳ��ѯ
	 * @param hql ��ѯ���
	 * @param offset ��һ����¼������
	 * @param pageSize ÿҳ����Ŀ
	 * @return
	 */
	List findByPage(final String hql, final int offset, final int pageSize);
	
	
	/**
	 * ��ҳ��ѯ
	 */
	List<Content> getItemsContents(PageBean pager);
}
