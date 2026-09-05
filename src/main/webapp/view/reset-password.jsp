<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đặt lại mật khẩu — UTE Shop</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css">
</head>
<body class="auth-wrap">
    <div class="auth-card">
        <div class="auth-logo">&#9733; UTE Shop</div>
        <h2>Đặt lại mật khẩu</h2>
        <p class="auth-sub">Mã OTP đã được xác nhận. Vui lòng nhập mật khẩu mới của bạn.</p>

        <c:if test="${not empty alert}">
            <div class="alert-error">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <div class="form-group">
                <label>Mật khẩu mới <span class="required">*</span></label>
                <input class="form-control" type="password" name="password"
                       required autofocus placeholder="Nhập ít nhất 6 ký tự...">
            </div>
            
            <div class="form-group">
                <label>Xác nhận mật khẩu mới <span class="required">*</span></label>
                <input class="form-control" type="password" name="confirm"
                       required placeholder="Nhập lại mật khẩu mới...">
            </div>
            
            <button class="btn btn-primary btn-block" type="submit" style="margin-top: 8px;">Lưu mật khẩu mới</button>
        </form>

        <div class="auth-divider"></div>

        <div class="auth-links">
            <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
        </div>
    </div>
</body>
</html>
