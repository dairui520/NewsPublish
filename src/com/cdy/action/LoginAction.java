package com.cdy.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdy.POJO.Admin;
import com.cdy.service.LoginService;
import com.cdy.utils.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	// 定义封装请求参数的username和password属性
	private Admin admin;
	private String username;
	private String password;
	private LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void login() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter pw;
		username = username != null ? username : "";
		password = password != null ? password : "";
		admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);

		if (loginService.signIn(admin)) {
			try {
				// 存入session中
				ActionContext.getContext().getSession()
						.put("username", username);
				pw = response.getWriter();
				JsonResult(pw, "ok");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				pw = response.getWriter();
				JsonResult(pw, "no");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 注册用户
	public void SignUp() {

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter pw;
		username = username != null ? username : "";
		password = password != null ? password : "";
		boolean result = loginService.isRegister(username);
		if (!result) {
			// 用户名已存在
			try {
				response.getWriter().print("regitered");		
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		admin = new Admin();
		
		admin.setUsername(username);
		admin.setPassword(password);
			
		if (loginService.signUp(admin)) {
			try {
				// 存入session中
				ActionContext.getContext().getSession().put("username", username);
				pw = response.getWriter();
				JsonResult(pw, "ok");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				pw = response.getWriter();
				JsonResult(pw, "no");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String execute() throws Exception {
		
		System.out.println("fsfs");
		return null;
	}

	/**
	 * 返回Ajax 数据
	 * 
	 * @param out
	 */
	public void JsonResult(PrintWriter out, String Result) {
		out.print(Result);
		out.flush();
		out.close();
	}
}
