<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Thêm danh mục mới — UTE Admin</title>

<div class="admin-wrapper" style="max-width: 600px;">
	<div class="breadcrumb">
		<a href="${pageContext.request.contextPath}/home">Trang chủ</a> <span
			class="sep">/</span> <a
			href="${pageContext.request.contextPath}/admin/category/list">Danh
			mục</a> <span class="sep">/</span> <span class="current">Thêm mới</span>
	</div>

	<div class="form-card">
		<div class="form-card-header">
			<div>
				<h1>Thêm danh mục mới</h1>
				<p>Điền thông tin bên dưới để tạo danh mục</p>
			</div>
			<a href="${pageContext.request.contextPath}/admin/category/list"
				class="btn-header-back">&#8592; Quay lại</a>
		</div>

		<div class="form-card-body">
			<c:if test="${not empty alert}">
				<div class="alert-error">${alert}</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/admin/category/add"
				method="post" enctype="multipart/form-data">

				<div class="form-group">
					<label for="name">Tên danh mục <span class="required">*</span></label>
					<input type="text" id="name" name="name" class="form-control"
						required placeholder="Ví dụ: Điện thoại, Laptop, Phụ kiện..." />
				</div>

				<div class="form-group">
					<label>Hình ảnh / Icon danh mục</label>
					<div class="file-upload-box">
						<input type="file" id="icon" name="icon" accept="image/*"
							onchange="previewImage(event)" />
						<div class="file-upload-icon">📁</div>
						<div class="file-upload-text">
							<em>Bấm để chọn ảnh</em> hoặc kéo thả vào đây<br> <small
								style="font-size: 11px;">Hỗ trợ PNG, JPG, WEBP</small>
						</div>
						<img id="imagePreview" class="img-preview" alt="Xem trước ảnh" />
					</div>
				</div>

				<div class="form-actions">
					<a href="${pageContext.request.contextPath}/admin/category/list"
						class="btn btn-secondary">Hủy bỏ</a>
					<button type="submit" class="btn btn-primary">&#10003;
						Thêm danh mục</button>
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

