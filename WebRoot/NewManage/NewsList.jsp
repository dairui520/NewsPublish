<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/NewManage/index.jsp" %>

<div class="admin">
<script src="/NewPublish/NewManage/js/jquery.datetimepicker.js"></script>

<link href="/NewPublish/NewManage/css/jquery.datetimepicker.css" rel="stylesheet" />
<link href="/NewPublish/layer/laypage/skin/laypage.css" rel="stylesheet" />
<script src="/NewPublish/layer/laypage/laypage.js"></script>
<script>
    window.onload = function () {
        var aSpan = document.getElementsByClassName('titlenowbg_soltu');
        for (i = 0; i < aSpan.length; i++) {

            aSpan[i].innerHTML = cutstr(aSpan[i].innerHTML, 100);
        }
    }
</script>
<style type="text/css">
    .myinput {
        font-size: 14px;
        padding: 10px;
        border: solid 1px #ddd;
        border-radius: 3px;
        -webkit-appearance: none;
    }
</style>
<style>
    #laydate_box .laydate_top .laydate_ym {
        height: 26px;
        border-bottom: 1px solid #C6C6C6;
    }
    #laydate_box{
        height:216px;
    }
        #laydate_box .laydate_top{
            height:36px;
            padding:4px;
        }
            #laydate_box .laydate_top .laydate_y {
                margin-right:10px;
            }
        
        
         #laydate_box .laydate_bottom {
            border-bottom: none;
        }
    #laydate_ys{
        width:121px;
    }
    .add-on .icon-calendar{
        padding-left:5px;
    }
</style>
<script type="text/javascript">
    $(function () {
        var totalpage ='<s:property value="totalCount"/>';
        var N_ST=' ${query_start_date}';
        var N_ET=' ${query_end_date}';
        var title=' ${title}';
        var type=' ${type}';
        laypage({
            cont: $('#pagination'), //容器。值支持id名、原生dom对象，jquery对象,
            pages: totalpage,//总页数
            skip: true, //是否开启跳页
            skin: '#099ACF',
            groups: 3,//连续显示分页数
            curr: function () { //通过url获取当前页，也可以同上（pages）方式获取
                var page = location.search.match(/page=(\d+)/);
                return page ? page[1] : 1;
            }(),
            jump: function (e, first) { //触发分页后的回调
                if (!first) { //一定要加此判断，否则初始时会无限刷新

                    //location.href = 'Index?page=' + e.curr;
                    location.href="NewsManage.action?"+'&query_start_date='+N_ST +'&query_end_date='+N_ET
                    +'&title='+title
                    +'&type='+type
                     +'&page='+e.curr;
                }
            }
        });
    });
</script>
<form method="get" action="NewsManage.action" id="formSearch" name="formSearch" style="margin-top:10px">
    <input type="hidden" name="order_state" id="order_state" value="">
    <input type="hidden" name="status" value="3" />

    <table class="search-form">
        <tbody>
            <tr style="margin-left:60px;display:inline-block;float:left;">
                <td class="w100"></td>
                <th style="font-size: 14px;">添加时间</th>
                <td class="w240" style="padding-left:5px">
                    <input type="text" style="height: 38px;" class="myinput" name="query_start_date" id="query_start_date" onclick="laydate({ istime: false, format: 'YYYY-MM-DD' })" value="<s:property value="query_start_date"/>"><label class="add-on"></label>&nbsp;–&nbsp;
                    <input id="query_end_date" style="height: 38px;" class="myinput" type="text" name="query_end_date" onclick="laydate({ istime: false, format: 'YYYY-MM-DD' })" value="<s:property value="query_end_date"/>"><label class="add-on"></label>
                </td>
            </tr>
            <tr style="float: left; margin-left: 30px; display: inline-block; ">
                <td class="w100"></td>
                <th style="font-size: 14px;">新闻标题</th>
                <td class="w240">
                    <input style="margin-left:10px;height: 38px;" type="text" class="myinput" name="title" id="title" value='<s:property value="title"/>'>
                </td>
            </tr>
            <tr style="float: left; margin-left: 30px; display: inline-block; ">
                <td class="w100"></td>
                <th style="font-size: 14px;">新闻类型</th>
                <td class="w240">
                    <input style="margin-left:10px;height: 38px;" type="text" class="myinput" name="type" id="type" value='<s:property value="type"/>'>
                    <input style="height: 38px;margin-left:30px;text-align: center;" type="submit" class="button border-main icon-search" value="搜索">
                </td>
            </tr>
        </tbody>
    </table>
