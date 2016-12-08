<%@page import="com.cdy.POJO.*"%>
<%@page import="com.cdy.action.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
Object index=session.getAttribute("index")!=null?session.getAttribute("index"):"";

/*  String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : ""; 
 */
Object id=session.getAttribute("id") != null ? session.getAttribute("id") : "";
Object title=session.getAttribute("title")!=null?session.getAttribute("title"):"";
String htmlData="";
if(!index.equals(""))
{
	htmlData=NewsManage.list.get(Integer.parseInt(index.toString())).getContent();
}
String content=(String)session.getAttribute("content");
System.out.println(index);
System.out.println(htmlData);
System.out.println(id);
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>KindEditor JSP</title>
<link rel="stylesheet" href="../themes/default/default.css" />
<link rel="stylesheet" href="../plugins/code/prettify.css" />
<script charset="utf-8" src="../kindeditor.js"></script>
<script charset="utf-8" src="../lang/zh_CN.js"></script>
<script charset="utf-8" src="../plugins/code/prettify.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content1"]', {
			cssPath : '../plugins/code/prettify.css',
			uploadJson : '../jsp/upload_json.jsp',
			fileManagerJson : '../jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
</script>
</head>
<body style="margin-left: 50px;">
	<%if(!id.equals("")) {%>
	
	<br>
	<form name="example" method="post" action="Article!update_content.action">
	<div style="margin-bottom: 10px;">
	 <span>文章标题： </span> <input type="text" name="title" value="<%=title%>"><br>
	</div>
		<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br/>
		 <input type="hidden" name="id" value="<%=id%>"> <input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>

	<% }else{
%>
	
	<br>
	<form name="example" method="post" action="Article!add.action">
	<div style="margin-bottom: 10px;">
	<span>文章标题： </span><input type="text" name="title" ><br>
	</div>
		<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br /> <input type="submit" name="button" value="提交内容" /> (提交快捷键:Ctrl + Enter)
	</form>
	<%} %>

</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;

}
%>