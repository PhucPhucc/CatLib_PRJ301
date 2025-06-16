/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Controller;

import com.CatLib.DAO.BookDAO;
import com.CatLib.Model.Book;
import com.CatLib.Ultis.MyUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DuyPhuc
 */
@WebServlet(urlPatterns = {"/detail"})
public class BookDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        Connection conn = MyUtils.getStoredConnection(req);
        Book book = null;
        try {
            book = BookDAO.findBookById(conn, bookId);
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        req.setAttribute("book", book);
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/detailView.jsp");
        dispatcher.forward(req, resp);
    }

}
