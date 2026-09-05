<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sản phẩm — UTE Shop</title>
</head>
<body>
<jsp:include page="/view/shop-header.jsp"/>
<div class="container">
    <h2 class="page-title">🛍️ Tất cả sản phẩm</h2>
    
    <div class="grid">
        <c:forEach items="${products}" var="p">
            <a class="card" href="${pageContext.request.contextPath}/product/detail?id=${p.id}">
                <c:choose>
                    <c:when test="${not empty p.image}">
                        <img src="${pageContext.request.contextPath}/image?fname=${p.image}" alt="${p.name}">
                    </c:when>
                    <c:otherwise>
                        <div class="thumb"></div>
                    </c:otherwise>
                </c:choose>
                <div class="card-body">
                    <h3>${p.name}</h3>
                    <div class="price"><fmt:formatNumber value="${p.price}" type="number"/> đ</div>
                    <div class="muted" style="margin-top:4px;">${p.category.catename}</div>
                </div>
            </a>
        </c:forEach>
    </div>
    
    <c:if test="${empty products}">
        <div style="text-align:center; padding:60px 20px; color:#64748b;">
            <div style="font-size:48px; margin-bottom:12px;">📦</div>
            <p>Chưa có sản phẩm nào. Quay lại sau nhé!</p>
        </div>
    </c:if>
    
    <div class="pagination">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${i == page}">
                    <span class="active">${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/product?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>
</body>
</html>
