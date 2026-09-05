package vn.ute.controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig; // Import annotation này
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import vn.ute.model.Category;
import vn.ute.service.Categoryservice;
import vn.ute.service.Impl.CategoryserviceImpl;
import vn.ute.util.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class Categoryeditcontroller extends HttpServlet {

    private final Categoryservice cateService = new CategoryserviceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        if (id != null && !id.trim().isEmpty()) {
            Category category = cateService.get(Integer.parseInt(id));
            req.setAttribute("category", category);
        }

        req.getRequestDispatcher("/view/edit-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            return;
        }

        int id = Integer.parseInt(idStr);
        String name = req.getParameter("name");

        Category category = cateService.get(id);
        if (category == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            return;
        }

        category.setCatename(name);

        Part filePart = req.getPart("icon");
        if (filePart != null && filePart.getSize() > 0) {
            String originalFileName = filePart.getSubmittedFileName();
            if (originalFileName != null && !originalFileName.isEmpty()) {
                int index = originalFileName.lastIndexOf(".");
                String ext = (index > 0) ? originalFileName.substring(index + 1) : "";
                String fileName = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);

                File uploadDir = new File(Constant.DIR + "/category");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                filePart.write(Constant.DIR + "/category/" + fileName);
                category.setIcon("category/" + fileName);
            }
        }

        cateService.edit(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}