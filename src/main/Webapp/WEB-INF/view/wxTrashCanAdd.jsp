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
    <meta charset="utf-8" />
    <link href="${ctx}/assets/users/img/logo.png" rel="shortcut icon" />
    <title>垃圾箱管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

	<script type="text/javascript">
		var ctx = "${ctx}";
	</script>
	
	<%@ include file="common/importCss.jsp"%>
    <%@ include file="common/importJs.jsp"%>
    <script src="${ctx}/assets/users/js/trashCanAdd.js" type="text/javascript"></script>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo">
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content">
            <div class="portlet box green">
                <div class="portlet-title">
                    <div class="caption">垃圾桶-新增</div>
                </div>
                <div class="portlet-body">
	                <form class="form-horizontal" id="trashcanAddForm" name="trashcanAddForm" >
	                    <div class="form-body">
	                        <br>
	                        <div class="form-group">
	                        	<div class="col-md-2"></div>
	                            <label class="control-label col-md-1"> 编号</label>
	                            <div class="col-md-3">
	                                <input type="text" class="form-control" id="code" name="code" >
	                            </div>
	                        </div>
	                        <div class="form-group">
	                        	<div class="col-md-2"></div>
	                            <label class="control-label col-md-1"> 类型</label>
								<div class="col-md-3">
									<select class="form-control" id="type" name="type" onchange="getTypeInfo()">
										<option >请选择...</option>
									</select>
								</div>
	                        </div>
	                        <div class="form-group">
	                        	<div class="col-md-2"></div>
	                            <label class="control-label col-md-1"> 尺寸</label>
	                            <div class="col-md-3">
	                                <input type="text" class="form-control" id="size" name="size" readonly>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                        	<div class="col-md-2"></div>
	                            <label class="control-label col-md-1"> 材质</label>
	                            <div class="col-md-3">
	                                <input type="text" class="form-control" id="material" name="material" readonly>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                        	<div class="col-md-2"></div>
	                            <label class="control-label col-md-1"> 电源</label>
	                            <div class="col-md-3">
	                                <input type="text" class="form-control" id="power" name="power" readonly>
	                            </div>
	                        </div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 寿命</label>
								<div class="col-md-3">
									<input type="text" class="form-control" id="lifetime" name="lifetime" readonly>
								</div>
							</div>
	                        <div class="form-group">
	                        	<div class="col-md-2"></div>
	                            <label class="control-label col-md-1"> 灯光</label>
	                            <div class="col-md-3">
	                                <input type="text" class="form-control" id="light" name="light" readonly>
	                            </div>
	                        </div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 颜色</label>
								<div class="col-md-3">
									<img id="color" src="" style="width: 100px;height: auto">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 经度</label>
								<div class="col-md-3">
									<input type="text" class="form-control" id="lat" name="lat" value="${lat}" readonly>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 纬度</label>
								<div class="col-md-3">
									<input type="text" class="form-control" id="lng" name="lng" value="${lng}" readonly>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 地址</label>
								<div class="col-md-3">
									<input type="text" class="form-control" id="address" name="address" value="${address}" readonly>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 使用日期</label>
								<div class="col-md-3">
									<input type="date" class="form-control" id="useDate" name="useDate" >
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 图片1</label>
								<div class="col-md-3">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<div id="preview1" class="fileinput-preview thumbnail" data-trigger="fileinput" > </div>
										<div>
											<span class="btn red btn-outline btn-file">
												<span class="fileinput-new"> 选择图片 </span>
												<span class="fileinput-exists"> 修改 </span>
												<input type="hidden"><input type="file" id ="pic1File" name="file" onchange="fileUploadBtn('pic1File','pic1')">
												<input type="hidden" class="form-control" id="pic1" name="pic1" >
											</span>
											<a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 图片2</label>
								<div class="col-md-3">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<div id="preview2" class="fileinput-preview thumbnail" data-trigger="fileinput" > </div>
										<div>
											<span class="btn red btn-outline btn-file">
												<span class="fileinput-new"> 选择图片 </span>
												<span class="fileinput-exists"> 修改 </span>
												<input type="hidden"><input type="file" id ="pic2File" name="file" onchange="fileUploadBtn('pic2File','pic2')">
												<input type="hidden" class="form-control" id="pic2" name="pic2" >
											</span>
											<a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 图片3</label>
								<div class="col-md-3">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<div id="preview3" class="fileinput-preview thumbnail" data-trigger="fileinput" > </div>
										<div>
											<span class="btn red btn-outline btn-file">
												<span class="fileinput-new"> 选择图片 </span>
												<span class="fileinput-exists"> 修改 </span>
												<input type="hidden"><input type="file" id ="pic3File" name="file" onchange="fileUploadBtn('pic3File','pic3')">
												<input type="hidden" class="form-control" id="pic3" name="pic3" >
											</span>
											<a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 图片4</label>
								<div class="col-md-3">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<div id="preview4" class="fileinput-preview thumbnail" data-trigger="fileinput" > </div>
										<div>
											<span class="btn red btn-outline btn-file">
												<span class="fileinput-new"> 选择图片 </span>
												<span class="fileinput-exists"> 修改 </span>
												<input type="hidden"><input type="file" id ="pic4File" name="file" onchange="fileUploadBtn('pic4File','pic4')">
												<input type="hidden" class="form-control" id="pic4" name="pic4" >
											</span>
											<a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<label class="control-label col-md-1"> 图片5</label>
								<div class="col-md-3">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<div id="preview5" class="fileinput-preview thumbnail" data-trigger="fileinput" > </div>
										<div>
											<span class="btn red btn-outline btn-file">
												<span class="fileinput-new"> 选择图片 </span>
												<span class="fileinput-exists"> 修改 </span>
												<input type="hidden"><input type="file" id ="pic5File" name="file" onchange="fileUploadBtn('pic5File','pic5')">
												<input type="hidden" class="form-control" id="pic5" name="pic5" >
											</span>
											<a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
										</div>
									</div>
								</div>
							</div>

	                    </div>
	                    <div class="form-actions">
	                        <div class="row">
	                            <div class="col-md-offset-3 col-md-9">
	                                <a class="btn default" href="wxIndex">返回</a>&nbsp;
									<button class="btn green">保存</button>
	                            </div>
	                        </div>
	                    </div>
	                </form>
                </div>
            </div>
        </div>
        <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<script type="text/javascript">
    var width = $("#code").width();
    document.getElementById('preview1').style.width = width + 'px';
    document.getElementById('preview1').style.height = width + 'px';
    document.getElementById('preview2').style.width = width + 'px';
    document.getElementById('preview2').style.height = width + 'px';
    document.getElementById('preview3').style.width = width + 'px';
    document.getElementById('preview3').style.height = width + 'px';
    document.getElementById('preview4').style.width = width + 'px';
    document.getElementById('preview4').style.height = width + 'px';
    document.getElementById('preview5').style.width = width + 'px';
    document.getElementById('preview5').style.height = width + 'px';
</script>
</body>
	</c:when>
	<c:otherwise>
		<%@ include file="wxerror.jsp" %>
	</c:otherwise>
</c:choose>
</html>