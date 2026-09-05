package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.ute.model.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/waiting" })
public class Waitingcontroller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		if (session != null && session.getAttribute("account") != null) {
			User user = (User) session.getAttribute("account");
			if (user.getRoleId() == 1 || user.getRoleId() == 2) {
				resp.sendRedirect(req.getContextPath() + "/admin/category/list");
			} else {
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
