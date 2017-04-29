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
				<c:set var="type" value="0"/>
				<h5><c:choose><c:when test="${empty sysResource }">添加</c:when><c:otherwise><c:set var="status" value="${sysResource.status }"/><c:set var="type" value="${sysResource.type }"/>编辑</c:otherwise></c:choose>资源</h5>
			</div>
			<div class="ibox-content">
				<div id="msg-box"></div>
				<form class="form-horizontal m-t" id="sysResourceForm" action="${ctx }/sysResource/save" method="post">
					<input id="id" name="id" value="${sysResource.id }" type="hidden">
					<div class="form-group">
						<label for="parentName" class="col-sm-3 control-label">父资源：</label>
						<div class="col-sm-6">
							<input id="parentName" name="parentName" value="${sysResource.parentName }" class="form-control" type="text" readonly="readonly">
							<input id="parentId" name="parentId" value="${sysResource.parentId }" type="hidden">
							<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> <c:choose><c:when test="${empty sysResource }"><spring:message code="sys.resource.add.parent.hint" /></c:when><c:otherwise><spring:message code="sys.resource.update.parent.hint" /></c:otherwise></c:choose></span>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">名称：</label>
						<div class="col-sm-6">
							<input id="name" name="name" value="${sysResource.name }" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-3 control-label">类型：</label>
						<div class="col-sm-6">
							<div class="input-group">
								<uform:select 
									classes="chosen-select" 
									style="width:120px;"
									name="type" 
									id="type" 
									items="${fns:getDictList('sys_resource_type')}" 
									itemLabel="label" 
									itemValue="value" 
									hintLabel="请选择状态"
									defaultValue="${type }"
								/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="permission" class="col-sm-3 control-label">权限标识：</label>
						<div class="col-sm-6">
							<input id="permission" name="permission" value="${sysResource.permission }" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-3 control-label">描述：</label>
						<div class="col-sm-6">
							<textarea id="description" name="description"
								class="form-control" rows="3">${sysResource.description }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-3 control-label">URL：</label>
						<div class="col-sm-6">
							<input id="url" name="url" value="${sysResource.url }" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="icon" class="col-sm-3 control-label">图标：</label>
						<div class="col-sm-6">
							<input id="icon" name="icon" value="${sysResource.icon }" class="form-control" type="text">
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
									items="${fns:getDictList('sys_resource_status')}" 
									itemLabel="label" 
									itemValue="value" 
									hintLabel="请选择状态"
									defaultValue="${status }"
								/>
								<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> <spring:message code="sys.resource.status.hint" /></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="sort" class="col-sm-3 control-label">排序：</label>
						<div class="col-sm-6">
							<div class="input-group spinner" data-trigger="spinner">
								<input id="sort" name="sort" value="${sysResource.sort }" type="text"
									class="form-control text-center" value="0" data-max="1000"
									data-min="0" data-step="10">
								<div class="input-group-addon">
									<a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up" style="margin-top:-2px;"></i></a> 
									<a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-caret-down" style="margin-top:-2px;"></i></a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-3">
							<button class="btn btn-primary btn-sm" type="submit">保存</button>
						</div>
						<div class="col-sm-2">
							<a class="btn btn-white btn-sm" href="${ctx }/sysResource/list">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

<!-- 父资源面板内容 -->
<div id="parentNameContent" style="display:none;">
	<div class="ibox float-e-margins">
        <div class="ibox-content" style="width:260px; height:300px; overflow:auto;">
        	<ul id="tree" class="ztree"></ul>
        </div>
    </div>
</div>
<%@include file="sysResourceEditJS.jsp"%>
</body>
</html>