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
@WebServlet(urlPatterns = { "/verify-otp" })
public class Verifyotpcontroller extends HttpServlet {

	private final Userservice userService = new UserserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("otpUser") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String otpUser = (String) session.getAttribute("otpUser");
		String purpose = (String) session.getAttribute("otpPurpose");
		boolean resend = "1".equals(req.getParameter("resend"));

		if (resend) {
			User user = userService.findByUsernameOrEmail(otpUser);
			if (user != null) {
				String title = "REGISTER".equals(purpose) ? "Kich hoat tai khoan" : "Dat lai mat khau";
				boolean sent = userService.sendOtp(user, title);
				req.setAttribute("message", sent ? "Đã gửi lại OTP."
						: "Không gửi được email. Xem OTP trên Console Eclipse.");
			}
			req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
			return;
		}

		String otp = req.getParameter("otp");
		if (!ValidationUtil.isOtp(otp)) {
			req.setAttribute("alert", "OTP phải gồm đúng 6 chữ số");
			req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
			return;
		}
		if ("REGISTER".equals(purpose)) {
			if (userService.verifyActivateOtp(otpUser, otp)) {
				session.removeAttribute("otpUser");
				session.removeAttribute("otpPurpose");
				req.setAttribute("success", "Kích hoạt thành công. Bạn có thể đăng nhập.");
				req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			} else {
				req.setAttribute("alert", "OTP không đúng hoặc đã hết hạn.");
				req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
			}
			return;
		}

		if ("FORGOT".equals(purpose)) {
			if (userService.verifyForgotOtp(otpUser, otp)) {
				session.setAttribute("resetAllowed", true);
				resp.sendRedirect(req.getContextPath() + "/reset-password");
			} else {
				req.setAttribute("alert", "OTP không đúng hoặc đã hết hạn.");
				req.getRequestDispatcher("/view/verify-otp.jsp").forward(req, resp);
			}
		}
	}
}
