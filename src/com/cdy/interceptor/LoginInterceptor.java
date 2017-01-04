package com.cdy.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		ActionContext context=arg0.getInvocationContext();
		Map session=context.getSession();
		String username = (String)session.get("username");
	
		if (username != null && !username.isEmpty() )
		{
		
			return arg0.invoke();
		}else {
			return Action.LOGIN;
		}
	
//		HttpServletResponse response = ServletActionContext.getResponse();
//		PrintWriter pw;
//		try {
//			pw = response.getWriter();
//			pw.write("msg:用户名或者密码错误,stu:ok");
//			pw.flush();
//			pw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
	}

}
