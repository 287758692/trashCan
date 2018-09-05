<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
    <c:when test="${User != null}">
<!DOCTYPE html >
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${ctx}/assets/users/img/logo.png" rel="shortcut icon" />
    <title>垃圾箱管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    
	<script type="text/javascript">
		var ctx = "${ctx}";
	</script>
	
	<%@ include file="common/importCss.jsp"%>
    <%@ include file="common/importJs.jsp"%>
    <script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp"></script>
    <script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>
    <script src="${ctx}/assets/users/js/wxTrashCanList.js" type="text/javascript"></script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo" onload="local()">
<!-- BEGIN CONTAINER -->
<div id="sample_2_wrapper" class="dataTables_wrapper no-footer" style="position:relative;">
    <div id="container"style="position:absolute; left:0; top:0;" ></div>
    <div id="searchBtn" style="position:absolute; left:0; top:0;">
        <div class="input-group input-group-lg">
            <input type="text" class="form-control ui-com" id="address" name="address" placeholder="请输入地址" autocomplete="off">
            <span class="input-group-btn">
                <button class="btn blue" onclick="getTrashCan()"><i class=" fa fa-search"></i> 查询 </button>
            </span>
        </div>
    </div>
</div>
<!-- END CONTAINER -->
<script type="text/javascript">
    document.getElementById('container').style.width = screen.width + 'px';
    document.getElementById('container').style.height = screen.height + 'px';
    document.getElementById('searchBtn').style.width = screen.width + 'px';
</script>
</body>
</c:when>
<c:otherwise>
    <%@ include file="wxerror.jsp" %>
</c:otherwise>
</c:choose>
</html>