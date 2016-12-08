<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 

  <div class="panel admin-panel" style="height:96%;">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 欢迎页面</strong></div>
    <div class="body-content" style="height:94%;">
        <form method="post" class="form-x" action="">
            <div class="form-group" style="margin:0 auto;padding-top:18%;padding-left:24%">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <div class="tips" style="font-family:'微软雅黑';font-size:24px;">您好,<span class="icon-user-md" style="color: #0ae; "><%=session.getAttribute("username") %></span>! 欢迎来到公共资源管理中心</div>
                </div>
            </div>
        </form>
    </div>
</div>

