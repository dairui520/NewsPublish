<%@page import="com.cdy.POJO.Content"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%Object id=session.getAttribute("id") != null ? session.getAttribute("id") : "";
Content content =(Content)session.getAttribute("content");

 %>
<%String htmlData= ""; 
if(!id.equals("")){
 %><%
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<!-- 编辑器引用 -->
<link rel="stylesheet" href="/NewPublish/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="/NewPublish/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/NewPublish/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/NewPublish/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="/NewPublish/kindeditor/plugins/code/prettify.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/NewPublish/kindeditor/plugins/code/prettify.css',
			uploadJson : '/NewPublish/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/NewPublish/kindeditor/jsp/file_manager_json.jsp',
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
<%@ include file="index.jsp" %>
<%if(!id.equals("")) {%> 
<div class="admin">
<div class="panel admin-panel" style="height:96%;">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加内容</strong></div>
  <div class="body-content">
    <form method="post" name="example" class="form-x" action="NewsManage!update.action">  
       <div class="form-group">
                    <div class="label">
                        <label for="title" class="validation">新闻标题：</label>
                        <input name="id" type="hidden" value="<%=content.getId()%>">
                   
                    </div>
                    <div class="field">
                        <input type="text" id="title" style="width:25%; height:35px; float:left" class=" input w50" value="<%=content.getTitle() %>" name="title" data-validate="required:请输入新闻标题" />
                        <div class="tips"></div>
                    </div>
                </div>    
                <div class="form-group">
          <div class="label">
            <label>新闻类别：</label>
          </div>
          <div class="field">
            <select name="type" class="input w50"  data-validate="required:请输入新闻类型">
            
              <option value="<%=content.getType()%>" selected="selected"><%=content.getType()%></option>
              <option value="国内新闻">国内新闻</option>
              <option value="国际新闻">国际新闻</option>
              <option value="军事新闻">军事新闻</option>
              <option value="娱乐新闻">娱乐新闻</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>   
           
                <div class="form-group">
                    <div class="label">
                        <label for="Goods_num" class="validation">新闻内容：</label>
                    </div>
                    <div class="field">
                        <textarea name="content1" cols="100" rows="8" style="width:800px;height:200px;visibility:hidden;"><%=htmlspecialchars(content.getContent())%></textarea>
						<br/>
		 				<!-- <input type="hidden" name="id" value=""> -->
                        <div class="tips"></div>
                    </div>
                </div>
                    <div class="form-group">
                    <div class="label">
                        <label></label>
                    </div>
                    <div class="field">
                        <button class="button bg-main icon-check-square-o" name="updateUser" id="updateUser" type="submit"> 提交</button>
                    </div>
                </div>
    </form>
  </div>
</div>
</div>
<% 
// 要清除session里面的属性
 session.removeAttribute("id");
 session.removeAttribute("content");
 }else{
%>
<div class="admin">
<div class="panel admin-panel" style="height:96%;">

  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加内容</strong></div>
  <div class="body-content">
    <form method="post" name="example" class="form-x" action="NewsManage!add.action">  
       <div class="form-group">
                    <div class="label">
                        <label for="title" class="validation">新闻标题：</label>
                    </div>
                    <div class="field">
                        <input type="text" id="title" style="width:25%; height:35px; float:left" class=" input w50" value="" name="title" data-validate="required:请输入新闻标题" />
                        <div class="tips"></div>
                    </div>
                </div>    
                <div class="form-group">
          <div class="label">
            <label>新闻类别：</label>
          </div>
          <div class="field">
            <select name="type" class="input w50"  data-validate="required:请输入新闻类型">
              <option value="">请选择分类</option>
              <option value="国内新闻">国内新闻</option>
              <option value="国际新闻">国际新闻</option>
              <option value="军事新闻">军事新闻</option>
              <option value="娱乐新闻">娱乐新闻</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>   
           
                <div class="form-group">
                    <div class="label">
                        <label for="Goods_num" class="validation">新闻内容：</label>
                    </div>
                    <div class="field">
                        <textarea name="content1" cols="100" rows="8" style="width:800px;height:300px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
						<br/>
		 				<!-- <input type="hidden" name="id" value=""> -->
                        <div class="tips"></div>
                    </div>
                </div>
                    <div class="form-group">
                    <div class="label">
                        <label></label>
                    </div>
                    <div class="field">
                        <button class="button bg-main icon-check-square-o" name="updateUser" id="updateUser" type="submit"> 提交</button>
                    </div>
                </div>
    </form>
  </div>
</div>
</div>
<%} %>

<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;

}
%>