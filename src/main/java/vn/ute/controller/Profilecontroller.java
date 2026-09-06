package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.ute.model.User;
import vn.ute.service.Impl.UserserviceImpl;
import vn.ute.service.Userservice;
import vn.ute.util.UploadUtil;
import vn.ute.util.ValidationUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/member/myaccount" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 12)
public class Profilecontroller extends HttpServlet {

	private final Userservice userService = new UserserviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!loadAccount(req, resp)) {
			return;
		}
		req.getRequestDispatcher("/view/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!loadAccount(req, resp)) {
			return;
		}

		HttpSession session = req.getSession(false);
		User account = (User) session.getAttribute("account");
		String fullName = ValidationUtil.trim(req.getParameter("fullName"));
		String phone = ValidationUtil.trim(req.getParameter("phone"));

		if (fullName.isEmpty() || fullName.length() > 100) {
			forwardWithError(req, resp, "Họ và tên bắt buộc và tối đa 100 ký tự");
			return;
		}
		if (!ValidationUtil.isPhone(phone)) {
			forwardWithError(req, resp, "Số điện thoại phải có 10-11 chữ số và bắt đầu bằng 0");
			return;
		}

		Part avatar = req.getPart("avatar");
		if (avatar != null && avatar.getSize() > 0 && !UploadUtil.isValidImage(avatar)) {
			forwardWithError(req, resp, "Ảnh phải là PNG, JPG, JPEG hoặc WEBP và không quá 10 MB");
			return;
		}

		String oldAvatar = account.getAvatar();
		account.setFullName(fullName);
		account.setPhone(phone);
		if (avatar != null && avatar.getSize() > 0) {
			String stored = UploadUtil.save(avatar, "user");
			account.setAvatar(stored);
		}
		userService.updateProfile(account);
		session.setAttribute("account", account);
		if (avatar != null && avatar.getSize() > 0 && oldAvatar != null && !oldAvatar.isBlank()) {
			UploadUtil.delete(oldAvatar);
		}
		req.setAttribute("success", "Cập nhật thông tin cá nhân thành công");
		req.getRequestDispatcher("/view/profile.jsp").forward(req, resp);
	}

	private boolean loadAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !(session.getAttribute("account") instanceof User)) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return false;
		}
		return true;
	}

	private void forwardWithError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("alert", message);
		req.getRequestDispatcher("/view/profile.jsp").forward(req, resp);
	}
}