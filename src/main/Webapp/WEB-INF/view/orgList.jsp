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
		var insertBtn = "${insertBtn}";
		var updateBtn = "${updateBtn}";
		var deleteBtn = "${deleteBtn}";
		var searchBtn = "${searchBtn}";
		var inBtn = "${inBtn}";
		var outBtn = "${outBtn}";
	</script>
	
	<%@ include file="common/importCss.jsp"%>
    <%@ include file="common/importJs.jsp"%>
    <script src="${ctx}/assets/users/js/orgList.js" type="text/javascript"></script>
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
            <div class="portlet box green col-md-6">
                <div class="portlet-title">
                    <div class="caption">机构列表</div>
                </div>
                <div class="portlet-body">
                    <div id="sample_2_wrapper" class="dataTables_wrapper no-footer">
                    	<div id="orgTb">
	                        <div class="row">
	                            <div class="col-md-6">
	                                <label class="control-label">机构名称</label>
	                                <input type="text" class="form-control" id="orgName">
	                            </div>
	                        </div>
	                        <div class="row margin-bottom-10">
	                            <div class="col-md-12 text-right">
	                                <button class="btn default" onclick="initTable()"><i class=" fa fa-search"></i> 查询 </button>
                                <c:choose>
								<c:when test="${insertBtn!='' && insertBtn!=null}">
									<a href="${ctx}${insertBtn}" class="btn green"><i class="fa fa-plus"></i> 新增 </a>
								</c:when>
								<c:otherwise>
								</c:otherwise>
						 		</c:choose>
                                </div>
	                        </div>
                        </div>
                       	<table id="orgDg" class="table table-striped table-bordered table-hover" style="white-space :nowrap"></table>
                    </div>
                </div>
            </div>
            <div class="portlet box green col-md-6">
                <div class="portlet-title">
                    <div class="caption">成员列表</div>
                </div>
                <div class="portlet-body">
                    <div id="sample_2_wrapper" class="dataTables_wrapper no-footer">
                    	<div id="userTb">
                    		<div class="row">
                    			<input type="hidden" class="form-control" id="userOrgId" >
                    			<div class="col-md-6">
	                                <label class="control-label">机构名称</label>
	                                <input type="text" class="form-control" id="userOrgName" readonly>
	                            </div>
	                        </div>
	                        <div class="row margin-bottom-10">
	                            <div class="col-md-12 text-right">
                                <c:choose>
								<c:when test="${inBtn!='' && inBtn!=null}">
									<a href="#addUser" data-toggle="modal" class="btn green" onclick="initInUser();"><i class="fa fa-plus"></i> 添加成员 </a>
								</c:when>
								<c:otherwise>
								</c:otherwise>
						 		</c:choose>
                                </div>
	                        </div>
                        </div>
                       	<table id="userDg" class="table table-striped table-bordered table-hover" style="white-space :nowrap"></table>
                    </div>
                </div>
            </div>
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
</div>
<div id="addUser" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">
                    <span>添加成员</span>
                </h4>
            </div>
            <div class="modal-body">
				<div id="sample_2_wrapper" class="dataTables_wrapper no-footer">
	               	<div id="userInTb">
	                  	<table id="userInDg" class="table table-striped table-bordered table-hover"></table>
	               </div>
	            </div>
            </div>
        </div>
    </div>
</div>
<!-- END CONTAINER -->
<%@ include file="common/pageFooter.jsp" %>
</body>
</html>