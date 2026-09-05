package vn.ute.util;

import java.security.SecureRandom;

public class OtpUtil {
	private static final SecureRandom RANDOM = new SecureRandom();

	public static String generate() {
		return String.format("%06d", RANDOM.nextInt(1_000_000));
	}
}
