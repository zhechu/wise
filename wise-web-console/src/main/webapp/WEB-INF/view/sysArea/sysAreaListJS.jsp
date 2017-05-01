<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function(){
	// 区域父主键
	var parentId = 0;
	
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
        callback: {
        	beforeClick: function(treeId, treeNode) {
        		// 清除所有节点选中样式
        		var treeObj = $.fn.zTree.getZTreeObj("tree");
        		var nodes = treeObj.transformToArray(treeObj.getNodes());
        		$.each(nodes, function(i, n){
        			$("#"+n.tId+"_a").removeClass("curSelectedNode");
      			});
        	},
        	onClick: function(event, treeId, treeNode) {
        		parentId = treeNode.id;
        		// 刷新子区域
        		$('#areaTable').bootstrapTable('refresh');
            }
        }
    };
	// 刷新树
	function initTree() {
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
		});
	}
	initTree();
	
	// 刷新树和表格
	function initTreeAndTable(nodeId){
		initTree();
		// 选中某个节点
		if (!nodeId || nodeId==0) {
			parentId = 0;
			$('#areaTable').bootstrapTable('refresh');
		} else {
			// 模拟点击树节点
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var parentNode = treeObj.getNodeByParam("id", nodeId);
	        $("#"+parentNode.tId+"_a").click();
	        // 当前节点样式
	        window.setTimeout(function () {
		        $("#"+parentNode.tId+"_a").addClass("curSelectedNode");
	        }, 200);
		}
	}
	
	// 回到根节点按钮事件
	$("#backRootBtn").on("click", function() {
		initTree();
		parentId = null;
		// 刷新子区域
		$('#areaTable').bootstrapTable('refresh');
	});
	
	// 刷新按钮事件
	$("#refreshBtn").on("click", function() {
		initTreeAndTable(parentId);
	});

    // 初始化高度
    var doc_height = $(document.body).height();
	var panel_height = doc_height - 20;
	$("#left").height(panel_height);
	$("#left .ibox-content").height($("#left").height()-80);
	$("#right").height(panel_height);
    
    var toggle_count = 0;
    $(".one").hide();
    $(".press").on("click", function(){
        $("#left").toggleClass("collapse");
        if (++toggle_count % 2 == 1) { // 折叠
        	$(".one").show();
            $(".two").hide();
        } else { // 展开
        	$(".one").hide();
            $(".two").show();
        }
        $("#right").toggleClass("col-sm-12 col-sm-10");
    });
    
 	// 请求参数
	function queryParams(params) {
		return {
			parentId: parentId
		};
	}
    $('#areaTable').bootstrapTable({
	    url: '${ctx }/sysArea/list',
	    queryParamsType: '', // 默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort 设置为 '' 在这种情况下传给服务器的参数为：pageSize,pageNumber
	    //method: "get", // 请求方式
	    //contentType: 'application/json', // 发送到服务器的数据编码类型，配合 get 方法使用
	    method: "post", // 请求方式
	    contentType: "application/x-www-form-urlencoded", // 发送到服务器的数据编码类型，配合 post 方法使用
	    dataType: "json", // 服务器返回的数据类型
	    queryParams: queryParams, // 自定义查询参数
	    striped: true, // 设置表格内容斑马纹
	    sidePagination: 'server', // 服务端模式
	    cache: false, // 缓存
	    locale:"zh-CN", // 国际化
	    toolbar: "#toolbar", // 工具栏
	    search: false, // 是否启用搜索框
	    showColumns: true, // 是否显示内容列下拉框
	    clickToSelect: true, // 点击内容，自动选择rediobox 和 checkbox
	    idField : "id", // 指定主键列
	    uniqueId: "id", // 每行唯一标识
	    selectItemName: 'checked', // radio or checkbox 的字段名
	    columns: [{
	        field: 'checked',
	        checkbox: true
	    }, {
	        field: 'name',
	        title: '名称'
	    }, {
	        field: 'code',
	        title: '编码',
	    }, {
	        field: 'typeName',
	        title: '类型'
	    }, {
	        field: 'remarks',
	        title: '备注',
	    }, {
	        field: 'sort',
	        title: '排序'
	    },{
	        field: 'id',
	        title: '操作',
	        align: 'center',
	        formatter:function(value,row,index){
	        	var operations = '';
	        	<shiro:hasPermission name="sys:area:update">
	        		operations = '<a href="${ctx }/sysArea/edit?id='+value+'">编辑</a>';
		        </shiro:hasPermission>
	            return operations;
	        }
	    }],
	    // 加载提示信息
		formatLoadingMessage: function () {
			return "请稍等，正在加载中...";
		},
		// 匹配提示信息
		formatNoMatches: function () {
	      return '没有匹配的记录';
	    }
	});
    
    // 删除
    $("#delBtn").on("click", function(){
    	var sysAreas = $('#areaTable').bootstrapTable('getSelections');
    	if (sysAreas==null || sysAreas.length==0) {
    		$("#msg-box").addMsgBox('info', "请选择要删除的数据");
    		return;
    	}
    	var ids = [];
    	$.each(sysAreas, function(i, n){
    		ids.push(n.id);
    	});
    	ids = ids.join(",");
    	top.layer.confirm('确定要删除吗', {icon: 3, title:'提示'}, function(index){
    		$.ajax({
	    		url : '${ctx }/sysArea/delete',
	    		dataType : 'json',
	    		type : 'post',
	    		data : {ids : ids},
	    		error : function() {
	    			$("#msg-box").addMsgBox('danger', '请求出错，请重试');
	    		},
	    		success : function(data) {
	    			if (data!=null && data.success) { // 删除成功，提示成功信息
	    				$("#msg-box").addMsgBox('success', data.msg);
		    			// 刷新树和表格	
		    			initTreeAndTable(parentId);
	    			} else { // 删除失败，提示失败信息
	    				$("#msg-box").addMsgBox('warning', data.msg);
	    			}
	    		}
	    	});
    		top.layer.close(index);
    	});
    });
});
</script>
