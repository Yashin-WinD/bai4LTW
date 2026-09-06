<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Đăng ký — UTE Shop</title>

<div class="auth-card">
	<div class="auth-logo">&#9733; UTE Shop</div>
	<h2>Tạo tài khoản mới</h2>
	<p class="auth-sub">Mã OTP kích hoạt sẽ được gửi qua email</p>

	<c:if test="${not empty alert}">
		<div class="alert-error">${alert}</div>
	</c:if>

	<form action="${pageContext.request.contextPath}/register"
		method="post">
		<div class="form-group">
			<label>Tên đăng nhập <span class="required">*</span></label> <input
				class="form-control" name="username" required
				placeholder="Nhập tên đăng nhập...">
		</div>
		<div class="form-group">
			<label>Email <span class="required">*</span></label> <input
				class="form-control" type="email" name="email" required
				placeholder="example@gmail.com">
		</div>
		<div class="form-group">
			<label>Họ và tên</label> <input class="form-control" name="fullName"
				placeholder="Nhập họ tên đầy đủ...">
		</div>
		<div class="form-group">
			<label>Số điện thoại</label> <input class="form-control" name="phone"
				placeholder="0xxx xxx xxx">
		</div>
		<div class="form-group">
			<label>Mật khẩu <span class="required">*</span></label> <input
				class="form-control" type="password" name="password" required
				placeholder="Ít nhất 6 ký tự...">
		</div>
		<div class="form-group">
			<label>Xác nhận mật khẩu <span class="required">*</span></label> <input
				class="form-control" type="password" name="confirm" required
				placeholder="Nhập lại mật khẩu...">
		</div>
		<button class="btn btn-primary btn-block" type="submit">Đăng
			ký ngay</button>
	</form>

	<div class="auth-divider"></div>

	<div class="auth-links">
		<a href="${pageContext.request.contextPath}/login">Đã có tài
			khoản? Đăng nhập</a> <span class="sep">·</span> <a
			href="${pageContext.request.contextPath}/home">Trang chủ</a>
	</div>
</div>
