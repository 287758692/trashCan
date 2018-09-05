<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- BEGIN SIDEBAR -->
    <div class="page-sidebar-wrapper">
        <!-- BEGIN SIDEBAR -->
        <div class="page-sidebar navbar-collapse collapse">
            <!-- BEGIN SIDEBAR MENU -->
            <ul id ="menu" class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                <li class="sidebar-toggler-wrapper hide">
                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    <div class="sidebar-toggler">
                        <span></span>
                    </div>
                    <!-- END SIDEBAR TOGGLER BUTTON -->
                </li>
			<c:forEach items="${sessionScope.Menu}" var="item" >
			<c:choose>
			<c:when test="${item.menuId eq sessionScope.MenuId}">
				<li class="nav-item active open">
					<a href="javascript:;" class="nav-link nav-toggle">
						<i class='${item.menuIcon}'></i>
						<span class="title">${item.menuName}</span>
						<span class="selected"></span>
						<span class="arrow open"></span>
					</a>
			</c:when>
			<c:otherwise>
				<li class="nav-item ">
					<a href="javascript:;" class="nav-link nav-toggle">
						<i class='${item.menuIcon}'></i>
						<span class="title">${item.menuName}</span>
						<span class="arrow"></span>
					</a>
			</c:otherwise>
	 		</c:choose>
					<ul class="sub-menu">
						<c:forEach items="${item.children}" var="subItem">
						<c:choose>  
				      	<c:when test="${subItem.menuId eq sessionScope.SubMenuId}">
			            <li class="nav-item active open">
							<a href="${ctx}${subItem.menuAction}" class="nav-link " onclick = "saveMenuId(${item.menuId},${subItem.menuId})">
							    <i class="${subItem.menuIcon}"></i>
							    <span class="title">${subItem.menuName}</span>
							    <span class="selected"></span>
							</a>
						</li>
				       </c:when>
				       <c:otherwise>
			            <li class="nav-item">
							<a href="${ctx}${subItem.menuAction}" class="nav-link " onclick = "saveMenuId(${item.menuId},${subItem.menuId})">
							    <i class="${subItem.menuIcon}"></i>
							    <span class="title">${subItem.menuName}</span>
							</a>
						</li>
				       </c:otherwise>
				       </c:choose>
				       </c:forEach>
					</ul>
			</c:forEach>
            </ul>
            <!-- END SIDEBAR MENU -->
        </div>
        <!-- END SIDEBAR -->
    </div>