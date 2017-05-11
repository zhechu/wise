<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/include/base.jsp"%>
<%@include file="/commons/include/tree.jsp"%>
<link href="${ctx }/res/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctx }/res/css/content.css" rel="stylesheet">
<style type="text/css">
	.form-group {
		margin-bottom: 0;
	}
	.trigon {
		color: #b4b4b4;
        font-size: 25px;
        padding-left: 2px;
    }
    #left {
        padding-left: 0;
        padding-right: 0;
    }
    #left .ibox {
    	height: 100%;
        background-color: white;
    }
    #right {
        padding-left: 0;
        padding-right: 0;
    }
    #right>table, #right>table>tbody, #right>table>tbody>tr, #right>table>tbody>tr>td {
    	height: 100%;
    }
    .press {
        width: 12px;
        background-color: #E7EAEC;
        height: 100%;
        float: left;
        border-radius: 3px;
        cursor:pointer;
    }
    .press i {
        position: relative;
  		top: 50%;
    }
    #right-main {
        background-color: white;
        height: 100%;
        float: right;
    }
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
	    <div class="row" style="padding-left:15px; padding-right:15px;">
	        <div id="left" class="col-sm-2">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>组织机构</h5>
	                    <div class="ibox-tools">
                            <a id="refreshBtn" href="javascript:;" data-toggle="tooltip" data-placement="top" title="刷新">
                                <i class="fa fa-refresh"></i>
                            </a>
                        </div>
	                </div>
	                <div class="ibox-content" style="overflow:auto;">
	                    <ul id="tree" class="ztree"></ul>
	                </div>
	            </div>
	        </div>
	        <div id="right" class="col-sm-10">
	            <table>
	                <tbody>
	                	<tr>
	                		<td width="12">
			                    <div class="press">
		                            <i class="fa fa-caret-right trigon one"></i>
		                            <i class="fa fa-caret-left trigon two"></i>
			                    </div>
			                </td>
			                <td width="12">&nbsp;&nbsp;&nbsp;</td>
			                <td width="100%">
			                    <div id="right-main" style="width: 100%;">
			                        <div class="ibox float-e-margins">
						                <div class="ibox-title">
						                    <h5>用户查询</h5>
								            <div class="ibox-tools">
								                <a class="collapse-link" data-toggle="tooltip" data-placement="left" title="折叠/展开">
								                    <i class="fa fa-chevron-up"></i>
								                </a>
								            </div>
						                </div>
						                <div class="ibox-content">
								            <form id="searchForm" role="form"  class="form-horizontal">
							                    <div class="row">
							                        <div class="form-group col-md-4">
							                            <label for="userName" class="col-md-3 control-label">用户名：</label>
							                            <div class="col-md-9">
							                                <input type="text" class="form-control" name="userName" id="userName">
							                            </div>
							                        </div>
							                        <div class="form-group col-md-4">
							                            <label for="name" class="col-md-3 control-label">姓名：</label>
							                            <div class="col-md-9">
							                                <input type="text" class="form-control" name="name" id="name">
							                            </div>
							                        </div>
							                        <div class="form-group col-md-4">
							                            <label for="email" class="col-md-3 control-label">邮箱：</label>
							                            <div class="col-md-9">
							                                <input type="text" class="form-control" name="email" id="email">
							                            </div>
							                        </div>
							                    </div>
							                    <br/>
							                    <div class="row">
							                    	<div class="form-group col-md-4">
							                            <label for="sysRoleId" class="col-md-3 control-label">角色：</label>
							                            <div class="col-md-9">
															<div class="input-group">
																<uform:select 
																	classes="chosen-select" 
																	style="width:200px;"
																	name="sysRoleId" 
																	id="sysRoleId" 
																	items="${sysRoleList}" 
																	itemLabel="name" 
																	itemValue="id" 
																	hintLabel="全部"
																/>
															</div>
							                            </div>
							                        </div>
							                    	<div class="form-group col-md-4">
							                            <label for="status" class="col-md-3 control-label">状态：</label>
							                            <div class="col-md-9">
															<div class="input-group">
																<uform:select 
																	classes="chosen-select" 
																	style="width:200px;"
																	name="status" 
																	id="status" 
																	items="${fns:getDictList('sys_manager_status')}" 
																	itemLabel="label" 
																	itemValue="value" 
																	hintLabel="全部"
																/>
															</div>
							                            </div>
							                        </div>
							                        <div class="form-group col-md-4">
							                            <label for="createdAtStart" class="col-md-3 control-label">创建时间：</label>
							                            <div class="col-md-9">
							                            	<div class="input-daterange input-group" id="created">
								                                <input type="text" class="form-control" name="createdAtStart" id="createdAtStart" />
								                                <span class="input-group-addon">-</span>
								                                <input type="text" class="form-control" name="createdAtEnd" id="createdAtEnd" />
								                            </div>
							                            </div>
							                        </div>
							                    </div>
							                    <br/>
							                    <div class="row">
							                        <div class="form-group col-md-12 text-right">
							                            <button type="submit" class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查询</button>
							                        </div>
							                    </div>
							                </form>
								        </div>
						            </div>
						            <div class="ibox float-e-margins">
										<div class="ibox-table">
											<div id="msg-box"></div>
											<div id="toolbar">
										        <shiro:hasPermission name="sys:manager:add">
										        	<a href="${ctx }/sysManager/edit" class="btn btn-info btn-sm"><i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;添加</a>
										        </shiro:hasPermission>
										        <shiro:hasPermission name="sys:manager:delete">
										        	<a id="delBtn" href="javascript:;" class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删除</a>
										        </shiro:hasPermission>
									        	<a id="exportBtn" href="javascript:;" class="btn btn-info btn-sm"><i class="glyphicon glyphicon-export"></i>&nbsp;&nbsp;导出</a>
									        	<a id="importBtn" href="javascript:;" class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-import"></i>&nbsp;&nbsp;导入</a>
										    </div>
										    <table id="managerTable" class="table table-hover">
										
										    </table>
										</div>
									</div>
			                    </div>
			                </td>
	                	</tr>
	                </tbody>
	            </table>
	        </div>
	    </div>
	</div>
<!-- 回显提示信息 -->
<c:if test="${ ! empty rm and rm.msg != null and rm.msg != '' }">
	<c:choose>
		<c:when test="${rm.success }">
			<script type="text/javascript">
				$("#msg-box").addMsgBox('success', "${rm.msg}");
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				$("#msg-box").addMsgBox('warning', "${rm.msg}");
			</script>
		</c:otherwise>
	</c:choose>
</c:if>	
<!-- 导入面板 -->
<div id="importContent" style="display:none;">
	<div class="ibox float-e-margins">
        <div class="ibox-content" style="width:260px; height:120px; overflow:auto;">
        	<form id="importForm" role="form" class="form-horizontal" style="padding:5px;" action="${ctx}/sysManager/import" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input type="file" class="form-control" name="importFile" id="importFile" accept=".XLS,.xlsx">
					<span class="help-block m-b-none"><spring:message code="sys.manager.export.hint" /></span>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${ctx }/res/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx }/res/js/content.min.js?v=1.0.0"></script>
<%@include file="sysManagerListJS.jsp"%>
</body>
</html>