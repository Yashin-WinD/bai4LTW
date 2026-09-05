<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${product.name} — UTE Shop</title>
</head>
<body>
<jsp:include page="/view/shop-header.jsp"/>
<div class="container">
    <div class="breadcrumb">
        <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
        <span class="sep">/</span>
        <a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
        <span class="sep">/</span>
        <span class="current">${product.name}</span>
    </div>
    
    <div class="detail">
        <div class="detail-img">
            <c:choose>
                <c:when test="${not empty product.image}">
                    <img src="${pageContext.request.contextPath}/image?fname=${product.image}" alt="${product.name}">
                </c:when>
                <c:otherwise>
                    <div style="height:320px; border-radius:12px; background:linear-gradient(135deg, #e2e8f0, #f1f5f9); display:flex; align-items:center; justify-content:center; font-size:40px; color:#94a3b8;">📦</div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="detail-info">
            <h2 style="font-size: 26px; font-weight: 800; color: #1e293b; margin-bottom: 12px; letter-spacing: -0.5px;">${product.name}</h2>
            <p style="margin-bottom: 20px;">
                <span class="badge badge-cate" style="font-size: 14px; padding: 6px 14px;">${product.category.catename}</span>
            </p>
            <p class="price" style="font-size: 28px; margin: 16px 0;">
                <fmt:formatNumber value="${product.price}" type="number"/> đ
            </p>
            <p style="font-weight: 600; color: #475569; margin-bottom: 24px;">
                Kho hàng: 
                <c:choose>
                    <c:when test="${product.amount == 0}">
                        <span class="badge badge-out">Hết hàng</span>
                    </c:when>
                    <c:when test="${product.amount <= 5}">
                        <span class="badge badge-low">${product.amount} sản phẩm</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge badge-ok">${product.amount} sản phẩm</span>
                    </c:otherwise>
                </c:choose>
            </p>
            <div style="background: #f8fafc; padding: 20px; border-radius: 12px; border: 1px solid #e2e8f0; margin-bottom: 24px;">
                <h4 style="margin-bottom: 10px; color: #1e293b;">Mô tả sản phẩm:</h4>
                <p style="white-space: pre-line; color: #475569; line-height: 1.6; font-size: 15px;">${product.description}</p>
            </div>
            <p>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/product" style="padding: 14px 28px; font-size: 16px;">&#8592; Xem tất cả sản phẩm</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
