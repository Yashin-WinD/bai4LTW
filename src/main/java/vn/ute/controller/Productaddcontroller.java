package vn.ute.controller;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.ute.model.Category;
import vn.ute.model.Product;
import vn.ute.service.Categoryservice;
import vn.ute.service.Impl.CategoryserviceImpl;
import vn.ute.service.Impl.ProductserviceImpl;
import vn.ute.service.Productservice;
import vn.ute.util.UploadUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/product/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class Productaddcontroller extends HttpServlet {

	private final Productservice productService = new ProductserviceImpl();
	private final Categoryservice cateService = new CategoryserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", cateService.getAll());
		req.getRequestDispatcher("/view/add-product.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String validationError = validate(req);
		if (validationError != null) {
			req.setAttribute("alert", validationError);
			req.setAttribute("categories", cateService.getAll());
			req.getRequestDispatcher("/view/add-product.jsp").forward(req, resp);
			return;
		}
		Product product = bindProduct(req);
		product.setCreatedDate(new Date());
		Part filePart = req.getPart("image");
		if (filePart != null && filePart.getSize() > 0 && !UploadUtil.isValidImage(filePart)) {
			req.setAttribute("alert", "Ảnh phải là PNG, JPG, JPEG hoặc WEBP và không quá 10 MB");
			req.setAttribute("categories", cateService.getAll());
			req.getRequestDispatcher("/view/add-product.jsp").forward(req, resp);
			return;
		}
		String stored = UploadUtil.save(filePart, "product");
		if (stored != null) {
			product.setImage(stored);
		}
		productService.insert(product);
		resp.sendRedirect(req.getContextPath() + "/admin/product/list");
	}

	static String validate(HttpServletRequest req) {
		String name = req.getParameter("name");
		if (name == null || name.trim().isEmpty() || name.trim().length() > 150) {
			return "Tên sản phẩm bắt buộc và tối đa 150 ký tự";
		}
		try {
			double price = Double.parseDouble(req.getParameter("price"));
			if (!Double.isFinite(price) || price < 0) {
				return "Giá bán phải là số không âm";
			}
		} catch (Exception e) {
			return "Giá bán không hợp lệ";
		}
		try {
			if (Integer.parseInt(req.getParameter("amount")) < 0) {
				return "Số lượng tồn kho không được âm";
			}
		} catch (Exception e) {
			return "Số lượng tồn kho không hợp lệ";
		}
		try {
			if (Integer.parseInt(req.getParameter("categoryId")) <= 0) {
				return "Vui lòng chọn danh mục";
			}
		} catch (Exception e) {
			return "Danh mục không hợp lệ";
		}
		return null;
	}

	static Product bindProduct(HttpServletRequest req) {
		Product product = new Product();
		product.setName(req.getParameter("name"));
		product.setDescription(req.getParameter("description"));
		try {
			product.setPrice(Double.parseDouble(req.getParameter("price")));
		} catch (Exception e) {
			product.setPrice(0);
		}
		try {
			product.setAmount(Integer.parseInt(req.getParameter("amount")));
		} catch (Exception e) {
			product.setAmount(0);
		}
		try {
			int cateId = Integer.parseInt(req.getParameter("categoryId"));
			Category category = new Category();
			category.setId(cateId);
			product.setCategory(category);
		} catch (Exception ignored) {
		}
		return product;
	}
}
