<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function() {
	// 资源树
	var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
        	key: {
            	url: ""
            },
        	simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: ""
            }
        },
        check: {
    		enable: true,
    		chkStyle: "radio",
    		radioType: "all"
    	},
    	callback: {
    		onClick: function (event, treeId, treeNode) {
    			// 单击选中或取消选中
    			var treeObj = $.fn.zTree.getZTreeObj("tree");
    			treeObj.checkNode(treeNode, !treeNode.checked, true);
    		}
    	}
    };
	// 刷新树
	function initTree() {
		$.post("${ctx }/sysResource/data", {}, function(data){
			$.fn.zTree.init($('#tree'), setting, data);
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			// 展开所有节点
	        //treeObj.expandAll(true);
			// 展开根节点
			var nodes = treeObj.getNodes();
			$.each(nodes, function(i, node){
				treeObj.expandNode(node, true);
	    	});
		});
	}
	//initTree();
	
	// 添加则绑定父资源事件
	<c:if test="${empty sysResource }">
		// 鼠标选中父资源事件
		var parentNameLayer = null;
		$("#parentName").on("focus" , function() {
			parentNameLayer = layer.open({
				title: '父资源',  
				type: 1, 
				shade: 0,
				content: $("#parentNameContent"),
				btn: ['确定', '清空', '取消'],
				yes: function(index, layero){
					// 获取选择的父资源
					var treeObj = $.fn.zTree.getZTreeObj("tree");
					var nodes = treeObj.getCheckedNodes();
					if (nodes && nodes.length>0) { // 只有选择节点后再处理
						$("#parentName").val(nodes[0].name);
						$("#parentId").val(nodes[0].id);
					}
					layer.close(parentNameLayer);
				},
				btn2: function(index, layero){
					$("#parentName").val('');
					$("#parentId").val('');
				},
				btn3: function(index, layero){
					layer.close(parentNameLayer);
				}
			});
			initTree();
		});
	</c:if>
	
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#sysResourceForm").validate({
		//debug : true, // 调试模式
		rules : {
			"name" : {
				required: true,
                minlength: 2
			}
		},
		messages : {
			"name" : {
				required: e+"请输入名称",
				minlength: e+"名称至少2个字符"
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
