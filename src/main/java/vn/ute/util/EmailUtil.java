package vn.ute.util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

	public static boolean send(String to, String subject, String htmlBody) {
		if (Constant.EMAIL_USER.contains("your.email") || Constant.EMAIL_PASS.contains("your-app-password")) {
			System.err.println("Chua cau hinh EMAIL_USER / EMAIL_PASS trong Constant.java");
			return false;
		}
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", Constant.SMTP_HOST);
			props.put("mail.smtp.port", Constant.SMTP_PORT);
			props.put("mail.smtp.ssl.trust", Constant.SMTP_HOST);

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Constant.EMAIL_USER, Constant.EMAIL_PASS);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constant.EMAIL_USER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(htmlBody, "text/html; charset=UTF-8");
			Transport.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String otpHtml(String title, String otp) {
		return "<div style='font-family:Segoe UI,sans-serif;max-width:480px'>"
				+ "<h2 style='color:#4f46e5'>" + title + "</h2>"
				+ "<p>Mã OTP của bạn (hiệu lực " + Constant.OTP_MINUTES + " phút):</p>"
				+ "<p style='font-size:28px;letter-spacing:6px;font-weight:700;color:#111827'>" + otp + "</p>"
				+ "<p style='color:#6b7280;font-size:13px'>Nếu bạn không yêu cầu, hãy bỏ qua email này.</p>"
				+ "</div>";
	}
}
