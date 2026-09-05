<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý sản phẩm — UTE Admin</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css">
</head>
<body style="background: #f1f5f9; min-height: 100vh;">

<jsp:include page="/view/admin-nav.jsp"/>

<div class="admin-wrapper">
    <div class="admin-page-header">
        <div>
            <h1>Quản lý sản phẩm</h1>
            <p class="sub">Tổng cộng <strong>${fn:length(products)}</strong> sản phẩm trong hệ thống</p>
        </div>
        <a href="${pageContext.request.contextPath}/admin/product/add" class="btn-add-new">
            &#43; Thêm sản phẩm mới
        </a>
    </div>

    <c:if test="${not empty flash}"><div class="alert-ok">${flash}</div></c:if>
    <c:if test="${not empty error}"><div class="alert-error">${error}</div></c:if>

    <div class="table-card">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Tồn kho</th>
                    <th>Danh mục</th>
                    <th style="text-align:center;">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty products}">
                        <c:forEach items="${products}" var="p" varStatus="st">
                            <tr>
                                <td class="col-stt">${st.index + 1}</td>
                                <td class="col-img">
                                    <c:choose>
                                        <c:when test="${not empty p.image}">
                                            <img src="${pageContext.request.contextPath}/image?fname=${p.image}" alt="${p.name}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="no-img">📦</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="col-name" style="font-size:14px;">${p.name}</td>
                                <td class="col-price">
                                    <fmt:formatNumber value="${p.price}" type="number"/> đ
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.amount == 0}">
                                            <span class="badge badge-out">Hết hàng</span>
                                        </c:when>
                                        <c:when test="${p.amount <= 5}">
                                            <span class="badge badge-low">${p.amount} còn lại</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-ok">${p.amount}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <span class="badge badge-cate">${p.category.catename}</span>
                                </td>
                                <td class="col-actions">
                                    <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.id}" class="btn-action btn-action-edit">&#9998; Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.id}"
                                       class="btn-action btn-action-delete"
                                       onclick="return confirm('Bạn có chắc muốn xóa sản phẩm \'${p.name}\' không?')">
                                       &#128465; Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">
                                <div class="empty-state">
                                    <div class="icon">📦</div>
                                    <p>Chưa có sản phẩm nào. Hãy bấm <strong>+ Thêm sản phẩm mới</strong> để bắt đầu!</p>
                                </div>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
