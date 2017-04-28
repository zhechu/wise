<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#sysDictForm").validate({
		//debug : true, // 调试模式
		rules : {
			"type" : "required",
			"label" : "required",
			"value" : "required",
			"status" : "required"
		},
		messages : {
			"type" : e + "请输入类型",
			"label" : e + "请输入标签",
			"value" : e + "请输入值",
			"status" : e + "请选择状态"
		},
		submitHandler : function(form) {
			$.ajax({
				url : '${ctx}/sysDict/save',
				dataType : 'json',
				type : 'post',
				data : $("#sysDictForm").serialize(),
				error : function() {
					$("#msg-box").addMsgBox('danger', '请求出错，请重试');
				},
				success : function(data) {
					if (data!=null && data.success) { // 保存成功，提示成功信息
						$("#msg-box").addMsgBox('success', data.msg);
						// 若是添加操作，则重置表单，反之则不重置
						<c:if test="${ empty sysDict }">$("#sysDictForm")[0].reset();</c:if>
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
