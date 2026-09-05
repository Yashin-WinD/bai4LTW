<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-sm-6 d-flex justify-content-end align-items-center">
    <ul class="list-inline mb-0 d-flex align-items-center gap-3">
        <c:choose>
            <c:when test="${empty sessionScope.account}">
                <li class="list-inline-item">
                    <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">Đăng nhập</a>
                    <span class="mx-1 text-muted">|</span>
                    <a href="${pageContext.request.contextPath}/register" class="text-decoration-none">Đăng ký</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="list-inline-item">
                    <a href="${pageContext.request.contextPath}/member/myaccount" class="text-decoration-none fw-semibold">
                        ${sessionScope.account.fullName}
                    </a>
                    <span class="mx-1 text-muted">|</span>
                    <a href="${pageContext.request.contextPath}/logout" class="text-decoration-none">Đăng xuất</a>
                </li>
            </c:otherwise>
        </c:choose>
        <li class="list-inline-item">
            <i class="search fa fa-search search-button" role="button"></i>
        </li>
    </ul>
</div>