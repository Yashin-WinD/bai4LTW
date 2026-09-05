<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa sản phẩm - ${product.name}</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', system-ui, -apple-system, sans-serif; }

        body { background: #f1f5f9; min-height: 100vh; }

        /* ---- Admin Navbar ---- */
        .admin-bar {
            background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%);
            padding: 0 24px;
            display: flex; align-items: center;
            height: 56px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.3);
        }
        .admin-bar .brand { font-weight: 800; font-size: 18px; color: #6366f1; margin-right: 28px; text-decoration: none; }
        .admin-bar a { color: #94a3b8; text-decoration: none; font-size: 13px; font-weight: 600; padding: 18px 14px; transition: color 0.2s; border-bottom: 3px solid transparent; }
        .admin-bar a:hover, .admin-bar a.active { color: #fff; border-bottom-color: #6366f1; }
        .admin-bar .spacer { flex: 1; }
        .admin-bar .logout { color: #f87171 !important; }

        /* ---- Page Layout ---- */
        .page-wrapper { max-width: 680px; margin: 36px auto; padding: 0 20px 40px; }

        /* ---- Breadcrumb ---- */
        .breadcrumb { font-size: 13px; color: #64748b; margin-bottom: 20px; }
        .breadcrumb a { color: #4f46e5; text-decoration: none; }
        .breadcrumb a:hover { text-decoration: underline; }
        .breadcrumb span { margin: 0 6px; }

        /* ---- Card ---- */
        .card {
            background: #fff; border-radius: 18px;
            box-shadow: 0 8px 28px rgba(15,23,42,0.09); overflow: hidden;
        }
        .card-header {
            background: linear-gradient(135deg, #0f4c81 0%, #1d4ed8 100%);
            padding: 24px 30px;
            display: flex; justify-content: space-between; align-items: center;
        }
        .card-header h1 { font-size: 20px; font-weight: 700; color: #fff; }
        .card-header p { font-size: 13px; color: #bfdbfe; margin-top: 3px; }
        .btn-back-header {
            color: #bfdbfe; font-size: 13px; font-weight: 600; text-decoration: none;
            border: 1px solid rgba(255,255,255,0.35); padding: 6px 14px; border-radius: 8px;
            transition: background 0.2s;
        }
        .btn-back-header:hover { background: rgba(255,255,255,0.15); color: #fff; }

        .card-body { padding: 32px 30px; }

        /* ---- Form ---- */
        .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
        .form-group { margin-bottom: 22px; }
        .form-group label {
            display: block; font-size: 13px; font-weight: 700;
            color: #374151; margin-bottom: 7px;
        }
        .required { color: #ef4444; }
        .form-control {
            width: 100%; padding: 11px 14px; font-size: 14px;
            border: 1.5px solid #e2e8f0; border-radius: 10px;
            outline: none; transition: all 0.2s; background: #f8fafc; color: #1e293b;
        }
        .form-control:focus { border-color: #2563eb; background: #fff; box-shadow: 0 0 0 3px rgba(37,99,235,0.12); }
        textarea.form-control { resize: vertical; min-height: 100px; }
        select.form-control { cursor: pointer; }

        /* ---- Current Image Box ---- */
        .current-img-box {
            display: flex; align-items: center; gap: 16px;
            background: #f0f9ff; border: 1.5px solid #bae6fd;
            border-radius: 10px; padding: 14px 18px; margin-bottom: 14px;
        }
        .current-img-box img {
            width: 72px; height: 72px; object-fit: cover;
            border-radius: 10px; border: 1px solid #bae6fd;
        }
        .current-img-info { font-size: 13px; color: #0369a1; }
        .current-img-info strong { color: #0c4a6e; display: block; margin-bottom: 3px; }

        /* ---- File Upload ---- */
        .file-upload-box {
            border: 2px dashed #cbd5e1; border-radius: 10px; padding: 22px;
            text-align: center; background: #f8fafc; cursor: pointer;
            position: relative; transition: all 0.2s;
        }
        .file-upload-box:hover { border-color: #2563eb; background: #eff6ff; }
        .file-upload-box input[type="file"] {
            position: absolute; top: 0; left: 0; width: 100%; height: 100%; opacity: 0; cursor: pointer;
        }
        .upload-icon { font-size: 28px; margin-bottom: 8px; }
        .upload-text { font-size: 13px; color: #64748b; }
        .upload-text span { color: #2563eb; font-weight: 700; }
        .img-preview {
            display: none; max-width: 120px; max-height: 120px;
            margin: 14px auto 0; border-radius: 10px; object-fit: cover;
            border: 2px solid #bae6fd; box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        /* ---- Buttons ---- */
        .form-actions { display: flex; gap: 12px; margin-top: 8px; }
        .btn {
            flex: 1; padding: 13px; font-size: 15px; font-weight: 700;
            border-radius: 10px; border: none; cursor: pointer;
            text-align: center; text-decoration: none;
            transition: all 0.2s; display: inline-block;
        }
        .btn-primary {
            background: linear-gradient(135deg, #1d4ed8, #2563eb);
            color: white; box-shadow: 0 4px 12px rgba(37,99,235,0.3);
        }
        .btn-primary:hover { transform: translateY(-1px); box-shadow: 0 6px 16px rgba(37,99,235,0.4); }
        .btn-secondary { background: #f1f5f9; color: #475569; border: 1.5px solid #e2e8f0; }
        .btn-secondary:hover { background: #e2e8f0; }

        /* ---- Alert ---- */
        .alert-error {
            background: #fef2f2; color: #991b1b; border: 1px solid #fecaca;
            padding: 12px 16px; border-radius: 10px; margin-bottom: 20px; font-size: 14px;
        }

        @media (max-width: 600px) { .form-row { grid-template-columns: 1fr; } }
    </style>
</head>
<body>

    <nav class="admin-bar">
        <a class="brand" href="${pageContext.request.contextPath}/home">&#9733; UTE Admin</a>
        <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/admin/category/list">Danh mục</a>
        <a href="${pageContext.request.contextPath}/admin/product/list" class="active">Sản phẩm</a>
        <div class="spacer"></div>
        <a href="${pageContext.request.contextPath}/logout" class="logout">Đăng xuất</a>
    </nav>

    <div class="page-wrapper">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
            <span>/</span>
            <a href="${pageContext.request.contextPath}/admin/product/list">Sản phẩm</a>
            <span>/</span>
            Chỉnh sửa #${product.id}
        </div>

        <div class="card">
            <div class="card-header">
                <div>
                    <h1>Chỉnh sửa sản phẩm</h1>
                    <p>ID: #${product.id} &mdash; Cập nhật thông tin sản phẩm</p>
                </div>
                <a href="${pageContext.request.contextPath}/admin/product/list" class="btn-back-header">&#8592; Quay lại</a>
            </div>

            <div class="card-body">
                <c:if test="${not empty error}">
                    <div class="alert-error">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/admin/product/edit"
                      method="post"
                      enctype="multipart/form-data">

                    <input type="hidden" name="id" value="${product.id}"/>

                    <div class="form-group">
                        <label for="name">Tên sản phẩm <span class="required">*</span></label>
                        <input type="text" id="name" name="name" class="form-control"
                               required value="${product.name}" placeholder="Tên sản phẩm..."/>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="price">Giá bán (VNĐ) <span class="required">*</span></label>
                            <input type="number" id="price" name="price" class="form-control"
                                   required min="0" step="1000" value="${product.price}"/>
                        </div>
                        <div class="form-group">
                            <label for="amount">Số lượng tồn kho <span class="required">*</span></label>
                            <input type="number" id="amount" name="amount" class="form-control"
                                   required min="0" value="${product.amount}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="categoryId">Danh mục <span class="required">*</span></label>
                        <select id="categoryId" name="categoryId" class="form-control" required>
                            <option value="" disabled>-- Chọn danh mục --</option>
                            <c:forEach items="${categories}" var="cate">
                                <option value="${cate.id}" ${product.category.id == cate.id ? 'selected' : ''}>
                                    ${cate.catename}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="description">Mô tả sản phẩm</label>
                        <textarea id="description" name="description" class="form-control"
                                  placeholder="Mô tả chi tiết sản phẩm...">${product.description}</textarea>
                    </div>

                    <div class="form-group">
                        <label>Ảnh sản phẩm</label>

                        <%-- Hiển thị ảnh hiện tại nếu có --%>
                        <c:if test="${not empty product.image}">
                            <div class="current-img-box">
                                <img src="${pageContext.request.contextPath}/image?fname=${product.image}" alt="${product.name}"/>
                                <div class="current-img-info">
                                    <strong>Ảnh hiện tại</strong>
                                    Chọn ảnh mới bên dưới nếu muốn thay thế. Bỏ trống để giữ ảnh cũ.
                                </div>
                            </div>
                        </c:if>

                        <div class="file-upload-box">
                            <input type="file" id="image" name="image" accept="image/*" onchange="previewImage(event)"/>
                            <div class="upload-icon">🖼️</div>
                            <div class="upload-text">
                                <span>Bấm để tải ảnh thay thế</span><br>
                                <small style="color:#94a3b8; font-size:11px;">Bỏ qua nếu muốn giữ ảnh cũ — PNG, JPG, WEBP</small>
                            </div>
                            <img id="imagePreview" class="img-preview" alt="Xem trước ảnh mới"/>
                        </div>
                    </div>

                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/admin/product/list" class="btn btn-secondary">Hủy bỏ</a>
                        <button type="submit" class="btn btn-primary">&#10003; Lưu thay đổi</button>
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
                };
                reader.readAsDataURL(input.files[0]);
            } else {
                preview.src = '';
                preview.style.display = 'none';
            }
        }
    </script>
</body>
</html>
