<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Chỉnh sửa danh mục — UTE Admin</title>

<div class="admin-wrapper" style="max-width: 600px;">
	<div class="breadcrumb">
		<a href="${pageContext.request.contextPath}/home">Trang chủ</a> <span
			class="sep">/</span> <a
			href="${pageContext.request.contextPath}/admin/category/list">Danh
			mục</a> <span class="sep">/</span> <span class="current">Chỉnh sửa
			#${category.id}</span>
	</div>

	<div class="form-card">
		<div class="form-card-header"
			style="background: linear-gradient(135deg, #0f4c81 0%, #1d4ed8 100%);">
			<div>
				<h1>Chỉnh sửa danh mục</h1>
				<p>Cập nhật thông tin chi tiết cho danh mục #${category.id}</p>
			</div>
			<a href="${pageContext.request.contextPath}/admin/category/list"
				class="btn-header-back">&#8592; Quay lại</a>
		</div>

		<div class="form-card-body">
			<c:if test="${not empty alert}">
				<div class="alert-error">${alert}</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/admin/category/edit"
				method="post" enctype="multipart/form-data">

				<input type="hidden" name="id" value="${category.id}" />

				<div class="form-group">
					<label for="name">Tên danh mục <span class="required">*</span></label>
					<input type="text" id="name" name="name" class="form-control"
						value="${category.catename != null ? category.catename : category.name}"
						required placeholder="Nhập tên danh mục..." />
				</div>

				<div class="form-group">
					<label>Ảnh đại diện / Icon</label>

					<c:if test="${not empty category.icon}">
						<div class="current-image-row">
							<img
								src="${pageContext.request.contextPath}/image?fname=${category.icon}"
								alt="Icon hiện tại" />
							<div class="info">
								<strong>Ảnh hiện tại</strong> Bỏ trống bên dưới nếu muốn giữ lại
								ảnh cũ
							</div>
						</div>
					</c:if>

					<div class="file-upload-box">
						<input type="file" id="icon" name="icon" accept="image/*"
							onchange="previewImage(event)" />
						<div class="file-upload-icon">📁</div>
						<div class="file-upload-text">
							<em>Bấm để tải ảnh thay thế</em><br> <small
								style="font-size: 11px;">Hỗ trợ PNG, JPG, WEBP</small>
						</div>
						<img id="imagePreview" class="img-preview" alt="Xem trước ảnh mới" />
					</div>
				</div>

				<div class="form-actions">
					<a href="${pageContext.request.contextPath}/admin/category/list"
						class="btn btn-secondary">Hủy bỏ</a>
					<button type="submit" class="btn btn-primary"
						style="background: linear-gradient(135deg, #1d4ed8, #2563eb);">&#10003;
						Lưu thay đổi</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	function previewImage(event) {
		const input = event.target;
		const preview = document.getElementById('imagePreview');
		if (input.files && input.files[0]) {
			const reader = new FileReader();
			reader.onload = function(e) {
				preview.src = e.target.result;
				preview.style.display = 'block';
			}
			reader.readAsDataURL(input.files[0]);
		} else {
			preview.src = '';
			preview.style.display = 'none';
		}
	}
</script>

