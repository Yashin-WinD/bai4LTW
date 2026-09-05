package vn.ute.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.ute.model.Category;
import vn.ute.service.Categoryservice;
import vn.ute.service.Impl.CategoryserviceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category/list" })
public class Categorycontroller extends HttpServlet {

    private final Categoryservice cateService = new CategoryserviceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        List<Category> cateList = cateService.getAll();
        req.setAttribute("cateList", cateList);


        req.getRequestDispatcher("/view/list-category.jsp").forward(req, resp);
    }
}