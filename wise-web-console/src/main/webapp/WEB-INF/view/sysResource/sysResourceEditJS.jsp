<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#sysResourceForm").validate({
		//debug : true, // 调试模式
		rules : {
			"name" : {
				required: true,
                minlength: 2,
                maxlength: 50
			},
			"permission" : {
                maxlength: 50
			},
			"url" : {
                maxlength: 200
			},
			"icon" : {
                maxlength: 50
			},
			"remarks" : {
                maxlength: 200
			}
		},
		messages : {
			"name" : {
				required: e+"请输入名称",
				minlength: e+"名称至少2个字符",
				maxlength: e+"名称不能大于50个字符"
			},
			"permission" : {
                maxlength: e+"权限标识不能大于50个字符"
			},
			"url" : {
                maxlength: e+"URL不能大于200个字符"
			},
			"icon" : {
                maxlength: e+"图标不能大于50个字符"
			},
			"remarks" : {
                maxlength: e+"备注不能大于200个字符"
			}
		},
		submitHandler : function(form) {
			$.ajax({
				url : '${ctx }/sysResource/save',
				dataType : 'json',
				type : 'post',
				data : $("#sysResourceForm").serialize(),
				error : function() {
					$("#msg-box").addMsgBox('danger', '请求出错，请重试');
				},
				success : function(data) {
					if (data!=null && data.success) { // 保存成功，提示成功信息
						$("#msg-box").addMsgBox('success', data.msg);
						// 若是添加操作，则重置表单，反之则不重置
						<c:if test="${ empty sysResource }">$("#sysResourceForm")[0].reset();</c:if>
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
