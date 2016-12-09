package com.cdy.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;

	static {
		// ����Configuration,�ö������ڶ�ȡhibernate.cfg.xml������ɳ�ʼ��
		Configuration config = new Configuration();
		config.configure();
		sessionFactory = config.buildSessionFactory();
	}

	/**
	 * ��ȡSessionFactory
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getcurrentSession() {
		session = sessionFactory.openSession();
		return session;
	}

	public static void closeSession(Session session) {

		if (null != session) {
			session.close();
		}
	}
}
