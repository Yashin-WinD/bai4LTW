package vn.ute.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.ute.service.Categoryservice;
import vn.ute.service.Impl.CategoryserviceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category/delete" })
public class Categorydeletecontroller extends HttpServlet {

    private final Categoryservice cateService = new CategoryserviceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");


        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                cateService.delete(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}