<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Thêm sản phẩm mới — UTE Admin</title>

<div class="admin-wrapper" style="max-width: 800px;">
	<div class="breadcrumb">
		<a href="${pageContext.request.contextPath}/home">Trang chủ</a> <span
			class="sep">/</span> <a
			href="${pageContext.request.contextPath}/admin/product/list">Sản
			phẩm</a> <span class="sep">/</span> <span class="current">Thêm mới</span>
	</div>

	<div class="form-card">
		<div class="form-card-header">
			<div>
				<h1>Thêm sản phẩm mới</h1>
				<p>Điền thông tin bên dưới để đăng bán sản phẩm</p>
			</div>
			<a href="${pageContext.request.contextPath}/admin/product/list"
				class="btn-header-back">&#8592; Quay lại</a>
		</div>

		<div class="form-card-body">
			<c:if test="${not empty alert}">
				<div class="alert-error">${alert}</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/admin/product/add"
				method="post" enctype="multipart/form-data">

				<div class="form-group">
					<label>Tên sản phẩm <span class="required">*</span></label> <input
						type="text" name="name" class="form-control" required
						placeholder="Nhập tên sản phẩm..." />
				</div>

				<div class="form-row-2">
					<div class="form-group">
						<label>Giá bán (VNĐ) <span class="required">*</span></label> <input
							type="number" name="price" class="form-control" required min="0"
							placeholder="0" />
					</div>
					<div class="form-group">
						<label>Số lượng tồn kho <span class="required">*</span></label> <input
							type="number" name="amount" class="form-control" required min="0"
							placeholder="0" />
					</div>
				</div>

				<div class="form-group">
					<label>Danh mục <span class="required">*</span></label> <select
						name="categoryId" class="form-control" required>
						<option value="" disabled selected>-- Chọn danh mục --</option>
						<c:forEach items="${categories}" var="c">
							<option value="${c.id}">${c.catename}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label>Mô tả chi tiết</label>
					<textarea name="description" class="form-control" rows="4"
						placeholder="Nhập mô tả sản phẩm..."></textarea>
				</div>

				<div class="form-group">
					<label>Hình ảnh sản phẩm</label>
					<div class="file-upload-box">
						<input type="file" id="image" name="image" accept="image/*"
							onchange="previewImage(event)" />
						<div class="file-upload-icon">📸</div>
						<div class="file-upload-text">
							<em>Bấm để chọn ảnh</em> hoặc kéo thả vào đây<br> <small
								style="font-size: 11px;">Hỗ trợ PNG, JPG, WEBP</small>
						</div>
						<img id="imagePreview" class="img-preview" alt="Xem trước ảnh" />
					</div>
				</div>

				<div class="form-actions">
					<a href="${pageContext.request.contextPath}/admin/product/list"
						class="btn btn-secondary">Hủy bỏ</a>
					<button type="submit" class="btn btn-primary">&#10003;
						Thêm sản phẩm</button>
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

