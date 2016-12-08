<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="/NewManage/index.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="admin">
<div class="panel admin-panel"  style="height:96%;">
    <div class="panel-head"><strong><span class="icon-key"></span> 修改会员密码</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="" id="form1">
            <div class="form-group">
                <div class="label">
                    <label for="sitename">用户帐号：</label>
                </div>
                <div class="field">
                    <label style="line-height:33px;">
                        <%=session.getAttribute("username") %>
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">原始密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" id="oldPwd" name="oldPwd" size="50" placeholder="请输入原始密码" data-validate="required:请输入原始密码" />
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" id="userPwd" name="userPwd" size="50" placeholder="请输入新密码" />
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">确认新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="checkPwd" id="checkPwd" size="50" placeholder="请再次输入新密码" />
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" name="updateUser" id="updateUser" type="button"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
<script src="/NewPublish/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="/NewPublish/layer/skin/layer.css">
<script type="text/javascript">
    $(function () {
        $("#updateUser").click(function () {           
            if ($("#oldPwd").val() == "") {
                layer.msg("原密码不能为空", { icon: 0 }); return;
            }
            if ($("#userPwd").val() == "") {
                layer.msg("密码不能为空", { icon: 0 }); return;
            }
            if ($("#userPwd").val().length < 6)
            {
                layer.msg("密码需大于6位", { icon: 0 }); return;
            }
            var pass = $("#oldPwd").val();
            var reg = /^[A-Za-z0-9]+$/;
            if (!reg.test(pass)) {
                alert("密码只能由数字和字母组成");
                return;
            }
            if ($("#checkPwd").val() == "") {
                layer.msg("确认密码不能为空", { icon: 0 }); return;
            }
            if ($("#userPwd").val() != $("#checkPwd").val()) {
                layer.msg("两次密码不一致", { icon: 2 }); return;
            }
            //ajax发送
            $.ajax({
                url: "HomeManage!EditPwd.action",
                data: $('#form1').serialize(),
                dataType: "text",
                type: 'post',
                cache: false,
                success: function (result) {
                if (result=="incorrect") {
                    layer.msg("原密码不正确，无权修改！", { icon: 2 }, { time: 2000 }); return;
                    }
                    if (result == "yes") {
                        layer.msg("密码修改成功！", { icon: 1 }, { time: 2000 });
                        location.href = "index.jsp";
                    }
                    if (result == "no") {
                        layer.msg("密码修改失败！", { icon: 2 }, { time: 2000 }); return;
                    }
                    if (result == "error") {
                        layer.msg("服务器忙，请稍后再试！", { icon: 2 }, { time: 2000 }); return;
                    }
                }
            });
        });
    });
</script>
