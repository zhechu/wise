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
<link href="${ctx }/res/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet">
<style type="text/css">
	.fileinput {
	    margin-bottom: 0;
	}
	.fileinput-preview.fileinput-exists img {
	    border-radius: 50%;
	}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<c:set var="status" value="1"/>
				<c:set var="sex" value="1"/>
				<c:set var="readonly" value=""/>
				<c:set var="portraitPic" value="${ctx }/res/img/default_user_image.png"/>
				<h5><c:choose><c:when test="${empty sysManager }">添加</c:when><c:otherwise><c:set var="status" value="${sysManager.status }"/><c:set var="sex" value="${sysManager.sex }"/><c:set var="readonly" value="readonly"/><c:if test="${ ! empty sysManager.portraitPic }"><c:set var="portraitPic" value="${sysManager.portraitPic }"/></c:if>编辑</c:otherwise></c:choose>用户</h5>
			</div>
			<div class="ibox-content">
				<div id="msg-box"></div>
				<form class="form-horizontal m-t" id="sysManagerForm" action="${ctx }/sysManager/save" method="post" enctype="multipart/form-data">
					<input id="id" name="id" value="${sysManager.id }" type="hidden">
					<div class="form-group">
						<label for="portraitPic" class="col-sm-3 control-label">头像：</label>
						<div class="col-sm-6">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
							    <div class="fileinput-new thumbnail" style="width: 100px; height: 64px; border:none;">
                                    <img id="picImg" class="img-circle" src="${portraitPic }" alt="" /> </div>
                                <div class="fileinput-preview fileinput-exists thumbnail" style="width: 100px; height: 64px; max-width: 100px; max-height: 64px; border:none;"> </div>
                               <div>
                                   <span class="btn-file">
                                       <span class="btn btn-default btn-sm fileinput-new"> 选择 </span>
                                       <span class="btn btn-default btn-sm fileinput-exists"> 选择 </span>
                                       <input type="file" name="pic" id="pic"></span>
                                   <a id="picClear" style="float:right;" href="javascript:;" class="btn btn-default btn-sm fileinput-exists" data-dismiss="fileinput"> 清空 </a>
                               </div>
                           </div>
                           <span class="help-block m-b-none"><spring:message code="sys.manager.portraitPic.hint" /></span>
						</div>
					</div>
					<div class="form-group">
						<label for="userName" class="col-sm-3 control-label">* 用户名：</label>
						<div class="col-sm-6">
							<input id="userName" name="userName" value="${sysManager.userName }" class="form-control" ${readonly } type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="pwd" class="col-sm-3 control-label">密码：</label>
						<div class="col-sm-6">
							<input id="pwd" name="pwd" class="form-control" type="password">
							<span class="help-block m-b-none"><c:choose><c:when test="${empty sysManager }"><spring:message code="pwd.add.hint" /></c:when><c:otherwise><spring:message code="pwd.update.hint" /></c:otherwise></c:choose></span>
						</div>
					</div>
					<div class="form-group">
						<label for="confirmPwd" class="col-sm-3 control-label">确认密码：</label>
						<div class="col-sm-6">
							<input id="confirmPwd" name="confirmPwd" class="form-control" type="password">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">* 姓名：</label>
						<div class="col-sm-6">
							<input id="name" name="name" value="${sysManager.name }" class="form-control" type="text">
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
								defaultValue="${sex }"
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
							<uform:checkbox
								items="${sysRoleList}" 
								name="roleIds" 
								itemLabel="name" 
								itemValue="id"
								classes="checkbox checkbox-success checkbox-inline"
								defaultValue="${roleIds }"
							/>
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
									items="${fns:getDictList('sys_manager_status')}" 
									itemLabel="label" 
									itemValue="value" 
									defaultValue="${status }"
								/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-3">
							<button class="btn btn-primary btn-sm" type="submit">保存</button>
						</div>
						<div class="col-sm-2">
							<a class="btn btn-white btn-sm" href="${ctx }/sysManager/list">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${ctx }/res/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<%@include file="sysManagerEditJS.jsp"%>
</body>
</html>