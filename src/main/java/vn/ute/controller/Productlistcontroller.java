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

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/product/list" })
public class Productlistcontroller extends HttpServlet {

	private final Productservice productService = new ProductserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = productService.getAll();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/view/list-product.jsp").forward(req, resp);
	}
}
