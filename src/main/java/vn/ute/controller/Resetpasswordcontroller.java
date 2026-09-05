package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.ute.service.Userservice;
import vn.ute.service.Impl.UserserviceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/reset-password" })
public class Resetpasswordcontroller extends HttpServlet {

	private final Userservice userService = new UserserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !Boolean.TRUE.equals(session.getAttribute("resetAllowed"))) {
			resp.sendRedirect(req.getContextPath() + "/forgot-password");
			return;
		}
		req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !Boolean.TRUE.equals(session.getAttribute("resetAllowed"))
				|| session.getAttribute("otpUser") == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot-password");
			return;
		}

		String password = req.getParameter("password");
		String confirm = req.getParameter("confirm");
		if (password == null || password.isEmpty() || !password.equals(confirm)) {
			req.setAttribute("alert", "Mật khẩu không khớp hoặc để trống");
			req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
			return;
		}

		String otpUser = (String) session.getAttribute("otpUser");
		userService.resetPassword(otpUser, password);
		session.removeAttribute("otpUser");
		session.removeAttribute("otpPurpose");
		session.removeAttribute("resetAllowed");
		req.setAttribute("success", "Đặt lại mật khẩu thành công. Hãy đăng nhập.");
		req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
	}
}
