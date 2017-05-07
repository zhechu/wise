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
				<c:set var="status" value="1"/>
				<h5><c:choose><c:when test="${empty sysDict }">添加</c:when><c:otherwise><c:set var="status" value="${sysDict.status }"/>编辑</c:otherwise></c:choose>字典</h5>
			</div>
			<div class="ibox-content">
				<div id="msg-box"></div>
				<form class="form-horizontal m-t" id="sysDictForm" action="/sysDict/save" method="post">
					<input id="id" name="id" value="${sysDict.id }" type="hidden">
					<div class="form-group">
						<label for="type" class="col-sm-3 control-label">* 类型：</label>
						<div class="col-sm-6">
							<div class="input-group">
								<uform:select 
									classes="chosen-select" 
									style="width:200px;"
									name="type" 
									id="type" 
									items="${sysDictMetaList}" 
									itemLabel="name" 
									itemValue="code" 
									defaultValue="${sysDict.type }"
								/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="label" class="col-sm-3 control-label">* 标签：</label>
						<div class="col-sm-6">
							<input id="label" name="label" value="${sysDict.label }" class="form-control" type="text" data-toggle="tooltip" data-placement="right" title="<spring:message code="sys.dict.label.length" />">
							<span class="help-block m-b-none"><spring:message code="sys.dict.label.hint" /></span>
						</div>
					</div>
					<div class="form-group">
						<label for="value" class="col-sm-3 control-label">* 值：</label>
						<div class="col-sm-6">
							<input id="value" name="value" value="${sysDict.value }" class="form-control" type="text">
							<span class="help-block m-b-none"><spring:message code="sys.dict.value.hint" /></span>
						</div>
					</div>
					<div class="form-group">
						<label for="status" class="col-sm-3 control-label">* 状态：</label>
						<div class="col-sm-6">
							<div class="input-group">
								<uform:select 
									classes="chosen-select" 
									style="width:200px;"
									name="status" 
									id="status" 
									items="${fns:getDictList('sys_dict_status')}" 
									itemLabel="label" 
									itemValue="value" 
									defaultValue="${status }"
								/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="sort" class="col-sm-3 control-label">排序：</label>
						<div class="col-sm-6">
							<div class="input-group spinner" data-trigger="spinner">
								<input id="sort" name="sort" value="${sysDict.sort }" type="text"
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
							<a class="btn btn-white btn-sm" href="${ctx }/sysDict/list">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<%@include file="sysDictEditJS.jsp"%>
</body>
</html>