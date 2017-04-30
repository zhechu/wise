<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/include/base.jsp"%>
<%@include file="/commons/include/tree.jsp"%>
<link href="${ctx }/res/css/content.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<c:set var="status" value="1"/>
				<h5><c:choose><c:when test="${empty sysRole }">添加</c:when><c:otherwise><c:set var="status" value="${sysRole.status }"/>编辑</c:otherwise></c:choose>角色</h5>
			</div>
			<div class="ibox-content">
				<div id="msg-box"></div>
				<form class="form-horizontal m-t" id="sysRoleForm" action="${ctx }/sysRole/save" method="post">
					<input id="id" name="id" value="${sysRole.id }" type="hidden">
					<input id="resourceIds" name="resourceIds" type="hidden">
					<div class="form-group">
						<label for="type" class="col-sm-3 control-label">* 名称：</label>
						<div class="col-sm-6">
							<input id="name" name="name" value="${sysRole.name }" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-3 control-label">描述：</label>
						<div class="col-sm-6">
							<textarea id="description" name="description"
								class="form-control" rows="3">${sysRole.description }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="status" class="col-sm-3 control-label">状态：</label>
						<div class="col-sm-6">
							<div class="input-group">
								<uform:select 
									classes="chosen-select" 
									style="width:120px;"
									name="status" 
									id="status" 
									items="${fns:getDictList('sys_role_status')}" 
									itemLabel="label" 
									itemValue="value" 
									defaultValue="${status }"
								/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">权限：</label>
						<div class="col-sm-6">
							<ul id="tree" class="ztree" style="overflow:auto;"></ul>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-3">
							<button class="btn btn-primary btn-sm" type="submit">保存</button>
						</div>
						<div class="col-sm-2">
							<a class="btn btn-white btn-sm" href="${ctx }/sysRole/list">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

<%@include file="sysRoleEditJS.jsp"%>
</body>
</html>