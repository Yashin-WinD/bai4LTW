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
@WebServlet(urlPatterns = { "", "/home" })
public class Homecontroller extends HttpServlet {

	private final Productservice productService = new ProductserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> newest = productService.findNewest(Constant.HOME_NEWEST_SIZE);
		req.setAttribute("newest", newest);
		req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
	}
}
