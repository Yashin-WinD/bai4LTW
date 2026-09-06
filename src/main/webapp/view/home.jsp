<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<title>Trang chủ - UTE Shop</title>

<div class="container">
	<h2 class="page-title">10 sản phẩm mới nhất</h2>

	<div class="grid">
		<c:forEach items="${newest}" var="p">
			<a class="card"
				href="${pageContext.request.contextPath}/product/detail?id=${p.id}">

				<c:choose>
					<c:when test="${not empty p.image}">
						<img
							src="${pageContext.request.contextPath}/image?fname=${p.image}"
							alt="${p.name}">
					</c:when>

					<c:otherwise>
						<div class="thumb"></div>
					</c:otherwise>
				</c:choose>

				<div class="card-body">
					<h3>${p.name}</h3>

					<div class="price">
						<fmt:formatNumber value="${p.price}" type="number" />
						đ
					</div>

					<div class="muted">${p.category.catename}</div>
				</div>
			</a>
		</c:forEach>
	</div>

	<c:if test="${empty newest}">
		<div class="text-center p-5 text-secondary">
			<p>
				Chưa có sản phẩm. <a
					href="${pageContext.request.contextPath}/admin/product/add">
					Thêm sản phẩm </a>
			</p>
		</div>
	</c:if>
</div>