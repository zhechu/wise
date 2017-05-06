<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="隐藏域id"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域name"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="隐藏域默认值"%>
<%@ attribute name="classes" type="java.lang.String" description="输入框样式"%>
<%@ attribute name="style" type="java.lang.String" description="输入框样式"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="数据请求链接"%>
<%@ attribute name="disabled" type="java.lang.String" description="是否限制选择"%>
<%@ attribute name="labelId" type="java.lang.String" required="true" description="输入框id"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框name"%>
<%@ attribute name="labelDefaultValue" type="java.lang.String" description="输入框默认值"%>
<%@ attribute name="panelTitle" type="java.lang.String" required="true" description="面板title"%>

<input id="${labelId }" name="${labelName }" value="${labelDefaultValue }" class="${classes }" type="text" readonly="readonly"/>
<input id="${id }" name="${name }" value="${defaultValue }" type="hidden"/>

<!-- 选择器面板
<div id="${id }Content" style="display:none;">
	<div class="ibox float-e-margins">
        <div class="ibox-content" style="width:260px; height:300px; overflow:auto;">
        	<form id="${id }Form" role="form"  class="form-horizontal" style="padding:5px;">
	           	<div class="input-group input-group-sm">
			      <input type="text" class="form-control" name="${id }Search" id="${id }Search" placeholder="名称">
			      <span class="input-group-btn">
			        <button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
			      </span>
			    </div>
            </form>
        	<ul id="${id }Tree" class="ztree"></ul>
        </div>
    </div>
</div> -->

<script>
$(document).ready(function() {
	(function(){
		// body 动态添加选择器面板
		var panelHtml = '';
		panelHtml += '<div id="${id }Content" style="display:none;">';
		panelHtml += '<div class="ibox float-e-margins">';
		panelHtml += '<div class="ibox-content" style="width:260px; height:300px; overflow:auto;">';
		panelHtml += '<form id="${id }Form" role="form"  class="form-horizontal" style="padding:5px;">';
		panelHtml += '<div class="input-group input-group-sm">';
		panelHtml += '<input type="text" class="form-control" name="${id }Search" id="${id }Search" placeholder="名称">';
		panelHtml += '<span class="input-group-btn">';
		panelHtml += '<button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>';
		panelHtml += '</span>';
		panelHtml += '</div>';
		panelHtml += '</form>';
		panelHtml += '<ul id="${id }Tree" class="ztree"></ul>';
		panelHtml += '</div>';
		panelHtml += '</div>';
		panelHtml += '</div>';
		$("body").append(panelHtml);
		
		var treeId = "${id }Tree"; // 树 id
		// 选择器树
		var setting = {
	        view: {
	            dblClickExpand: false,
	            showLine: true,
	            selectedMulti: false,
	            fontCss: function (treeId, treeNode) {
	    			return (!!treeNode.highlight) ? {color:"#1ab394"} : {color:"#333"};
	    		}
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
	    			var treeObj = $.fn.zTree.getZTreeObj(treeId);
	    			treeObj.checkNode(treeNode, !treeNode.checked, true);
	    		}
	    	}
	    };
		// 刷新树
		function initTree(callback) {
			$.post("${url }", {}, function(data){
				$.fn.zTree.init($('#'+treeId), setting, data);
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				// 展开所有节点
		        //treeObj.expandAll(true);
				// 展开根节点
				var nodes = treeObj.getNodes();
				$.each(nodes, function(i, node){
					treeObj.expandNode(node, true);
		    	});
				// 回调
				if (callback) {
					callback();
				}
			});
		}
		
		// 是否限制选择
		var disabled = "${disabled}";
		if (disabled != "true") {
			// 鼠标选中父区域事件
			var panelLayer = null;
			$("#${labelId}").on("focus" , function() {
				panelLayer = layer.open({
					title: '${panelTitle}',  
					type: 1, 
					shade: 0,
					content: $("#${id }Content"),
					btn: ['确定', '清空', '取消'],
					yes: function(index, layero){
						// 获取选择的节点
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						var nodes = treeObj.getCheckedNodes();
						if (nodes && nodes.length>0) { // 只有选择节点后再处理
							$("#${labelId}").val(nodes[0].name);
							$("#${id}").val(nodes[0].id);
						}
						// 重置 search form
						$("#${id }Form")[0].reset();
						layer.close(panelLayer);
					},
					btn2: function(index, layero){
						$("#${labelId}").val('');
						$("#${id}").val('');
						// 重置 search form
						$("#${id }Form")[0].reset();
					},
					btn3: function(index, layero){
						// 重置 search form
						$("#${id }Form")[0].reset();
						layer.close(panelLayer);
					}
				});
				initTree();
			});
		}
		
		// 更新节点状态
		function updateNodes(name) {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var nodes = treeObj.getNodesByParamFuzzy("name", name, null);
			var data = [];
			for(var i=0, l=nodes.length; i<l; i++) {
				nodes[i].highlight = true; // 高亮
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
			$.fn.zTree.init($("#"+treeId), setting, uniqueData);
			// 展开所有节点
	        treeObj.expandAll(true);
		}
		
		// 选择器搜索
		$("#${id }Form").on("submit", function() {
			initTree(function(){
				var name = $.trim($("#${id }Search").val());
				if (name.length == 0) {
					return;
				}
		        // 更新父区域选择器
		        updateNodes(name);
			}); // 重新加载
	        return false;
		});
	})();
});
</script>