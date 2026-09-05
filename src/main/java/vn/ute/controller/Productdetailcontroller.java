package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.ute.model.Product;
import vn.ute.service.Productservice;
import vn.ute.service.Impl.ProductserviceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/product/detail" })
public class Productdetailcontroller extends HttpServlet {

	private final Productservice productService = new ProductserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Product product = productService.get(id);
			if (product == null) {
				resp.sendRedirect(req.getContextPath() + "/product");
				return;
			}
			req.setAttribute("product", product);
			req.getRequestDispatcher("/view/product-detail.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/product");
		}
	}
}
