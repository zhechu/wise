<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<script>
$(document).ready(function() {
	// 区域树
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
	function initTree(callback) {
		$.post("${ctx }/sysArea/data", {}, function(data){
			$.fn.zTree.init($('#tree'), setting, data);
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			// 展开所有节点
	        //treeObj.expandAll(true);
			// 展开根节点
			var nodes = treeObj.getNodes();
			$.each(nodes, function(i, node){
				treeObj.expandNode(node, true);
	    	});
			if (callback) {
				callback();
			}
		});
	}
	//initTree();
	
	// 添加则绑定父区域事件
	<c:if test="${empty sysArea }">
		// 鼠标选中父区域事件
		var parentNameLayer = null;
		$("#parentName").on("focus" , function() {
			parentNameLayer = layer.open({
				title: '父区域',  
				type: 1, 
				shade: 0,
				content: $("#parentNameContent"),
				btn: ['确定', '清空', '取消'],
				yes: function(index, layero){
					// 获取选择的父区域
					var treeObj = $.fn.zTree.getZTreeObj("tree");
					var nodes = treeObj.getCheckedNodes();
					if (nodes && nodes.length>0) { // 只有选择节点后再处理
						$("#parentName").val(nodes[0].name);
						$("#parentId").val(nodes[0].id);
					}
					// 重置 search form
					$("#searchParentForm")[0].reset();
					layer.close(parentNameLayer);
				},
				btn2: function(index, layero){
					$("#parentName").val('');
					$("#parentId").val('');
					// 重置 search form
					$("#searchParentForm")[0].reset();
				},
				btn3: function(index, layero){
					// 重置 search form
					$("#searchParentForm")[0].reset();
					layer.close(parentNameLayer);
				}
			});
			initTree();
		});
	</c:if>

	// 更新节点状态
	function updateNodes(name) {
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getNodesByParamFuzzy("name", name, null);
		var data = [];
		for(var i=0, l=nodes.length; i<l; i++) {
			nodes[i].children = [];
			data.push(nodes[i]);
			// 获取符合条件节点的父节点
			while(nodes[i].getParentNode() != null){
				nodes[i] = nodes[i].getParentNode();
				nodes[i].children = [];
				data.push(nodes[i]);
			}
		}
		// data 去重
		data = treeObj.transformToArray(data);
		var uniqueData = [];
		$.each(data, function(i, n){
		    var isContinue = false;
		    for(var u=0, len=uniqueData.length; u<len; u++) {
		    	if (uniqueData[u].tId == n.tId) {
		    		isContinue = true;
		    		break;
		    	}
		    }
		    if (isContinue) {
		    	return true;
		    }
		    uniqueData.push(n);
		});
		uniqueData = treeObj.transformTozTreeNodes(uniqueData);
		$.fn.zTree.init($("#tree"), setting, uniqueData);
		// 展开所有节点
        treeObj.expandAll(true);
	}
	
	// 父区域选择器搜索
	$("#searchParentForm").on("submit", function() {
		initTree(function(){
			var name = $.trim($("#searchParentName").val());
			if (name.length == 0) {
				return;
			}
	        // 更新父区域选择器
	        updateNodes(name);
		}); // 重新加载
        return false;
	});
	
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#sysAreaForm").validate({
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
				url : '${ctx }/sysArea/save',
				dataType : 'json',
				type : 'post',
				data : $("#sysAreaForm").serialize(),
				error : function() {
					$("#msg-box").addMsgBox('danger', '请求出错，请重试');
				},
				success : function(data) {
					if (data!=null && data.success) { // 保存成功，提示成功信息
						$("#msg-box").addMsgBox('success', data.msg);
						// 若是添加操作，则重置表单，反之则不重置
						<c:if test="${ empty sysArea }">$("#sysAreaForm")[0].reset();</c:if>
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
