<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng nhập — UTE Shop</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css">
</head>
<body class="auth-wrap">
    <div class="auth-card">
        <div class="auth-logo">&#9733; UTE Shop</div>
        <h2>Chào mừng trở lại</h2>
        <p class="auth-sub">Đăng nhập vào tài khoản của bạn</p>

        <c:if test="${not empty alert}"><div class="alert-error">${alert}</div></c:if>
        <c:if test="${not empty success}"><div class="alert-ok">${success}</div></c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label>Tên đăng nhập</label>
                <input class="form-control" type="text" name="username"
                       value="${username}" required autofocus placeholder="Nhập tên đăng nhập...">
            </div>
            <div class="form-group">
                <label>Mật khẩu</label>
                <input class="form-control" type="password" name="password"
                       required placeholder="Nhập mật khẩu...">
            </div>
            <div class="form-group" style="display:flex; align-items:center; gap:8px;">
                <input type="checkbox" name="remember" id="remember" style="width:16px; height:16px; accent-color:#4f46e5;">
                <label for="remember" style="margin:0; font-weight:600; color:#64748b; font-size:13px; cursor:pointer;">Ghi nhớ đăng nhập</label>
            </div>
            <button class="btn btn-primary btn-block" type="submit">Đăng nhập</button>
        </form>

        <div class="auth-divider"></div>

        <div class="auth-links">
            <a href="${pageContext.request.contextPath}/register">Đăng ký tài khoản</a>
            <span class="sep">·</span>
            <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
            <span class="sep">·</span>
            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
        </div>
    </div>
</body>
</html>
