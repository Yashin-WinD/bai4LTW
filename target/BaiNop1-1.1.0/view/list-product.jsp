<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý sản phẩm</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', system-ui, -apple-system, sans-serif; }

        body { background: #f1f5f9; min-height: 100vh; }

        /* ---- Admin Navbar ---- */
        .admin-bar {
            background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%);
            padding: 0 24px;
            display: flex;
            align-items: center;
            gap: 0;
            height: 56px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.3);
        }
        .admin-bar .brand {
            font-weight: 800;
            font-size: 18px;
            color: #6366f1;
            margin-right: 28px;
            text-decoration: none;
            letter-spacing: -0.5px;
        }
        .admin-bar a {
            color: #94a3b8;
            text-decoration: none;
            font-size: 13px;
            font-weight: 600;
            padding: 18px 14px;
            transition: color 0.2s, border-bottom 0.2s;
            border-bottom: 3px solid transparent;
        }
        .admin-bar a:hover, .admin-bar a.active { color: #fff; border-bottom-color: #6366f1; }
        .admin-bar .spacer { flex: 1; }
        .admin-bar .logout { color: #f87171 !important; }

        /* ---- Page Content ---- */
        .page-wrapper { max-width: 1200px; margin: 0 auto; padding: 28px 20px; }

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 22px;
        }
        .page-header h1 { font-size: 22px; font-weight: 700; color: #1e293b; }
        .page-header p { font-size: 13px; color: #64748b; margin-top: 2px; }

        .btn-add-product {
            display: inline-flex;
            align-items: center;
            gap: 6px;
            padding: 10px 20px;
            background: linear-gradient(135deg, #4f46e5, #6366f1);
            color: white;
            text-decoration: none;
            border-radius: 10px;
            font-weight: 700;
            font-size: 14px;
            box-shadow: 0 4px 12px rgba(79,70,229,0.35);
            transition: transform 0.15s, box-shadow 0.15s;
        }
        .btn-add-product:hover { transform: translateY(-1px); box-shadow: 0 6px 16px rgba(79,70,229,0.45); }

        /* ---- Alert flash ---- */
        .flash-ok {
            background: #ecfdf5; color: #065f46; border: 1px solid #a7f3d0;
            padding: 12px 18px; border-radius: 10px; margin-bottom: 18px; font-size: 14px;
        }
        .flash-err {
            background: #fef2f2; color: #991b1b; border: 1px solid #fecaca;
            padding: 12px 18px; border-radius: 10px; margin-bottom: 18px; font-size: 14px;
        }

        /* ---- Table Card ---- */
        .table-card {
            background: #ffffff;
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(15,23,42,0.07);
            overflow: hidden;
        }

        table { width: 100%; border-collapse: collapse; }
        thead tr { background: linear-gradient(90deg, #4f46e5, #6366f1); }
        thead th {
            color: #fff;
            font-size: 12px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            padding: 14px 16px;
            text-align: left;
        }
        thead th:last-child { text-align: center; }

        tbody tr {
            border-bottom: 1px solid #f1f5f9;
            transition: background 0.15s;
        }
        tbody tr:hover { background: #f8fafc; }
        tbody tr:last-child { border-bottom: none; }

        td { padding: 14px 16px; font-size: 14px; color: #374151; vertical-align: middle; }

        td.stt { color: #94a3b8; font-weight: 600; width: 50px; }
        td.col-img { width: 80px; }
        td.col-img img {
            width: 60px; height: 60px; object-fit: cover;
            border-radius: 10px; border: 1px solid #e5e7eb;
        }
        td.col-img .no-img {
            width: 60px; height: 60px; border-radius: 10px;
            background: #e5e7eb; display: flex; align-items: center; justify-content: center;
            font-size: 22px; color: #9ca3af;
        }
        td.col-name { font-weight: 600; color: #1e293b; }
        td.col-price { color: #dc2626; font-weight: 700; }
        td.col-amount .badge {
            display: inline-block;
            padding: 3px 10px; border-radius: 20px; font-size: 12px; font-weight: 700;
        }
        .badge-ok { background: #dcfce7; color: #166534; }
        .badge-low { background: #fef9c3; color: #854d0e; }
        .badge-out { background: #fee2e2; color: #991b1b; }

        td.col-cate .cate-tag {
            display: inline-block;
            background: #ede9fe; color: #5b21b6;
            padding: 3px 10px; border-radius: 20px; font-size: 12px; font-weight: 600;
        }

        td.col-actions { text-align: center; white-space: nowrap; }
        .btn-edit, .btn-delete {
            display: inline-flex; align-items: center; gap: 4px;
            padding: 7px 14px; border-radius: 8px; font-size: 12px; font-weight: 700;
            text-decoration: none; border: none; cursor: pointer; transition: all 0.2s;
        }
        .btn-edit { background: #eff6ff; color: #1d4ed8; margin-right: 6px; }
        .btn-edit:hover { background: #dbeafe; }
        .btn-delete { background: #fef2f2; color: #dc2626; }
        .btn-delete:hover { background: #fee2e2; }

        /* ---- Empty state ---- */
        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: #64748b;
        }
        .empty-state .icon { font-size: 52px; margin-bottom: 12px; }
        .empty-state p { font-size: 15px; }
    </style>
</head>
<body>

    <%-- Admin Navbar --%>
    <nav class="admin-bar">
        <a class="brand" href="${pageContext.request.contextPath}/home">&#9733; UTE Admin</a>
        <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/admin/category/list">Danh mục</a>
        <a href="${pageContext.request.contextPath}/admin/product/list" class="active">Sản phẩm</a>
        <div class="spacer"></div>
        <a href="${pageContext.request.contextPath}/logout" class="logout">Đăng xuất</a>
    </nav>

    <div class="page-wrapper">
        <div class="page-header">
            <div>
                <h1>Quản lý sản phẩm</h1>
                <p>Tổng cộng <strong>${products.size()}</strong> sản phẩm trong hệ thống</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/product/add" class="btn-add-product">
                &#43; Thêm sản phẩm mới
            </a>
        </div>

        <c:if test="${not empty flash}">
            <div class="flash-ok">${flash}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="flash-err">${error}</div>
        </c:if>

        <div class="table-card">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Ảnh</th>
                        <th>Tên sản phẩm</th>
                        <th>Giá</th>
                        <th>Tồn kho</th>
                        <th>Danh mục</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty products}">
                            <c:forEach items="${products}" var="p" varStatus="st">
                                <tr>
                                    <td class="stt">${st.index + 1}</td>
                                    <td class="col-img">
                                        <c:choose>
                                            <c:when test="${not empty p.image}">
                                                <img src="${pageContext.request.contextPath}/image?fname=${p.image}" alt="${p.name}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="no-img">📦</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="col-name">${p.name}</td>
                                    <td class="col-price">
                                        <fmt:formatNumber value="${p.price}" type="number"/> đ
                                    </td>
                                    <td class="col-amount">
                                        <c:choose>
                                            <c:when test="${p.amount == 0}">
                                                <span class="badge badge-out">Hết hàng</span>
                                            </c:when>
                                            <c:when test="${p.amount <= 5}">
                                                <span class="badge badge-low">${p.amount} còn lại</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-ok">${p.amount}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="col-cate">
                                        <span class="cate-tag">${p.category.catename}</span>
                                    </td>
                                    <td class="col-actions">
                                        <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.id}" class="btn-edit">&#9998; Sửa</a>
                                        <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.id}"
                                           class="btn-delete"
                                           onclick="return confirm('Bạn có chắc muốn xóa sản phẩm \'${p.name}\' không?')">
                                           &#128465; Xóa
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7">
                                    <div class="empty-state">
                                        <div class="icon">📦</div>
                                        <p>Chưa có sản phẩm nào. Hãy bấm <strong>+ Thêm sản phẩm mới</strong> để bắt đầu!</p>
                                    </div>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>
