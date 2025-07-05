/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Controller.admin;

import com.CatLib.DAO.BookDAO;
import com.CatLib.Model.Book;
import com.CatLib.Ultis.AdminUltil;
import com.CatLib.Ultis.MyUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 *
 * @author tvphu
 */
@WebServlet(urlPatterns = {"/admin/book-manager/update"})
public class UpdateBookManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String bookId = req.getParameter("id");
        Connection conn = MyUtils.getStoredConnection(req);
        Book book = BookDAO.findBookById(conn, bookId);

        StringBuilder json = new StringBuilder();
        json.append("[{");
        json.append("\"bookId\":\"").append(book.getBookId()).append("\",");
        json.append("\"title\":\"").append(book.getTitle()).append("\",");

        json.append("\"publishDate\":\"").append(book.getPublishDate().toString()).append("\",");
        json.append("\"publisher\":\"").append(book.getPublisher()).append("\",");
        json.append("\"categoryName\":\"").append(book.getCategoryName()).append("\",");
        json.append("\"authorName\":\"").append(book.getAuthorName()).append("\",");
        json.append("\"stockQuantity\":\"").append(book.getStockQuantity()).append("\",");
        json.append("\"description\":\"").append(book.getDescription()).append("\",");
        json.append("\"imageURL\":\"").append(book.getUrlImage()).append("\"");
        json.append("}]");
        out.write(json.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        String categoryId = req.getParameter("category");
        String authorId = req.getParameter("author");
        Book book = AdminUltil.GetBookDoPost(req);
        Connection conn = MyUtils.getStoredConnection(req);
        boolean isUpdate = BookDAO.updateBook(conn, book, bookId, categoryId, authorId);
        HttpSession session = req.getSession();

        if (isUpdate) {
            session.setAttribute("message", book.getTitle() + " has been successfully updated");
        } else {
            session.setAttribute("message", book.getTitle() + " has been updated unsuccessfully");
        }
        resp.sendRedirect(req.getContextPath() + "/admin/book-manager");

    }

}
