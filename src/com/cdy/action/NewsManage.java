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

	private int id; // Content�� id
	private int deleta_id; // ɾ��������id
	private String content1; // �����������
	private String detail_Content; // չʾ��������
	private Content content; // �޸�������Ҫʹ��
	private String title;
	private String type;
	private String page; // ��ǰ��ҳ��ֵ
	private String query_start_date;
	private String query_end_date;
	private NewsService newsService;
	private ArrayList<Content> listContents;
	
	private long totalCount; // ��¼����

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
	 * ��������
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
		// ��ȡϵͳʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��

		// ��ȡʱ��� java��ȡȡ��Timestamp���͵ĵ�ǰϵͳʱ�� ��ʽ��2010-11-04 16:19:42
		Timestamp time = new Timestamp(System.currentTimeMillis());
		content.setUpdatetime(time);
		newsService.sava(content);
		clearValue();
		sreach();
		return SUCCESS;
	}

	/**
	 * �������� ����List����
	 */
	public void sreach() {
		int currentPage;
		PageBean pager=new PageBean(); // ��ҳ��
		String hql = null;
		pager.NumPerPage = 2; // ÿҳ��ʾ����Ŀ
		pager.SearchData = "1=1"; // Ĭ�ϲ�ѯ��������ֹ����
		String username = ActionContext.getContext().getSession()
				.get("username").toString();
		// �ж��ǹ���Ա������ͨ�û�
		if (!username.equals("admin")) {
			// ������ǹ���Ա����ѯ����������ֻ�����Լ���
			pager.SearchData += " and updateadmin='" + username + "'";
		}

		
		if (page == null || page.isEmpty()) {
			pager.CurrentPage = 1; // ���ó�ʼҳ
		} else {
			pager.CurrentPage = Integer.parseInt(page); // ����ҳ�൱ǰҳ��ֵ
		}

		if (query_start_date != null && !query_start_date.trim().equals("")
				&& query_end_date != null && !query_end_date.trim().equals("")) {
			Timestamp startTime = Timestamp.valueOf(query_start_date + ":00");
			Timestamp endTime = Timestamp.valueOf(query_end_date + ":00");

			if (startTime.getTime() - endTime.getTime() > 0) {
				/* return "ʱ��ѡ�����"; */
			}
			pager.SearchData += " and updatetime >= '" + startTime
					+ "'and updatetime <='" + query_end_date + "'";
		}

		if (title != null && !title.trim().isEmpty()) {
			title = title.trim();
			// ģ����ѯ
			pager.SearchData += " and title like '%" + title + "%'";
		}
		if (type != null && !type.trim().isEmpty()) {
			type = type.trim();
			// ģ����ѯ
			pager.SearchData += " and type like '%" + type + "%'";
		}

		// ����ҳ��
		long TotalCount = newsService.getContentNumber(pager.SearchData);
		totalCount = (TotalCount % 2 == 0 ? TotalCount / 2 : TotalCount / 2 + 1);
		pager.TotlaPage = (int) totalCount;
		listContents = (ArrayList<Content>) newsService.getItemsContents(pager);

	}

	/**
	 * �޸�����
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
		// ��ȡʱ��� java��ȡȡ��Timestamp���͵ĵ�ǰϵͳʱ�� ��ʽ��2010-11-04 16:19:42
		Timestamp time = new Timestamp(System.currentTimeMillis());
		content.setUpdatetime(time);
		newsService.update(content);
		System.out.println(page);
		// ���ֵջ
		clearValue();

		sreach();
		return SUCCESS;

	}

	// �޸���������
	public String update_News() {
		content = newsService.get(id);
		// HttpServletRequest request = ServletActionContext.getRequest();
		// ��Ϊ���ض���requst ��response�ᱻ��� ������session����������List ����
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("id", id);
		session.setAttribute("content", content);
		return INPUT;

	}

	// ɾ������
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
	 * ���ֵջ������ֵ
	 */
	private void clearValue() {
		title = "";
		type = "";

	}

}
