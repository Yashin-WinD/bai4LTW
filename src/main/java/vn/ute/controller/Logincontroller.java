package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.ute.model.User;
import vn.ute.service.Userservice;
import vn.ute.service.Impl.UserserviceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/login" })
public class Logincontroller extends HttpServlet {

	private final Userservice userService = new UserserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName())) {
					req.setAttribute("username", cookie.getValue());
					break;
				}
			}
		}

		req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		boolean isRememberMe = "on".equals(remember);

		if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			return;
		}

		User user = userService.login(username.trim(), password);

		if (user == null) {
			req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			return;
		}

		if (!user.isActive()) {
			HttpSession session = req.getSession(true);
			session.setAttribute("otpUser", user.getEmail());
			session.setAttribute("otpPurpose", "REGISTER");
			req.setAttribute("alert", "Tài khoản chưa kích hoạt. Vui lòng nhập OTP đã gửi email.");
			req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
			return;
		}

		HttpSession session = req.getSession(true);
		session.setAttribute("account", user);

		if (isRememberMe) {
			saveRememberMe(resp, username.trim());
		} else {
			deleteRememberMe(resp);
		}

		resp.sendRedirect(req.getContextPath() + "/waiting");
	}

	private void saveRememberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie("username", username);
		cookie.setMaxAge(30 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	private void deleteRememberMe(HttpServletResponse response) {
		Cookie cookie = new Cookie("username", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
