package vn.ute.util;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.Part;

public class UploadUtil {

	public static boolean isValidImage(Part part) {
		if (part == null || part.getSize() <= 0 || part.getSize() > 10 * 1024 * 1024) {
			return false;
		}
		String fileName = part.getSubmittedFileName();
		if (fileName == null || fileName.isBlank()
				|| part.getContentType() == null || !part.getContentType().startsWith("image/")) {
			return false;
		}
		int index = fileName.lastIndexOf('.');
		if (index < 0) {
			return false;
		}
		String extension = fileName.substring(index + 1).toLowerCase();
		return extension.equals("png") || extension.equals("jpg")
				|| extension.equals("jpeg") || extension.equals("webp");
	}

	public static String save(Part part, String folder) throws IOException {
		if (part == null || part.getSize() <= 0) {
			return null;
		}
		String originalFileName = part.getSubmittedFileName();
		if (originalFileName == null || originalFileName.isEmpty()) {
			return null;
		}
		int index = originalFileName.lastIndexOf('.');
		String ext = (index > 0) ? originalFileName.substring(index + 1) : "";
		String fileName = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);

		File uploadDir = new File(Constant.DIR + File.separator + folder);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		part.write(uploadDir.getAbsolutePath() + File.separator + fileName);
		return folder + "/" + fileName;
	}

	public static void delete(String relativePath) {
		if (relativePath == null || relativePath.isBlank()) {
			return;
		}
		File file = new File(Constant.DIR + File.separator + relativePath.replace("/", File.separator));
		if (file.exists()) {
			file.delete();
		}
	}
}
