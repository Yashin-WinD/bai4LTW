<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Xác nhận OTP — UTE Shop</title>
<style>
.otp-input {
	letter-spacing: 12px;
	font-size: 22px;
	font-weight: 800;
	text-align: center;
}

.otp-timer {
	text-align: center;
	font-size: 13px;
	color: #64748b;
	margin-bottom: 20px;
	padding: 10px 14px;
	background: #f8fafc;
	border-radius: 8px;
	border: 1px solid #e2e8f0;
}

.otp-timer strong {
	color: #4f46e5;
}
</style>
<div class="auth-card">
	<div class="auth-logo">&#9733; UTE Shop</div>
	<h2>Xác nhận OTP</h2>
	<p class="auth-sub">Nhập mã 6 chữ số để kích hoạt tài khoản</p>

	<c:if test="${not empty alert}">
		<div class="alert-error">${alert}</div>
	</c:if>
	<c:if test="${not empty message}">
		<div class="alert-ok">${message}</div>
	</c:if>

	<div class="otp-timer">
		Mã đã gửi tới <strong>${sessionScope.otpUser}</strong> &mdash; hiệu
		lực <strong>5 phút</strong>
	</div>

	<form action="${pageContext.request.contextPath}/verify-otp"
		method="post">
		<div class="form-group">
			<label style="text-align: center; display: block;">Mã OTP</label> <input
				class="form-control otp-input" name="otp" maxlength="6" required
				placeholder="______" autocomplete="one-time-code">
		</div>
		<button class="btn btn-primary btn-block" type="submit">Xác
			nhận</button>
	</form>

	<form action="${pageContext.request.contextPath}/verify-otp"
		method="post" style="margin-top: 10px">
		<input type="hidden" name="resend" value="1">
		<button class="btn btn-secondary btn-block" type="submit">Gửi
			lại mã OTP</button>
	</form>

	<div class="auth-divider"></div>
	<div class="auth-links">
		<a href="${pageContext.request.contextPath}/home">&#8592; Về trang
			chủ</a>
	</div>
</div>

