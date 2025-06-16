/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Controller;

import com.CatLib.DAO.OrderDAO;
import com.CatLib.Model.OrderModel;
import com.CatLib.Model.User;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DuyPhuc
 */
@WebServlet(urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginedUser = MyUtils.getLoginedUser(req.getSession());

        if (loginedUser == null || !loginedUser.isActive()) {
            // Redirect (Chuyển hướng) tới trang login.
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Connection conn = MyUtils.getStoredConnection(req);
        List<OrderModel> orders = null;
        try {
            orders = OrderDAO.findApprovedOrder(conn, loginedUser);
        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(orders);
        req.setAttribute("orders", orders);
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/orderView.jsp");
        dispatcher.forward(req, resp);
    }

}
