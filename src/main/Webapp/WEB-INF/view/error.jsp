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
	<meta name="HandheldFriendly" content="true" />
	<meta name="MobileOptimized" content="320" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

	<script type="text/javascript">
		var ctx = "${ctx}";
	</script>
	<link href="${ctx}/assets/users/css/error.css" rel="stylesheet" type="text/css" media="screen" />   
</head>

<body>
    
<!-- Main Wrapper. Set this to 'fixed' for fixed layout and 'fluid' for fluid layout' -->
<div id="da-wrapper" class="fluid">
	<!-- Content -->
	<div id="da-content">
	    <!-- Container -->
	    <div class="da-container clearfix">
	    	<div id="da-error-wrapper">
	           	<div id="da-error-pin"></div>
	            <div id="da-error-code">
	            	error 
	            	<span>404</span>                    
	            </div>
	        	<h1 class="da-error-heading">页面丢失中……</h1>
	            <p>请重新登陆<a href="${ctx}/login/close">返回登录</a></p>
	        </div>
	    </div>
	</div>
</div>
</body>
</html>
