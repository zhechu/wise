<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>

<script>
$(document).ready(function () {
	var exampleTable = $('#roleTable').bootstrapTable({
	    url: '${ctx}/sysRole/list',
	    queryParamsType: '', // 默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort 设置为 '' 在这种情况下传给服务器的参数为：pageSize,pageNumber
	    //method: "get", // 请求方式
	    //contentType: 'application/json', // 发送到服务器的数据编码类型，配合 get 方法使用
	    method: "post", // 请求方式
	    contentType: "application/x-www-form-urlencoded", // 发送到服务器的数据编码类型，配合 post 方法使用
	    dataType: "json", // 服务器返回的数据类型
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
	        field: 'remarks',
	        title: '备注',
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
	        field: 'creator.name',
	        title: '创建人',
	    }, {
	        field: 'createAt',
	        title: '创建时间',
	        formatter:function(value,row,index){
	            return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
	        }
	    },{
	        field: 'id',
	        title: '操作',
	        align: 'center',
	        formatter:function(value,row,index){
	        	var operations = '';
	        	<shiro:hasPermission name="sys:role:update">
	        		operations = '<a href="${ctx}/sysRole/edit?id='+value+'">编辑</a>';
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
    	var sysRoles = $('#roleTable').bootstrapTable('getSelections');
    	if (sysRoles==null || sysRoles.length==0) {
    		$("#msg-box").addMsgBox('info', "请选择要删除的数据");
    		return;
    	}
    	var ids = [];
    	$.each(sysRoles, function(i, n){
    		ids.push(n.id);
    	});
    	ids = ids.join(",");
    	top.layer.confirm('确定要删除吗', {icon: 3, title:'提示'}, function(index){
    		$.ajax({
	    		url : '${ctx}/sysRole/delete',
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
	    				$('#roleTable').bootstrapTable('refresh');
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
