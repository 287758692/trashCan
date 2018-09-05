<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE HTML>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <link href="${ctx}/assets/users/img/logo.png" rel="shortcut icon"/>
    <title>垃圾箱管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

	<script type="text/javascript">
		var ctx = "${ctx}";
        var width_screen=screen.width;
        var height_screen=screen.height;
	</script>
	
	<%@ include file="common/importCss.jsp"%>
    <link href="${ctx}/assets/users/css/login.min.css" rel="stylesheet" type="text/css" />
</head>
<!-- END HEAD -->

<body class="login" onload="return username.focus()" style="background-image:url('${ctx}/assets/users/img/wxLoginbg.jpg');background-size:${width_screen}，${height_screen};">
<!-- BEGIN LOGO -->
<div class="logo">
	<h1 style="color: white">垃圾箱管理系统</h1>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<form class="login-form" id="loginForm" name="loginForm" action="${ctx}/WxTrashCanController/wxIndex" method="post">
		<h3 class="form-title font-green">登录</h3>
		<div class="form-group" data-bv-notempty data-bv-notempty-message="请输入用户名">
		    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
		    <label class="control-label visible-ie8 visible-ie9">用户名</label>
		    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" id="username" name="username" /> </div>
		<div class="form-group">
		    <label class="control-label visible-ie8 visible-ie9">密码</label>
		    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" id="password" name="password" /> </div>
		<div class="form-group">
		    <div class="row">
		        <div class="col-md-7">
		            <label class="control-label visible-ie8 visible-ie9">验证码</label>
		            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="验证码" id="verifycode" name="verifycode"/>
		        </div>
		        <div class="col-md-5" align="right">
		            <label class="control-label visible-ie8 visible-ie9">&nbsp;</label>
		            <img src="${ctx}/login/image" id="img" onclick="changeImage()" style="margin-top: 5%">
		        </div>
		    </div>
		</div>
		<div class="form-actions">
		    <input type="button" class="btn green uppercase" value="登录" style="width: 100%" id="loginbtn"/>
		</div>
		<div class="create-account">
	  	</div>
	</form>
</div>
<!-- END LOGIN -->
<div class="copyright"> 2018 &copy; 忻铭科技. </div>
    <%@ include file="common/importJs.jsp"%>
	<script src="${ctx}/assets/users/js/login.min.js" type="text/javascript"></script>
</body>
</html>