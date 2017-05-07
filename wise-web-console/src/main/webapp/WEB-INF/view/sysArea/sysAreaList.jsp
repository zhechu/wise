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
    #right table, thead, tr, td {
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
	                    <h5>区域</h5>
	                    <div class="ibox-tools">
                            <a id="backRootBtn" href="javascript:;" data-toggle="tooltip" data-placement="top" title="回到根节点">
                                <i class="fa fa-undo"></i>
                            </a>
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
						                    <h5>子区域</h5>
						                </div>
						                <div class="ibox-content">
						                    <div id="msg-box"></div>
											<div id="toolbar">
										        <shiro:hasPermission name="sys:area:add">
										        	<a href="${ctx }/sysArea/edit" class="btn btn-info btn-sm"><i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;添加</a>
										        </shiro:hasPermission>
										        <shiro:hasPermission name="sys:area:delete">
										        	<a id="delBtn" href="javascript:;" class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删除</a>
										        </shiro:hasPermission>
										    </div>
										    <table id="areaTable" class="table table-hover">
												
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

<script src="${ctx }/res/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx }/res/js/content.min.js?v=1.0.0"></script>
<%@include file="sysAreaListJS.jsp"%>
</body>
</html>