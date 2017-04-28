<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	// 权限树
	var setting = {
        check: {
            enable: true
        },
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: ""
            }
        }
    };
	$.post("${ctx }/sysResource/data", {}, function(data){
		$.fn.zTree.init($('#tree'), setting, data);
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		// 展开所有节点
        treeObj.expandAll(true);
		// 若是编辑，则回显
        <c:forEach var="sysResourceId" items="${sysRole.sysResourceIdList }">
        	var node = treeObj.getNodeByParam("id","${sysResourceId}");
        	treeObj.checkNode(node, true);
    	</c:forEach>
	});
	
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#sysRoleForm").validate({
		//debug : true, // 调试模式
		rules : {
			"name" : "required",
			"status" : {
				required : true,
				min : 0
			}
		},
		messages : {
			"name" : e + "请输入名称",
			"status" : {
				required : e + "请选择状态",
				min : e + "请选择状态"
			}
		},
		submitHandler : function(form) {
			// 获取选择的权限节点
			var resourceIds = [];
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getCheckedNodes(true);
			$.each(nodes, function(i, n){
				resourceIds.push(n.id);
			});
			$("#resourceIds").val(resourceIds.join(","));
			$.ajax({
				url : '${ctx }/sysRole/save',
				dataType : 'json',
				type : 'post',
				data : $("#sysRoleForm").serialize(),
				error : function() {
					$("#msg-box").addMsgBox('danger', '请求出错，请重试');
				},
				success : function(data) {
					if (data!=null && data.success) { // 保存成功，提示成功信息
						$("#msg-box").addMsgBox('success', data.msg);
						// 若是添加操作，则重置表单，反之则不重置
						<c:if test="${ empty sysRole }">$("#sysRoleForm")[0].reset();</c:if>
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
