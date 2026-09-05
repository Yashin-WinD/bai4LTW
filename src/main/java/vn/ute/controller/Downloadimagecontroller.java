package vn.ute.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.ute.util.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/image") // Gọi dạng: /image?fname=category/123.jpg
public class Downloadimagecontroller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fileName = req.getParameter("fname");
        if (fileName == null || fileName.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File file = new File(Constant.DIR + "/" + fileName);

        if (file.exists()) {
       
            String contentType = getServletContext().getMimeType(file.getName());
            if (contentType == null) {
                contentType = "image/jpeg";
            }
            resp.setContentType(contentType);

       
            try (FileInputStream fis = new FileInputStream(file)) {
            	fis.transferTo(resp.getOutputStream());
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}