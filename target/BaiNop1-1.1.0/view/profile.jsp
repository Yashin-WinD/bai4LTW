<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Thông tin cá nhân — UTE Shop</title>

<div class="container" style="max-width: 680px;">
    <div class="form-card">
        <div class="form-card-header">
            <div>
                <h1>Thông tin cá nhân</h1>
                <p>Cập nhật thông tin tài khoản của bạn</p>
            </div>
        </div>
        <div class="form-card-body">
            <c:if test="${not empty alert}">
                <div class="alert-error">${alert}</div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="alert-ok">${success}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/member/myaccount" method="post"
                  enctype="multipart/form-data">
                <div class="form-group" style="text-align: center; margin-bottom: 24px;">
                    <c:choose>
                        <c:when test="${not empty sessionScope.account.avatar}">
                            <img src="${pageContext.request.contextPath}/image?fname=${sessionScope.account.avatar}"
                                 alt="Ảnh đại diện" style="width: 112px; height: 112px; object-fit: cover; border-radius: 50%; margin: 0 auto 12px;">
                        </c:when>
                        <c:otherwise>
                            <div style="width:112px;height:112px;border-radius:50%;background:#e0e7ff;color:#4f46e5;display:flex;align-items:center;justify-content:center;font-size:42px;font-weight:800;margin:0 auto 12px;">
                                <c:choose>
                                    <c:when test="${not empty sessionScope.account.fullName}">${sessionScope.account.fullName.substring(0, 1).toUpperCase()}</c:when>
                                    <c:otherwise>U</c:otherwise>
                                </c:choose>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <label for="avatar" class="btn btn-secondary">Chọn ảnh đại diện</label>
                    <input id="avatar" name="avatar" type="file" accept="image/png,image/jpeg,image/webp"
                           style="display:none;" onchange="document.getElementById('file-name').textContent = this.files[0] ? this.files[0].name : ''">
                    <div id="file-name" style="font-size:12px;color:#64748b;margin-top:8px;"></div>
                </div>

                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input class="form-control" value="${sessionScope.account.userName}" disabled>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input class="form-control" value="${sessionScope.account.email}" disabled>
                </div>
                <div class="form-group">
                    <label for="fullName">Họ và tên <span class="required">*</span></label>
                    <input id="fullName" name="fullName" class="form-control" required maxlength="100"
                           value="${sessionScope.account.fullName}">
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại</label>
                    <input id="phone" name="phone" class="form-control" inputmode="numeric"
                           pattern="0[0-9]{9,10}" maxlength="11" value="${sessionScope.account.phone}">
                </div>
                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Hủy bỏ</a>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
</div>