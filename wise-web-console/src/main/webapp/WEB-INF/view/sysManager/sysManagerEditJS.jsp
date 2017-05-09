<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#sysManagerForm").validate({
		//debug : true, // 调试模式
		rules : {
			"companyName" : "required",
			"deptName" : "required",
			"userName" : {
				required: true,
                minlength: 2,
                maxlength: 50
			},
			"pwd" : {
                minlength: 6,
                maxlength: 50
			},
			"confirmPwd" : {
                equalTo:"#pwd"
            },
			"name" : {
				required: true,
                minlength: 2,
                maxlength: 50
			},
			"phone" : {
				required: true,
				phone: true
			},
            "email" : {
                required: true,
                email: true
            }
		},
		messages : {
			"companyName" : e+"请选择归属公司",
			"deptName" : e+"请选择归属部门",
			"userName" : {
				required: e+"请输入用户名",
				minlength: e+"用户名不能小于2个字符",
				maxlength: e+"用户名不能大于50个字符"
			},
			"pwd" : {
				minlength: e+"密码不能小于6位字符",
				maxlength: e+"密码不能大于50位字符"
			},
			"confirmPwd" : {
				equalTo: e+"两次输入的密码不一致"
			},
			"name" : {
				required: e+"请输入姓名",
				minlength: e+"姓名不能小于2个字符",
				maxlength: e+"姓名不能大于50个字符"
			},
			"phone" : {
				required: e+"请输入电话",
				phone: e+"电话格式有误"
			},
			"email" : {
                required: e+"请输入邮箱",
                email: e+"邮箱格式有误"
            }
		},
		submitHandler : function() {
			// 若密码不为空，密码 base64 加密
			var pwd = $.trim($("#pwd").val());
			if (pwd && pwd!='') {
				$("#pwd").val($.base64.encode(pwd));
			}
			var form = new FormData($("#sysManagerForm")[0]);
			// 头像 base64 data
			var picImg = $("#picImg").attr("src");
			// 检查是否需提交头像
			if (picImg.indexOf("data:image") == 0) { // 表明是 base64 data 图像格式
				form.append("picImg", picImg);
			}
			$.ajax({
				url : '${ctx }/sysManager/save',
				dataType : 'json',
				type : 'post',
				data:form,  
			    cache: false,
			    processData: false,  
			    contentType: false,
				error : function() {
					$("#msg-box").addMsgBox('danger', '请求出错，请重试');
					// 重置密码
					$("#pwd").val('');
					$("#confirmPwd").val('');
				},
				success : function(data) {
					if (data!=null && data.success) { // 保存成功，提示成功信息
						$("#msg-box").addMsgBox('success', data.msg);
						// 若是添加操作，则重置表单，反之则不重置
						<c:if test="${ empty sysManager }">$("#sysManagerForm")[0].reset();</c:if>
					} else { // 保存失败，提示失败信息
						$("#msg-box").addMsgBox('warning', data.msg);
					}
					// 重置密码
					$("#pwd").val('');
					$("#confirmPwd").val('');
				}
			});
			return false;
		}
	});
});
</script>
