<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/include/base.jsp"%>
<%@include file="/commons/include/cropper.jsp"%>
<link href="${ctx }/res/css/content.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>个人资料</h5>
			</div>
			<div class="ibox-content">
				<div id="msg-box"></div>
				<form class="form-horizontal m-t" id="infoForm" action="${ctx }/sysManager/info" method="post">
					<c:set var="portraitPic" value="${ctx }/res/img/default_user_image.png"/>
					<c:if test="${ ! empty sysManager.portraitPic }"><c:set var="portraitPic" value="${sysManager.portraitPic }"/></c:if>
					<div class="form-group">
						<label for="portraitPic" class="col-sm-3 control-label">头像：</label>
						<div class="col-sm-6">
                            <uform:portrait id="picImg" defaultValue="${portraitPic }" panelTitle="头像"/>
						</div>
					</div>
					<div class="form-group">
						<label for="userName" class="col-sm-3 control-label">用户名：</label>
						<div class="col-sm-6">
							<p class="form-control-static">${sysManager.userName }</p>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">* 姓名：</label>
						<div class="col-sm-6">
							<input id="name" name="name" value="${sysManager.name }" class="form-control" type="text" data-toggle="tooltip" data-placement="right" title="<spring:message code="sys.manager.info.name.length" />">
						</div>
					</div>
					<div class="form-group">
						<label for="label" class="col-sm-3 control-label">性别：</label>
						<div class="col-sm-6">
							<uform:radio 
								items="${fns:getDictList('sex')}" 
								name="sex" 
								itemLabel="label" 
								itemValue="value"
								defaultValue="${sysManager.sex }"
								classes="radio radio-success radio-inline"
							/>
						</div>
					</div>
					<div class="form-group">
						<label for="phone" class="col-sm-3 control-label">* 电话：</label>
						<div class="col-sm-6">
							<input id="phone" name="phone" value="${sysManager.phone }" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">* 邮箱：</label>
						<div class="col-sm-6">
							<input id="email" name="email" value="${sysManager.email }" class="form-control" type="email">
						</div>
					</div>
					<div class="form-group">
						<label for="roleIds" class="col-sm-3 control-label">角色：</label>
						<div class="col-sm-6">
							<p class="form-control-static"><c:forEach var="sysRole" items="${sysManager.sysRoleList }" varStatus="status">${sysRole.name }<c:if test="${ ! status.last}">,</c:if></c:forEach></p>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-3">
							<button class="btn btn-primary btn-sm" type="submit">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

<%@include file="infoJS.jsp"%>
</body>
</html>