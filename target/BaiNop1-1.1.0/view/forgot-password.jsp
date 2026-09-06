<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<title>Quên mật khẩu — UTE Shop</title>
<div class="auth-card">
	<div class="auth-logo">&#9733; UTE Shop</div>
	<h2>Quên mật khẩu?</h2>
	<p class="auth-sub">Đừng lo! Vui lòng nhập tên đăng nhập hoặc email
		của bạn. Chúng tôi sẽ gửi mã xác nhận để đặt lại mật khẩu mới.</p>

	<c:if test="${not empty alert}">
		<div class="alert-error">${alert}</div>
	</c:if>
	<c:if test="${not empty message}">
		<div class="alert-ok">${message}</div>
	</c:if>

	<form action="${pageContext.request.contextPath}/forgot-password"
		method="post">
		<div class="form-group">
			<label>Tên đăng nhập hoặc Email <span class="required">*</span></label>
			<input class="form-control" type="text" name="key" required autofocus
				placeholder="Nhập tên đăng nhập hoặc email...">
		</div>

		<button class="btn btn-primary btn-block" type="submit"
			style="margin-top: 8px;">Gửi mã khôi phục</button>
	</form>

	<div class="auth-divider"></div>

	<div class="auth-links">
		<a href="${pageContext.request.contextPath}/login">&#8592; Quay
			lại đăng nhập</a> <span class="sep">·</span> <a
			href="${pageContext.request.contextPath}/home">Trang chủ</a>
	</div>
</div>
