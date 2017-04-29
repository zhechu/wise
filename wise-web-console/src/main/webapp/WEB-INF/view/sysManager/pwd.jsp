<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/include/base.jsp"%>
<link href="${ctx }/res/css/content.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>修改密码</h5>
			</div>
			<div class="ibox-content">
				<div id="msg-box"></div>
				<form class="form-horizontal m-t" id="pwdForm" action="${ctx }/sysManager/pwd" method="post">
					<div class="form-group">
						<label for="oldPwd" class="col-sm-3 control-label">旧密码：</label>
						<div class="col-sm-8">
							<input id="oldPwd" name="oldPwd" class="form-control" type="password">
						</div>
					</div>
					<div class="form-group">
						<label for="newPwd" class="col-sm-3 control-label">新密码：</label>
						<div class="col-sm-8">
							<input id="newPwd" name="newPwd" class="form-control" type="password">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmPwd" class="col-sm-3 control-label">确认密码：</label>
						<div class="col-sm-8">
							<input id="confirmPwd" name="confirmPwd" class="form-control" type="password">
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

<%@include file="pwdJS.jsp"%>
</body>
</html>