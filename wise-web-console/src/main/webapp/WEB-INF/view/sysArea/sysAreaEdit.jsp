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
				<c:set var="type" value="0"/>
				<h5><c:choose><c:when test="${empty sysArea }">添加</c:when><c:otherwise><c:set var="type" value="${sysArea.type }"/>编辑</c:otherwise></c:choose>区域</h5>
			</div>
			<div class="ibox-content">
				<div id="msg-box"></div>
				<form class="form-horizontal m-t" id="sysAreaForm" action="${ctx }/sysArea/save" method="post">
					<input id="id" name="id" value="${sysArea.id }" type="hidden">
					<div class="form-group">
						<label for="parentName" class="col-sm-3 control-label">父区域：</label>
						<div class="col-sm-6">
							<input id="parentName" name="parentName" value="${sysArea.parent.name }" class="form-control" type="text" readonly="readonly">
							<input id="parentId" name="parent.id" value="${sysArea.parent.id }" type="hidden">
							<span class="help-block m-b-none"><c:choose><c:when test="${empty sysArea }"><spring:message code="sys.area.add.parent.hint" /></c:when><c:otherwise><spring:message code="sys.area.update.parent.hint" /></c:otherwise></c:choose></span>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">* 名称：</label>
						<div class="col-sm-6">
							<input id="name" name="name" value="${sysArea.name }" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="code" class="col-sm-3 control-label">编码：</label>
						<div class="col-sm-6">
							<input id="code" name="code" value="${sysArea.code }" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-3 control-label">类型：</label>
						<div class="col-sm-6">
							<div class="input-group">
								<uform:select 
									classes="chosen-select" 
									style="width:200px;"
									name="type" 
									id="type" 
									items="${fns:getDictList('sys_area_type')}" 
									itemLabel="label" 
									itemValue="value" 
									defaultValue="${type }"
								/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="remarks" class="col-sm-3 control-label">备注：</label>
						<div class="col-sm-6">
							<textarea id="remarks" name="remarks"
								class="form-control" rows="3">${sysArea.remarks }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="sort" class="col-sm-3 control-label">排序：</label>
						<div class="col-sm-6">
							<div class="input-group spinner" data-trigger="spinner">
								<input id="sort" name="sort" value="${sysArea.sort }" type="text"
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
							<a class="btn btn-white btn-sm" href="${ctx }/sysArea/list">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

<!-- 父区域面板内容 -->
<div id="parentNameContent" style="display:none;">
	<div class="ibox float-e-margins">
        <div class="ibox-content" style="width:260px; height:300px; overflow:auto;">
        	<ul id="tree" class="ztree"></ul>
        </div>
    </div>
</div>
<%@include file="sysAreaEditJS.jsp"%>
</body>
</html>