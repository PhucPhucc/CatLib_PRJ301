/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Controller.admin;

import com.CatLib.DAO.AuthorDAO;
import com.CatLib.DAO.BookDAO;
import com.CatLib.DAO.CategoryDAO;
import com.CatLib.Model.Author;
import com.CatLib.Model.Book;
import com.CatLib.Model.Category;
import com.CatLib.Ultis.AdminUltil;
import com.CatLib.Ultis.MyUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tvphu
 */
@WebServlet(urlPatterns = {"/admin/book-manager/add"})
public class AddBookManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

//        List<Category> categories = CategoryDAO.findAllCategory(conn);
//        List<Author> authors = AuthorDAO.findAllAuthor(conn);
//
//        Map<String, Object> responseData = new HashMap<>();
//        responseData.put("categories", categories);
//        responseData.put("authors", authors);
//
//        Gson gson = new Gson();
//        String finalJson = gson.toJson(responseData);
//
//        resp.getWriter().write(finalJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categories");
        String authorId = req.getParameter("authors");
        Book book = AdminUltil.GetBookDoPost(req);
        Connection conn = MyUtils.getStoredConnection(req);
        boolean isAdd = BookDAO.createBook(conn, book, categoryId, authorId);
        HttpSession session = req.getSession();
        if (isAdd) {
            session.setAttribute("message", book.getTitle() + " has been added success");
        } else {
            session.setAttribute("message", "There is a problem when adding " + book.getTitle());
        }
        resp.sendRedirect(req.getContextPath() + "/admin/book-manager");
    }
}
