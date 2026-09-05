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
import vn.ute.service.Productservice;
import vn.ute.service.Impl.CategoryserviceImpl;
import vn.ute.service.Impl.ProductserviceImpl;
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
		Product product = bindProduct(req);
		product.setCreatedDate(new Date());
		Part filePart = req.getPart("image");
		String stored = UploadUtil.save(filePart, "product");
		if (stored != null) {
			product.setImage(stored);
		}
		productService.insert(product);
		resp.sendRedirect(req.getContextPath() + "/admin/product/list");
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
