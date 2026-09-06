package vn.ute.service;

import vn.ute.model.User;

public interface Userservice {
	User login(String username, String password);

	User get(String username);

	User findByUsernameOrEmail(String key);

	String register(User user);

	boolean sendOtp(User user, String mailTitle);

	boolean verifyActivateOtp(String emailOrUsername, String otp);

	boolean verifyForgotOtp(String emailOrUsername, String otp);

	boolean resetPassword(String emailOrUsername, String newPassword);

	void updateProfile(User user);
}
