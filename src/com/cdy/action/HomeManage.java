package com.cdy.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.cdy.POJO.Admin;
import com.cdy.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

public class HomeManage extends ActionSupport {

	private String userPwd;
	private String oldPwd;
	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	private LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * �޸�����
	 * 
	 * @return
	 */
	public void EditPwd() {
		HttpServletResponse response = ServletActionContext.getResponse();
		if (userPwd == null || userPwd.trim().isEmpty()) {
			try {
				response.getWriter().print(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			String username = (String) session.getAttribute("username");
			Admin admin = loginService.getByUsername(username);
			try {

				// ���ԭ�������
				if (!admin.getPassword().trim().equals(oldPwd.trim())) {
					response.getWriter().print("incorrect"); // ����Ajax ����				
				}else {
					admin.setPassword(userPwd);
					loginService.update(admin);
					response.getWriter().print("yes"); // ����Ajax ����
				}

			
			} catch (IOException e) {

				try {
					response.getWriter().print("error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
	
	public String exitLogin() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		// ���session
		session.invalidate();
		return LOGIN;
	}
	
}
