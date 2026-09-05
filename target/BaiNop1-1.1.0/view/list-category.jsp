<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý danh mục — UTE Admin</title>
<style>
    /* Use the global shop.css */
</style>
</head>
<body style="background: #f1f5f9; min-height: 100vh;">

<jsp:include page="/view/admin-nav.jsp"/>

<div class="admin-wrapper">
    <div class="admin-page-header">
        <div>
            <h1>Quản lý danh mục</h1>
            <p class="sub">Tổng cộng <strong>${cateList.size()}</strong> danh mục đang hoạt động</p>
        </div>
        <a href="${pageContext.request.contextPath}/admin/category/add" class="btn-add-new">
            &#43; Thêm danh mục mới
        </a>
    </div>

    <div class="table-card">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>ID</th>
                    <th>Icon</th>
                    <th>Tên danh mục</th>
                    <th style="text-align:center;">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty cateList}">
                        <c:forEach items="${cateList}" var="cate" varStatus="st">
                            <tr>
                                <td class="col-stt">${st.index + 1}</td>
                                <td><span style="color:#64748b; font-weight:600;">#${cate.id}</span></td>
                                <td class="col-img">
                                    <c:choose>
                                        <c:when test="${not empty cate.icon}">
                                            <img src="${pageContext.request.contextPath}/image?fname=${cate.icon}" alt="${cate.catename}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="no-img">📁</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="col-name" style="font-size:15px;">${cate.catename}</td>
                                <td class="col-actions">
                                    <a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.id}" class="btn-action btn-action-edit">&#9998; Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.id}"
                                       class="btn-action btn-action-delete"
                                       onclick="return confirm('Bạn có chắc muốn xóa danh mục \'${cate.catename}\' không?')">
                                       &#128465; Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5">
                                <div class="empty-state">
                                    <div class="icon">📁</div>
                                    <p>Chưa có danh mục nào. Hãy bấm <strong>+ Thêm danh mục mới</strong> để tạo!</p>
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