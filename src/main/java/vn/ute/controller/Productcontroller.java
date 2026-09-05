package vn.ute.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.ute.model.Product;
import vn.ute.service.Productservice;
import vn.ute.service.Impl.ProductserviceImpl;
import vn.ute.util.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/product" })
public class Productcontroller extends HttpServlet {

	private final Productservice productService = new ProductserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		try {
			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception ignored) {
		}
		if (page < 1) {
			page = 1;
		}
		int pageSize = Constant.PRODUCT_PAGE_SIZE;
		long total = productService.count();
		int totalPages = (int) Math.ceil(total / (double) pageSize);
		if (totalPages == 0) {
			totalPages = 1;
		}
		if (page > totalPages) {
			page = totalPages;
		}
		List<Product> products = productService.findPage(page, pageSize);
		req.setAttribute("products", products);
		req.setAttribute("page", page);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("total", total);
		req.getRequestDispatcher("/view/product-list.jsp").forward(req, resp);
	}
}
