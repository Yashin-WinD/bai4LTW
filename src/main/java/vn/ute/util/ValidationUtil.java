package vn.ute.util;

import java.util.regex.Pattern;

public final class ValidationUtil {

    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern USERNAME = Pattern.compile("^[A-Za-z0-9_.-]{3,30}$");
    private static final Pattern PHONE = Pattern.compile("^0[0-9]{9,10}$");
    private static final Pattern OTP = Pattern.compile("^[0-9]{6}$");

    private ValidationUtil() {
    }

    public static String trim(String value) {
        return value == null ? "" : value.trim();
    }

    public static boolean isEmail(String value) {
        return value != null && EMAIL.matcher(value).matches();
    }

    public static boolean isUsername(String value) {
        return value != null && USERNAME.matcher(value).matches();
    }

    public static boolean isPhone(String value) {
        return value == null || value.isEmpty() || PHONE.matcher(value).matches();
    }

    public static boolean isPassword(String value) {
        return value != null && value.length() >= 6 && value.length() <= 100;
    }

    public static boolean isOtp(String value) {
        return value != null && OTP.matcher(value).matches();
    }
}