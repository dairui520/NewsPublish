package com.cdy.service.impl;

import java.util.List;

import com.cdy.POJO.Content;
import com.cdy.dao.ContentDao;
import com.cdy.javabean.PageBean;
import com.cdy.service.NewsService;

public class NewsServiceImpl implements NewsService {

	private ContentDao contentDao;
	public ContentDao getContentDao() {
		return contentDao;
	}

	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

	@Override
	public Integer sava(Content content) {
		
		return contentDao.save(content);
	}

	@Override
	public void update(Content content) {
		
		contentDao.update(content);
	}

	@Override
	public Content get(Integer id) {
		
		return  contentDao.get(id);
	}

	@Override
	public List<Content> getItems() {
		return contentDao.findAllContent();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getPageItems(String hql,int offset,int pageSize) {
		
		return (List<Content>)contentDao.findByPage(hql, offset, pageSize);
	}

	@Override
	public long getContentNumber(String hql) {
		return contentDao.getContentNumber(hql);
	}

	@Override
	public List<Content> getItemsContents(PageBean pager) {
		return contentDao.getItemsContents(pager);
	}

	@Override
	public void delete(Integer id) {
		
		contentDao.delete(id);
	}

}
