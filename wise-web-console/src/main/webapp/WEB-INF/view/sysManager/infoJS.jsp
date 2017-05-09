<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#infoForm").validate({
		//debug : true, // 调试模式
		rules : {
			"name" : {
				required: true,
                minlength: 2
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
			"name" : {
				required: e+"请输入姓名",
				minlength: e+"姓名必须2个字符以上"
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
		submitHandler : function(form) {
			var form = new FormData($("#infoForm")[0]);
			// 头像 base64 data
			var picImg = $("#picImg").attr("src");
			// 检查是否需提交头像
			if (picImg.indexOf("data:image") == 0) { // 表明是 base64 data 图像格式
				form.append("picImg", picImg);
			}
			$.ajax({
				url : '${ctx }/sysManager/info',
				dataType : 'json',
				type : 'post',
				data:form,  
			    cache: false,
			    processData: false,  
			    contentType: false,
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
