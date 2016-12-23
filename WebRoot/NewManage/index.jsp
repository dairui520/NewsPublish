<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="/NewPublish/NewManage/css/pintuer.css">
    <link rel="stylesheet" href="/NewPublish/NewManage/css/admin.css">
    <script src="/NewPublish/NewManage/js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="/NewPublish/NewManage/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="HomeManage!exitLogin.action"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>基本设置</h2>
  <ul style="display:block">
  	<li><a href="/NewPublish/NewManage/index.jsp" ><span class="icon-caret-right"></span>欢迎页面</a></li>
    <li><a href="/NewPublish/NewManage/EditPwd.jsp" ><span class="icon-caret-right"></span>修改密码</a></li>
   
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>栏目管理</h2>
  <ul style="display:block">
    <li><a href="<%=path %>/NewManage/AddNews.jsp" ><span class="icon-caret-right"></span>发布新闻</a></li>
    <li><a href="<s:url  action="NewsManage.action"></s:url>"><span class="icon-caret-right"></span>新闻列表</a></li>
    <li><a href="#" ><span class="icon-caret-right"></span>分类管理</a></li>        
  </ul>  
</div>
 <script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script> 
<ul class="bread">
  <li><a href="#"  class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
  <li><b>当前位置：</b><span style="color:red;">后台首页</php></span>
  </li>
</ul>
<div class="admin">
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
</div>
<div style="text-align:center;">
</div>
</body>
</html>
