package com.cdy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CORBA.Request;

import com.cdy.POJO.Content;
import com.cdy.javabean.PageBean;
import com.cdy.service.NewsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NewsManage extends ActionSupport {

	private int id; // Content的 id
	private int deleta_id; // 删除的新闻id
	private String content1; // 添加新闻内容
	private String detail_Content; // 展示文章详情
	private Content content; // 修改新闻需要使用
	private String title;
	private String type;
	private String page; // 当前的页面值
	private String query_start_date;
	private String query_end_date;
	private NewsService newsService;
	private ArrayList<Content> listContents;
	
	private long totalCount; // 记录总数

	public long getTotalCount() {
		return totalCount;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getQuery_start_date() {
		return query_start_date;
	}

	public void setQuery_start_date(String query_start_date) {
		this.query_start_date = query_start_date;
	}

	public String getQuery_end_date() {
		return query_end_date;
	}

	public void setQuery_end_date(String query_end_date) {
		this.query_end_date = query_end_date;
	}

	public ArrayList<Content> getListContents() {
		return listContents;
	}

	public void setListContents(ArrayList<Content> listContents) {
		this.listContents = listContents;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static ArrayList<Content> list = new ArrayList<Content>();

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getDetail_Content() {
		return detail_Content;
	}

	public void setDetail_Content(String detail_Content) {
		this.detail_Content = detail_Content;
	}

	public int getDeleta_id() {
		return deleta_id;
	}

	public void setDeleta_id(int deleta_id) {
		this.deleta_id = deleta_id;
	}

	/**
	 * 增加新闻
	 * 
	 * @return
	 */
	public String add() {

		Content content = new Content();
		content.setTitle(title);
		content.setContent(content1);
		content.setType(type);
		content.setUpdateadmin(ActionContext.getContext().getSession()
				.get("username").toString());
		content.setUpdateip(ServletActionContext.getRequest().getRemoteAddr());
		// 获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

		// 获取时间戳 java获取取得Timestamp类型的当前系统时间 格式：2010-11-04 16:19:42
		Timestamp time = new Timestamp(System.currentTimeMillis());
		content.setUpdatetime(time);
		newsService.sava(content);
		clearValue();
		sreach();
		return SUCCESS;
	}

	/**
	 * 查找新闻 返回List集合
	 */
	public void sreach() {
		int currentPage;
		PageBean pager=new PageBean(); // 分页类
		String hql = null;
		pager.NumPerPage = 2; // 每页显示的数目
		pager.SearchData = "1=1"; // 默认查询条件，防止出错
		String username = ActionContext.getContext().getSession()
				.get("username").toString();
		// 判断是管理员还是普通用户
		if (!username.equals("admin")) {
			// 如果不是管理员，查询出来的文章只能是自己的
			pager.SearchData += " and updateadmin='" + username + "'";
		}

		
		if (page == null || page.isEmpty()) {
			pager.CurrentPage = 1; // 设置初始页
		} else {
			pager.CurrentPage = Integer.parseInt(page); // 给分页类当前页赋值
		}

		if (query_start_date != null && !query_start_date.trim().equals("")
				&& query_end_date != null && !query_end_date.trim().equals("")) {
			Timestamp startTime = Timestamp.valueOf(query_start_date + ":00");
			Timestamp endTime = Timestamp.valueOf(query_end_date + ":00");

			if (startTime.getTime() - endTime.getTime() > 0) {
				/* return "时间选择错误"; */
			}
			pager.SearchData += " and updatetime >= '" + startTime
					+ "'and updatetime <='" + query_end_date + "'";
		}

		if (title != null && !title.trim().isEmpty()) {
			title = title.trim();
			// 模糊查询
			pager.SearchData += " and title like '%" + title + "%'";
		}
		if (type != null && !type.trim().isEmpty()) {
			type = type.trim();
			// 模糊查询
			pager.SearchData += " and type like '%" + type + "%'";
		}

		// 求总页数
		long TotalCount = newsService.getContentNumber(pager.SearchData);
		totalCount = (TotalCount % 2 == 0 ? TotalCount / 2 : TotalCount / 2 + 1);
		pager.TotlaPage = (int) totalCount;
		listContents = (ArrayList<Content>) newsService.getItemsContents(pager);

	}

	/**
	 * 修改文章
	 * 
	 * @return
	 */
	public String update() {

		Content content = new Content();
		content.setId(id);
		content.setTitle(title);
		content.setContent(content1);
		content.setType(type);
		content.setUpdateadmin(ActionContext.getContext().getSession()
				.get("username").toString());
		content.setUpdateip(ServletActionContext.getRequest().getRemoteAddr());
		// 获取时间戳 java获取取得Timestamp类型的当前系统时间 格式：2010-11-04 16:19:42
		Timestamp time = new Timestamp(System.currentTimeMillis());
		content.setUpdatetime(time);
		newsService.update(content);
		System.out.println(page);
		// 清除值栈
		clearValue();

		sreach();
		return SUCCESS;

	}

	// 修改文章内容
	public String update_News() {
		content = newsService.get(id);
		// HttpServletRequest request = ServletActionContext.getRequest();
		// 因为是重定向，requst 和response会被清空 所以用session来保存标题和List 索引
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("id", id);
		session.setAttribute("content", content);
		return INPUT;

	}

	// 删除文章
	public void delete() {

		int deleteID = deleta_id;
		try {
			newsService.delete(deleteID);
			sreach();
			HttpServletResponse response = ServletActionContext.getResponse();

			PrintWriter out = response.getWriter();
			out.print(1);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public String execute() throws Exception {

		sreach();
		return SUCCESS;

	}

	public String detail() {
		Content content;
		content = newsService.get(id);
		detail_Content = content.getContent();
		return "DETAIl";
	}

	/**
	 * 清楚值栈的属性值
	 */
	private void clearValue() {
		title = "";
		type = "";

	}

}
