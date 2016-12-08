package com.cdy.POJO;

import java.sql.Timestamp;

public class Content 
{
	private  int    id;
	private   String title;
	private   String  content;
	private Timestamp updatetime;
	private  String   updateip;
	private   String  updateadmin;
	private String type; // 新闻类型
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public String getUpdateip() {
		return updateip;
	}
	public void setUpdateip(String updateip) {
		this.updateip = updateip;
	}
	public String getUpdateadmin() {
		return updateadmin;
	}
	public void setUpdateadmin(String updateadmin) {
		this.updateadmin = updateadmin;
	}
	
}
