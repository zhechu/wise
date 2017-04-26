<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#loginForm").validate({
		//debug : true, // 调试模式
		rules : {
			"userName" : "required",
			"pwd" : "required"
		},
		messages : {
			"userName" : e+"请输入用户名",
			"pwd" : e+"请输入密码"
		},
		submitHandler : function(form) {
			// 若密码不为空，密码 base64 加密
			var pwd = $.trim($("#pwd").val());
			pwd = $.base64.encode(pwd);
			var data = {
				userName: $("#userName").val(),
				pwd: pwd
			};
			$.ajax({
				url : '${ctx}/login.do',
				dataType : 'json',
				type : 'post',
				data : data,
				error : function() {
					$("#msg-box").addMsgBox('danger', '请求出错，请重试');
				},
				success : function(data) {
					if (data!=null && data.success) { // 登录成功，则跳转到主页
						window.location.href = "${ctx}/console/main.do";
					} else { // 登录失败，提示失败信息
						$("#msg-box").addMsgBox('warning', data.msg);
					}
				}
			});
			return false;
		}
	});
});
</script>
