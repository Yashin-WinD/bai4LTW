package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.ute.model.User;
import vn.ute.service.Impl.UserserviceImpl;
import vn.ute.service.Userservice;
import vn.ute.util.ValidationUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/forgot-password" })
public class Forgotpasswordcontroller extends HttpServlet {

	private final Userservice userService = new UserserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = ValidationUtil.trim(req.getParameter("key"));
		if (key.isEmpty() || key.length() > 100) {
			req.setAttribute("alert", "Nhập username hoặc email");
			req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
			return;
		}

		User user = userService.findByUsernameOrEmail(key.trim());
		if (user == null) {
			req.setAttribute("alert", "Không tìm thấy tài khoản");
			req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
			return;
		}

		boolean sent = userService.sendOtp(user, "Dat lai mat khau");
		HttpSession session = req.getSession(true);
		session.setAttribute("otpUser", user.getEmail());
		session.setAttribute("otpPurpose", "FORGOT");
		session.removeAttribute("resetAllowed");
		req.setAttribute("message", sent ? "Đã gửi OTP tới email."
				: "Không gửi được email. Xem OTP trên Console Eclipse.");
		req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
	}
}
