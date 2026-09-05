package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.ute.service.Productservice;
import vn.ute.service.Impl.ProductserviceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/product/delete" })
public class Productdeletecontroller extends HttpServlet {

	private final Productservice productService = new ProductserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			productService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/admin/product/list");
	}
}
