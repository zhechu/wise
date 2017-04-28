<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/include/base.jsp"%>
<link href="${ctx }/res/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctx }/res/css/content.css" rel="stylesheet">
<style type="text/css">
	.form-group {
		margin-bottom: 0;
	}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-table">
				<div id="msg-box"></div>
				<div id="toolbar">
			        <shiro:hasPermission name="sys:role:add">
			        	<a href="${ctx }/sysRole/edit" class="btn btn-info btn-sm"><i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;添加</a>
			        </shiro:hasPermission>
			        <shiro:hasPermission name="sys:role:delete">
			        	<a id="delBtn" href="javascript:;" class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删除</a>
			        </shiro:hasPermission>
			    </div>
			    <table id="roleTable" class="table table-hover">
					
			    </table>
			</div>
		</div>
	</div>

<script src="${ctx }/res/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx }/res/js/content.min.js?v=1.0.0"></script>

<%@include file="sysRoleListJS.jsp"%>
</body>
</html>