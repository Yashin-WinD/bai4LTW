<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css">
<header class="site-header">
    <a class="brand" href="${pageContext.request.contextPath}/home">&#9733; UTE Shop</a>
    <nav class="nav">
        <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
        <c:if test="${sessionScope.account.roleId == 1 || sessionScope.account.roleId == 2}">
            <a href="${pageContext.request.contextPath}/admin/category/list">Danh mục</a>
            <a href="${pageContext.request.contextPath}/admin/product/list">Quản lý SP</a>
        </c:if>
    </nav>
    <div class="header-user">
        <c:choose>
            <c:when test="${empty sessionScope.account}">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-primary" style="padding:8px 18px; font-size:13px;">Đăng nhập</a>
                <a href="${pageContext.request.contextPath}/register" style="font-size:13px; color:#64748b; font-weight:600;">Đăng ký</a>
            </c:when>
            <c:otherwise>
                <strong>${sessionScope.account.fullName}</strong>
                <span class="muted">|</span>
                <a href="${pageContext.request.contextPath}/logout" style="color:#dc2626; font-size:13px; font-weight:700;">Đăng xuất</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>
