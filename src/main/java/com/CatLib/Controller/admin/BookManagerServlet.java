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
import com.CatLib.Ultis.MyUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author tvphu
 */
@WebServlet(urlPatterns = {"/admin/book-manager"})
public class BookManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        List<Book> books = BookDAO.findAllBook(conn);
        List<Category> categories = CategoryDAO.findAllCategory(conn);
        List<Author> authors = AuthorDAO.findAllAuthor(conn);

        req.setAttribute("books", books);
        req.setAttribute("categories", categories);
        req.setAttribute("authors", authors);

        HttpSession session = req.getSession(false);
        if (session != null) {
            String message = (String) session.getAttribute("message");
            if (message != null) {
                req.setAttribute("message", message);
                session.removeAttribute("message"); 
            }
        }

        this.getServletContext().getRequestDispatcher("/views/admin/bookManagerView.jsp").forward(req, resp);
    }

}
