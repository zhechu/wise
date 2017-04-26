<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function () {
	// 请求参数
	function queryParams(params) {
		return {
			pageSize: params.pageSize,   //页面大小
			pageNumber: params.pageNumber,  //页码
			sortName: params.sortName,
			sortOrder: params.sortOrder,
			type: $("#type").val(),
			status: $("#status").val()
		};
	}
	// 表格的高度
	var doc_height = $(document.body).height();
	var table_height = doc_height - 145;
	var exampleTable = $('#dictTable').bootstrapTable({
	    url: '/sysDict/list.do',
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
	    sortName: 'type', // 默认排序字段
	    sortOrder: 'asc', // 默认排序方式
	    silentSort: false, // 设置为 false 将在点击分页按钮时，自动记住排序项
	    idField : "id", // 指定主键列
	    uniqueId: "id", // 每行唯一标识
	    selectItemName: 'checked', // radio or checkbox 的字段名
	    columns: [{
	        field: 'checked',
	        checkbox: true
	    }, {
	        field: 'type',
	        title: '类型',
	        sortable: true
	    }, {
	        field: 'label',
	        title: '标签',
	    }, {
	        field: 'value',
	        title: '键值',
	    }, {
	        field: 'description',
	        title: '描述',
	    }, {
	        field: 'sort',
	        title: '排序',
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
	    },{
	        field: 'id',
	        title: '操作',
	        align: 'center',
	        formatter:function(value,row,index){
	        	var operations = '';
	        	<shiro:hasPermission name="sys:dict:update">
	        		operations = '<a href="/sysDict/edit.do?id='+value+'">编辑</a>';
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
	
	// 列表查询折叠事件
    var collapse_link_count = 0;
    $("a.collapse-link").on("click", function(){
        if (++collapse_link_count % 2 == 1) { // 收起
        	table_height += 65;
        } else { // 展开
        	table_height -= 65;
        }
        $('#dictTable').bootstrapTable('resetView',{height:table_height});
    });
    
    // 查询按钮事件
    $("#searchBtn").on("click", function(){
    	 $('#dictTable').bootstrapTable('refresh');
    });
    
    // 删除
    $("#delBtn").on("click", function(){
    	var sysDicts = $('#dictTable').bootstrapTable('getSelections');
    	if (sysDicts==null || sysDicts.length==0) {
    		$("#msg-box").addMsgBox('info', "请选择要删除的数据");
    		return;
    	}
    	var ids = [];
    	$.each(sysDicts, function(i, n){
    		ids.push(n.id);
    	});
    	ids = ids.join(",");
    	top.layer.confirm('确定要删除吗', {icon: 3, title:'提示'}, function(index){
    		$.ajax({
	    		url : '/sysDict/delete.do',
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
	    				$('#dictTable').bootstrapTable('refresh');
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
