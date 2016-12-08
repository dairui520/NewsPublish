package com.cdy.action;

import com.opensymphony.xwork2.ActionSupport;
import com.cdy.POJO.Content;
@SuppressWarnings("serial")
public class NewsDetail extends ActionSupport {

	String detail_content;
	String index;
	Content content;
	

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDetail_content() {
		return detail_content;
	}

	public void setDetail_content(String detail_content) {
		this.detail_content = detail_content;
	}

	@Override
	public String execute() throws Exception {
		
		content=NewsManage.list.get(Integer.parseInt(index));
		detail_content=content.getContent();
		return SUCCESS;
		
	}
}
