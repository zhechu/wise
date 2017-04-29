<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#pwdForm").validate({
		//debug : true, // 调试模式
		rules : {
			"oldPwd" : "required",
			"newPwd" : {
				required: true,
                minlength: 6
			},
			"confirmPwd" : {
                equalTo:"#newPwd"
            }
		},
		messages : {
			"oldPwd" : e+"请输入旧密码",
			"newPwd" : {
				required: e+"请输入新密码",
				minlength: e+"新密码至少6位字符"
			},
			"confirmPwd" : {
				equalTo: e+"两次输入的密码不一致"
			}
		},
		submitHandler : function(form) {
			// 密码 base64 加密
			var oldPwd = $.trim($("#oldPwd").val());
			oldPwd = $.base64.encode(oldPwd);
			var newPwd = $.trim($("#newPwd").val());
			newPwd = $.base64.encode(newPwd);
			var data = {
				oldPwd: oldPwd,
				newPwd: newPwd
			};
			$.ajax({
				url : '${ctx }/sysManager/pwd',
				dataType : 'json',
				type : 'post',
				data : data,
				error : function() {
					$("#msg-box").addMsgBox('danger', '请求出错，请重试');
				},
				success : function(data) {
					if (data!=null && data.success) { // 保存成功，提示成功信息
						$("#msg-box").addMsgBox('success', data.msg);
					} else { // 保存失败，提示失败信息
						$("#msg-box").addMsgBox('warning', data.msg);
					}
				}
			});
			return false;
		}
	});
});
</script>
