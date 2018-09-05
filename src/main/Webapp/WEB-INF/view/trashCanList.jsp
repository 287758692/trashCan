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
	</script>
	
	<%@ include file="common/importCss.jsp"%>
    <%@ include file="common/importJs.jsp"%>
    <script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp"></script>
    <script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>
    <script src="${ctx}/assets/users/js/trashCanList.js" type="text/javascript"></script>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo" onload="local()">
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
                    <div class="caption">垃圾桶管理</div>
                </div>
                <div class="portlet light bordered">
                    <div class="portlet-title tabbable-line">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#portlet_tab1" data-toggle="tab" aria-expanded="true"> 地图 </a>
                            </li>
                            <li class="">
                                <a href="#portlet_tab2" data-toggle="tab" aria-expanded="false"> 列表 </a>
                            </li>
                        </ul>
                    </div>
                    <div class="portlet-body">
                        <div class="tab-content">
                            <div class="tab-pane active" id="portlet_tab1">
                                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 700px;">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-6">
                                            <input type="text" class="form-control ui-com" id="address" name="address" placeholder="请输入地址">
                                        </div>
                                        <div class="col-md-6 col-sm-6 text-right">
                                            <button class="btn default" onclick="getTrashCan()"><i class=" fa fa-search"></i> 查询 </button>
                                        </div>
                                    </div>
                                    <br>
                                    <div id="container" style="width:100%; height:700px;"></div>
                                </div>
                            </div>
                            <div class="tab-pane" id="portlet_tab2">
                                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 700px;">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-6">
                                            <input type="text" class="form-control ui-com" id="code" name="address" placeholder="请输入编号">
                                        </div>
                                        <div class="col-md-6 col-sm-6 text-right">
                                            <button class="btn default" onclick="getlist()"><i class=" fa fa-search"></i> 查询 </button>
                                        </div>
                                    </div>
                                    <br>
                                    <table id="trashCanDg" class="table table-striped table-bordered table-hover " style="white-space :nowrap"></table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@ include file="common/pageFooter.jsp" %>
</body>
</html>