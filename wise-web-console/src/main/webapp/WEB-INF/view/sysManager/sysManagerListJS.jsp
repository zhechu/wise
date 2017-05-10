<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function () {
	// 组织机构主键
	var orgId = 0;
	
	// 组织机构树
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
        		orgId = treeNode.id;
        		// 刷新用户表
        		$('#managerTable').bootstrapTable('refresh');
            }
        }
    };
	// 刷新树
	function initTree(callback) {
		$.post("${ctx }/sysOrg/validData", {}, function(data){
			$.fn.zTree.init($('#tree'), setting, data);
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			// 展开所有节点
	        //treeObj.expandAll(true);
			// 展开根节点
			var nodes = treeObj.getNodes();
			$.each(nodes, function(i, node){
				treeObj.expandNode(node, true);
	    	});
			// 执行回调函数
			if (callback) {
				callback();
			}
		});
	}
	function initTreeCallback() {
		// 默认选中第一个根节点
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getNodes();
		if (nodes != null && nodes.length > 0) {
			// 模拟点击第一个根节点
			$("#"+nodes[0].tId+"_a").trigger("click");
		}
	}
	initTree(initTreeCallback);
	
	// 刷新树和表格
	function initTreeAndTable(nodeId){
		initTree();
		// 选中某个节点
		if (!nodeId || nodeId==0) {
			orgId = 0;
			$('#managerTable').bootstrapTable('refresh');
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
	
	// 刷新按钮事件
	$("#refreshBtn").on("click", function() {
		initTreeAndTable(orgId);
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
			pageSize: params.pageSize,   //页面大小
			pageNum: params.pageNumber,  //页码
			sortName: params.sortName,
			sortOrder: params.sortOrder,
			orgId: orgId, // 组织机构主键
			userName: $.trim($("#userName").val()),
			name: $.trim($("#name").val()),
			email: $.trim($("#email").val()),
			sysRoleId: $("#sysRoleId").val(),
			status: $("#status").val(),
			createdAtStart: $("#createdAtStart").val(),
			createdAtEnd: $("#createdAtEnd").val()
		};
	}
	// 表格的高度
	var doc_height = $(document.body).height();
	var table_height = doc_height - 145 - 53 - 52;
	var exampleTable = $('#managerTable').bootstrapTable({
	    url: '${ctx}/sysManager/list',
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
	    pageNumber: 1, // 首页页码
	    totalRows: 0,
	    pageSize: 20, // 页面数据条数
	    pageList: [10, 20, 30, 50, 100], // 设置可供选择的页面数据条数
	    paginationPreText: '上一页',
	    paginationNextText: '下一页',
	    showColumns: true, // 是否显示内容列下拉框
	    clickToSelect: true, // 点击内容，自动选择rediobox 和 checkbox
	    height: table_height, // 表格高度
	    sortable: true, // 是否启用排序
	    sortName: 'modifiedAt', // 默认排序字段
	    sortOrder: 'desc', // 默认排序方式
	    silentSort: false, // 设置为 false 将在点击分页按钮时，自动记住排序项
	    idField : "id", // 指定主键列
	    uniqueId: "id", // 每行唯一标识
	    selectItemName: 'checked', // radio or checkbox 的字段名
	    columns: [{
	        field: 'checked',
	        checkbox: true
	    }, {
	        field: 'company.name',
	        title: '归属公司',
	    }, {
	        field: 'dept.name',
	        title: '归属部门',
	    }, {
	        field: 'userName',
	        title: '用户名',
	        sortable: true
	    }, {
	        field: 'name',
	        title: '姓名',
	    }, {
	        field: 'sexName',
	        title: '性别',
	    }, {
	        field: 'phone',
	        title: '电话',
	    }, {
	        field: 'email',
	        title: '邮箱',
	    }, {
	        field: 'sysRoleList',
	        title: '角色',
	        formatter:function(value,row,index){
	        	var roles = '';
	        	$.each(value, function(i, n){
        			roles += n.name + ',';
        		});
	        	var len = roles.length;
	        	if (len > 0) {
	        		roles = roles.substring(0, len-1);
	        	}
	            return roles;
	        }
	    }, {
	        field: 'statusName',
	        title: '状态',
	        align: 'center',
	        formatter:function(value,row,index){
	        	// 启用字体为绿色，禁用字体为黄色，其它情况不变
	        	if ("启用" == value) {
	        		value = '<span style="color:#1ab394;">'+value+'</span>';
	        	} else if ("禁用" == value) {
	        		value = '<span style="color:#ec971f;">'+value+'</span>';
	        	}
	            return value;
	        }
	    }, {
	        field: 'registIp',
	        title: '注册IP',
	    }, {
	        field: 'creator.name',
	        title: '创建人',
	    }, {
	        field: 'createdAt',
	        title: '创建时间',
	        formatter:function(value,row,index){
	            return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
	        },
	        sortable: true
	    },{
	        field: 'id',
	        title: '操作',
	        align: 'center',
	        formatter:function(value,row,index){
	        	var operations = '';
	        	<shiro:hasPermission name="sys:manager:update">
	        		operations = '<a href="${ctx}/sysManager/edit?id='+value+'">编辑</a>';
		        </shiro:hasPermission>
	            return operations;
	        }
	    }],
	    pagination: true,
	    // 加载提示信息
		formatLoadingMessage: function () {
			return "请稍等，正在加载中...";
		},
		// 匹配提示信息
		formatNoMatches: function () {
	      return '没有匹配的记录';
	    }
	});
	
	// 日期
	$("#created input").datetimepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        format: 'yyyy-mm-dd hh:ii'//日期格式，详见 http://www.bootcss.com/p/bootstrap-datetimepicker
    });
	
	// 列表查询折叠事件
    var collapse_link_count = 0;
    $("a.collapse-link").on("click", function(){
        if (++collapse_link_count % 2 == 1) { // 收起
        	table_height += 170;
        } else { // 展开
        	table_height -= 170;
        }
        $('#managerTable').bootstrapTable('resetView',{height:table_height});
    });
    
    // 查询
    $("#searchForm").on("submit", function(){
    	$('#managerTable').bootstrapTable('refresh');
    	 return false;
    });
    
    // 删除
    $("#delBtn").on("click", function(){
    	var sysManagers = $('#managerTable').bootstrapTable('getSelections');
    	if (sysManagers==null || sysManagers.length==0) {
    		$("#msg-box").addMsgBox('info', "请选择要删除的数据");
    		return;
    	}
    	var ids = [];
    	$.each(sysManagers, function(i, n){
    		ids.push(n.id);
    	});
    	ids = ids.join(",");
    	top.layer.confirm('确定要删除吗', {icon: 3, title:'提示'}, function(index){
    		$.ajax({
	    		url : '${ctx}/sysManager/delete',
	    		dataType : 'json',
	    		type : 'post',
	    		data : {ids : ids},
	    		error : function() {
	    			$("#msg-box").addMsgBox('danger', '请求出错，请重试');
	    		},
	    		success : function(data) {
	    			if (data!=null && data.success) { // 删除成功，提示成功信息
	    				$("#msg-box").addMsgBox('success', data.msg);
	    				// 刷新表格
	    				$('#managerTable').bootstrapTable('refresh');
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
