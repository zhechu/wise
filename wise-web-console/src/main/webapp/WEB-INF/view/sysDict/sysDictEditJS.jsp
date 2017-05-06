<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	// 设置值
	function setValue(type) {
		$.ajax({
			url : '${ctx}/sysDict/value',
			dataType : 'json',
			type : 'get',
			data : {type: type},
			success : function(data) {
				if (data!=null && data.success) {
					$("#value").val(data.data);
				}
			}
		});
	}
	// 添加时，类型级联值
	<c:if test="${empty sysDict }">
		setValue($("#type").val());
		// 类型选择级联改变值
		$("#type").on("change", function(){
			setValue($(this).val());
		});
	</c:if>
	
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
