<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css">
<nav class="admin-bar">
    <a class="admin-brand" href="${pageContext.request.contextPath}/home">
        &#9733; UTE Admin
    </a>
    <div class="admin-nav-links">
        <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/admin/category/list"
           class="${fn:contains(pageContext.request.servletPath, '/category') ? 'active' : ''}">Danh mục</a>
        <a href="${pageContext.request.contextPath}/admin/product/list"
           class="${fn:contains(pageContext.request.servletPath, '/product') ? 'active' : ''}">Sản phẩm</a>
    </div>
    <div class="spacer"></div>
    <div class="admin-user">
        <c:if test="${not empty sessionScope.account}">
            <strong>${sessionScope.account.fullName}</strong>
        </c:if>
        <a href="${pageContext.request.contextPath}/logout" class="admin-logout">Đăng xuất</a>
    </div>
</nav>
