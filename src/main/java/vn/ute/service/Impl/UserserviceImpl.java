package vn.ute.service.Impl;

import java.sql.Date;
import java.util.Calendar;

import vn.ute.DAO.UserDAO;
import vn.ute.DAO.Impl.UserDAOImpl;
import vn.ute.model.User;
import vn.ute.service.Userservice;
import vn.ute.util.Constant;
import vn.ute.util.EmailUtil;
import vn.ute.util.OtpUtil;

public class UserserviceImpl implements Userservice {
	UserDAO userDao = new UserDAOImpl();

	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

	@Override
	public User findByUsernameOrEmail(String key) {
		return userDao.findByUsernameOrEmail(key);
	}

	@Override
	public String register(User user) {
		if (userDao.existsByUsername(user.getUserName())) {
			return "Tên đăng nhập đã tồn tại";
		}
		if (userDao.existsByEmail(user.getEmail())) {
			return "Email đã được sử dụng";
		}
		user.setRoleId(3);
		user.setStatus(0);
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		String otp = OtpUtil.generate();
		user.setOtp(otp);
		user.setOtpExpire(expireAt());
		userDao.insert(user);
		System.out.println("OTP dang ky [" + user.getEmail() + "]: " + otp);
		boolean sent = EmailUtil.send(user.getEmail(), "Kich hoat tai khoan",
				EmailUtil.otpHtml("Kích hoạt tài khoản", otp));
		if (!sent) {
			return "OTP_CONSOLE";
		}
		return null;
	}

	@Override
	public boolean sendOtp(User user, String mailTitle) {
		String otp = OtpUtil.generate();
		user.setOtp(otp);
		user.setOtpExpire(expireAt());
		userDao.update(user);
		System.out.println("OTP [" + user.getEmail() + "]: " + otp);
		return EmailUtil.send(user.getEmail(), mailTitle, EmailUtil.otpHtml(mailTitle, otp));
	}

	@Override
	public boolean verifyActivateOtp(String emailOrUsername, String otp) {
		User user = userDao.findByUsernameOrEmail(emailOrUsername);
		if (!otpValid(user, otp)) {
			return false;
		}
		user.setStatus(1);
		user.setOtp(null);
		user.setOtpExpire(null);
		userDao.update(user);
		return true;
	}

	@Override
	public boolean verifyForgotOtp(String emailOrUsername, String otp) {
		User user = userDao.findByUsernameOrEmail(emailOrUsername);
		return otpValid(user, otp);
	}

	@Override
	public boolean resetPassword(String emailOrUsername, String newPassword) {
		User user = userDao.findByUsernameOrEmail(emailOrUsername);
		if (user == null) {
			return false;
		}
		user.setPassWord(newPassword);
		user.setOtp(null);
		user.setOtpExpire(null);
		userDao.update(user);
		return true;
	}

	private boolean otpValid(User user, String otp) {
		if (user == null || otp == null || user.getOtp() == null) {
			return false;
		}
		if (user.getOtpExpire() == null || user.getOtpExpire().before(new java.util.Date())) {
			return false;
		}
		return user.getOtp().equals(otp.trim());
	}

	private java.util.Date expireAt() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, Constant.OTP_MINUTES);
		return cal.getTime();
	}
}
