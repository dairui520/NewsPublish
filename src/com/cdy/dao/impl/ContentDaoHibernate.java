package com.cdy.dao.impl;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.Session;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

import com.cdy.POJO.Content;
import com.cdy.dao.ContentDao;
import com.cdy.javabean.PageBean;

public class ContentDaoHibernate extends HibernateDaoSupport implements
		ContentDao {

	@Override
	public Content get(Integer id) {

		return getHibernateTemplate().get(Content.class, id);
	}

	@Override
	public Integer save(Content content) {

		return (Integer) getHibernateTemplate().save(content);
	}

	@Override
	public void update(Content content) {

		getHibernateTemplate().update(content);
	}

	@Override
	public void delete(Integer id) {

		getHibernateTemplate().delete(get(id));
	}

	@Override
	public void delete(Content content) {

		getHibernateTemplate().delete(content);
	}

	@Override
	public List<Content> findByTitle(String title) {
		return (List<Content>) getHibernateTemplate().find("from Content p where p.title like '%" + title + "%'");
	}

	@Override
	public List findAllContent() {
		return (List<Content>) getHibernateTemplate().find("from Content");
	}

	@Override
	public long getContentNumber(String hql) {
		if (hql==null||hql.isEmpty()) {
			hql="1=1";
		}
		
		return (Long) getHibernateTemplate().find(
				"select count(*) from Content where "+hql).get(0);
	}

	@Override
	public List findByPage(final String hql, final int offset,final int pageSize) {
		
//		System.out.println(hql);
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)throws HibernateException, SQLException {

				List result = session.createQuery("from Content where"+hql+" ORDER BY ID DESC")
						.setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	
	@Override
	public List<Content> getItemsContents(final PageBean pager) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				List result = null;
				try {
					String hql="from Content where "+pager.SearchData+" ORDER BY ID desc limit "
							+pager.NumPerPage*(pager.CurrentPage-1)+","+pager.NumPerPage;
					
					 result =  session.createQuery("from Content where "+pager.SearchData+" ORDER BY ID desc ")
					.setFirstResult(pager.NumPerPage*(pager.CurrentPage-1))
					.setMaxResults(pager.NumPerPage)
							.list();
				} catch (Exception e) {
					System.out.println(e);
				}	
				return result;
			}
		});
		return (List<Content>)list;
		/*return (List<Content>) getHibernateTemplate().find("from Content where "+pager.SearchData
				+"and id not in (select top "+pager.NumPerPage*(pager.CurrentPage-1)
				+"id from Content where "+pager.SearchData+"ORDER BY id DESC)"+"ORDER BY ID desc"	
				);*/
	}

}
