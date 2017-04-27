<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="app.name" /> - 登录</title>
<%@include file="/commons/include/base.jsp"%>
<style type="text/css">
	.middle-box h1 {
	    font-size: 85px;
	}
	.logo-name {
	    letter-spacing: 5px;
	}
	.middle-box {
	    padding-top: 90px;
	}
</style>
<script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div id="msg-box"></div>
            <div>
                <h1 class="logo-name"><spring:message code="logo.name" /></h1>
            </div>
            <h2><spring:message code="logo.sub.name" /></h2>
            <form id="loginForm" class="m-t" action="${ctx }/login" method="post">
                <div class="form-group">
                    <input id="userName" name="userName" type="text" class="form-control" placeholder="用户名">
                </div>
                <div class="form-group">
                    <input id="pwd" name="pwd" type="password" class="form-control" placeholder="密码">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
                <p class="text-muted text-center"> <a href="#"><small>忘记密码了？</small></a> | <a href="#">注册一个新账号</a></p>
            </form>
        </div>
    </div>

<%@include file="loginJS.jsp"%>
</body>
</html>