</form>

<table border="1" cellpadding="3" cellspacing="0" width="90%" style="border:1px;margin-top:35px;margin-left:20px ">
    <thead>
        <tr align="center">
            <th class="titlenowbg" height="32" valign="top" width="10%" style="font-size: 14px;"><span>添加时间</span></th>
            <th class="titlenowbg" valign="top" width="40%" style="font-size: 14px;"><span>新闻标题</span></th>
            <th class="titlenowbg" valign="top" width="10%" style="font-size: 14px;"><span>新闻类型</span></th>
            <th class="titlenowbg" valign="top" width="10%" style="font-size: 14px;"><span>操作</span></th>
        </tr>
    </thead>
    <tbody >
        <s:if test="listContents.size<=0"> 
        	<tr class="no_data" style="margin-bottom:5px;line-height: 8;text-align: center;">
                <td colspan="8">没有符合条件的记录</td>
            </tr>
         </s:if>
         <s:else>
          <s:iterator value="listContents" var="content" status="dex">
            <tr>          
                <td class="titlenowbg" height="32" valign="top" width="5%" style="text-align:center;vertical-align:middle;font-size: 14px;/*设置垂直居中*/"><span><s:date name="#content.updatetime" format="yyyy-MM-dd HH:mm:ss"/> </span></td>
                <td class="titlenowbg" valign="top" width="40%" style="text-align:center;font-size: 14px;"><a href=' <s:url  action="NewsManage!detail.action">  
             	<s:param name="id" value="#content.id"></s:param>  </s:url>' target="_blank"> 
                <span style="line-height:38px;"><s:property value="#content.title"/>
                </span></a></td>              
                <td class="titlenowbg" height="32" valign="top" width="5%" style="text-align:center;vertical-align:middle;font-size: 14px;/*设置垂直居中*/"><span><s:property value="#content.type"/></span></td>               
                <td class="titlenowbg" height="32" valign="top" width="10%" style="text-align:center;vertical-align:middle;font-size: 14px;/*设置垂直居中*/">
                <a href="javascript:void(0)" onclick="DeleteNews(<s:property value="#content.id"/>)"><span class="icon-trash-o" >删除 </span></a>
              	<a href=' <s:url  action="NewsManage!update_News.action">  
             	<s:param name="id" value="#content.id"></s:param>  </s:url>' > 
             	<span class="icon-edit">修改</span>
             	</a>           
                </td>
            </tr>
</s:iterator>
         </s:else>
     
    </tbody>
    
    <tfoot style="text-align:center;vertical-align:middle">
        <tr style="margin-top:10px;height:50px">
            <td colspan="9">
                <div style ="margin-left:10px" id="pagination"></div>
            </td>
        </tr>
    </tfoot>
</table>
<script type="text/javascript">


    //时间过滤器
    $('#query_start_date').datetimepicker({ lang: 'ch' });

    $('#query_end_date').datetimepicker({ lang: 'ch' });

</script>
<script src="/NewPublish/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="/NewPublish/layer/skin/layer.css">
<script type="text/javascript">
    function DeleteNews(Content_ID) {
        layer.confirm('确定删除拍卖公告？', {
            btn: ['删除', '取消'] //按钮
        }, function () {
            $.ajax({
                'type': 'GET',
                'url': "/NewPublish/NewsManage!delete.action",
                'contentType': 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
                'data': "deleta_id=" + Content_ID,
                'dataType': 'text',
                'success': function (data) {
                if(data==1)
                {
                layer.msg("删除成功 ^_^", { shift: 4, icon: 1 }, 1000);
                    setTimeout(function () {
                        window.location.href="/NewPublish/NewsManage.action";
                    }, 1000);
                }else if(data==0)
                {
                	layer.msg("删除失败！",{ anim: 4, icon: 2 }, 1000);
           
                }               
                },
                'error': function (data) {
                    alert('服务器出现异常!');
                }
            });
        }, function () {
        });
    }

</script>
</div>
</div>


