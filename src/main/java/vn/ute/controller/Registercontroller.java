package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.ute.model.User;
import vn.ute.service.Userservice;
import vn.ute.service.Impl.UserserviceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/register" })
public class Registercontroller extends HttpServlet {

	private final Userservice userService = new UserserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = trim(req.getParameter("username"));
		String email = trim(req.getParameter("email"));
		String fullName = trim(req.getParameter("fullName"));
		String password = req.getParameter("password");
		String confirm = req.getParameter("confirm");
		String phone = trim(req.getParameter("phone"));

		if (username.isEmpty() || email.isEmpty() || password == null || password.isEmpty()) {
			req.setAttribute("alert", "Vui lòng nhập đầy đủ username, email và mật khẩu");
			req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
			return;
		}
		if (!password.equals(confirm)) {
			req.setAttribute("alert", "Mật khẩu xác nhận không khớp");
			req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
			return;
		}

		User user = new User();
		user.setUserName(username);
		user.setEmail(email);
		user.setFullName(fullName.isEmpty() ? username : fullName);
		user.setPassWord(password);
		user.setPhone(phone);

		String error = userService.register(user);
		if (error != null && !"OTP_CONSOLE".equals(error)) {
			req.setAttribute("alert", error);
			req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
			return;
		}

		HttpSession session = req.getSession(true);
		session.setAttribute("otpUser", email);
		session.setAttribute("otpPurpose", "REGISTER");
		if ("OTP_CONSOLE".equals(error)) {
			req.setAttribute("alert",
					"Chưa cấu hình SMTP. Mã OTP đã in trên Console Eclipse (xem log Tomcat).");
		} else {
			req.setAttribute("message", "Đã gửi OTP tới email. Vui lòng kiểm tra hộp thư.");
		}
		req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
	}

	private String trim(String value) {
		return value == null ? "" : value.trim();
	}
}
