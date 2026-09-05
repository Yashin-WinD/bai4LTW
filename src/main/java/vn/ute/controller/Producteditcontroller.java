package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import vn.ute.model.Product;
import vn.ute.service.Categoryservice;
import vn.ute.service.Productservice;
import vn.ute.service.Impl.CategoryserviceImpl;
import vn.ute.service.Impl.ProductserviceImpl;
import vn.ute.util.UploadUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/product/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class Producteditcontroller extends HttpServlet {

	private final Productservice productService = new ProductserviceImpl();
	private final Categoryservice cateService = new CategoryserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Product product = productService.get(id);
			if (product == null) {
				resp.sendRedirect(req.getContextPath() + "/admin/product/list");
				return;
			}
			req.setAttribute("product", product);
			req.setAttribute("categories", cateService.getAll());
			req.getRequestDispatcher("/view/edit-product.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		if (idStr == null || idStr.isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
			return;
		}
		Product product = Productaddcontroller.bindProduct(req);
		product.setId(Integer.parseInt(idStr));
		Part filePart = req.getPart("image");
		String stored = UploadUtil.save(filePart, "product");
		if (stored != null) {
			product.setImage(stored);
		}
		productService.edit(product);
		resp.sendRedirect(req.getContextPath() + "/admin/product/list");
	}
}
