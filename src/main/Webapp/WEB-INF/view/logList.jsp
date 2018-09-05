<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html >
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <link href="${ctx}/assets/users/img/logo.png" rel="shortcut icon" />
    <title>垃圾箱管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    
	<script type="text/javascript">
		var ctx = "${ctx}";
		var insertFunc = "${insertFunc}";
		var updateFunc = "${updateFunc}";
		var applyFunc = "${applyFunc}";
	</script>
	
	<%@ include file="common/importCss.jsp"%>
    <%@ include file="common/importJs.jsp"%>
    <script src="${ctx}/assets/users/js/logList.js" type="text/javascript"></script>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo">
<%@ include file="common/pageHeader.jsp" %>

<!-- BEGIN HEADER & CONTENT DIVIDER -->
<div class="clearfix"> </div>
<!-- END HEADER & CONTENT DIVIDER -->

<!-- BEGIN CONTAINER -->
<div class="page-container">
	<%@ include file="common/pageMenu.jsp" %>
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content">
            <div class="portlet box green">
                <div class="portlet-title">
                    <div class="caption">日志管理</div>
                </div>
                <div class="portlet-body">
                    <div id="sample_2_wrapper" class="dataTables_wrapper no-footer">
                    	<div id="logTb">
	                        <div class="row">
	                            <div class="col-md-3">
	                                <label class="control-label">状态</label>
	                                <select class="form-control" id="status">
	                                	<option value = "">请选择...</option>
	                                	<option value = "0">正常</option>
	                                	<option value = "1">报错</option>
	                                </select>
	                            </div>
	                        </div>
	                        <div class="row margin-bottom-10">
	                            <div class="col-md-12 text-right">
	                                <button class="btn default" onclick="initTable()"><i class=" fa fa-search"></i> 查询 </button>
                                </div>
	                        </div>
                        </div>
                       	<table id="logDg" class="table table-striped table-bordered table-hover " style="white-space :nowrap"></table>
                    </div>
                </div>
            </div>
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<div id="logDetail" class="modal fade" tabindex="-1" >
    <div class="modal-dialog" style="width:90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">
                    <span id="logTitle"></span>
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="logDetailForm" name="logDetailForm" >
                    <div class="form-body">
                        <br>
                        <div class="form-group">
                            <div class="col-md-10" id="ExceptionDetail" >
                            </div>
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <a class="btn default" onclick="$('#logDetail').modal('hide')">返回</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/pageFooter.jsp" %>
</body>
</html>