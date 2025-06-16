/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Controller;

import com.CatLib.DAO.OrderDAO;
import com.CatLib.Model.User;
import com.CatLib.Ultis.MyUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author DuyPhuc
 */
@WebServlet(urlPatterns = {"/borrow"})
public class BorrowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginedUser = MyUtils.getLoginedUser(req.getSession());

        if (loginedUser == null || !loginedUser.isActive()) {
            // Redirect (Chuyển hướng) tới trang login.
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Connection conn = MyUtils.getStoredConnection(req);
        String bookId = req.getParameter("id");
        boolean isSuccess = OrderDAO.sendBorrow(conn, loginedUser.getUserId(), Integer.parseInt(bookId));
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/order");
        }
    }

}
