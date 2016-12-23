package com.cdy.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;

public class myfilter implements Filter {

	private FilterConfig config;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// ��ȡ��Filter�����ò���
		String encoding = config.getInitParameter("encoding");
		String SignIn = config.getInitParameter("SignIn");
		String SignUp = config.getInitParameter("SignUp");

		// ����request�����õ��ַ���
		request.setCharacterEncoding(encoding);
		HttpServletResponse response2 = (HttpServletResponse) response;
		HttpServletRequest requ=(HttpServletRequest)request;
		HttpSession session = requ.getSession(true);

		// ��ȡ�ͻ������ҳ��
		String requestPath = requ.getServletPath();
		// ��session����ȥȡ����
		String username = (String) session.getAttribute("username");
		if (username == null || username.isEmpty()) {
			response2.sendRedirect(SignIn);
			
		} else {
			
			// ����
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

}
