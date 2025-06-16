/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Controller.auth;

import com.CatLib.DAO.UserDAO;
import com.CatLib.Model.User;
import com.CatLib.Ultis.MyUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author DuyPhuc
 */
@WebServlet(urlPatterns = {"/sign-up"})
public class SignUpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/signUpView.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re-password");

        User user = null;
        boolean hasError = false;
        String errorString = null;
//
//        User userAccount = DataDAO.findUser(userName, password);
//
//        if (!password.equals(rePassword) || userAccount != null) {
//            RequestDispatcher dispatcher
//                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signinView.jsp");
//
//            dispatcher.forward(req, resp);
//            return;
//        }

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else if (!password.equals(rePassword)) {
            hasError = true;
            errorString = "Confirm password does not match!";
        } else {
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                // Tìm user trong DB.
                user = UserDAO.findUser(conn, userName);
                if (user != null) {
                    hasError = true;
                    errorString = "User is exist";
                }
            } catch (SQLException e) {
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // Trong trường hợp có lỗi,
        // forward (chuyển hướng) tới /WEB-INF/views/signUpView.jsp
        if (hasError) {
            user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            // Lưu các thông tin vào request attribute trước khi forward.
            req.setAttribute("errorString", errorString);
            req.setAttribute("user", user);

            // Forward (Chuyển tiếp) tới trang /WEB-INF/views/signUpView.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/views/signUpView.jsp");

            dispatcher.forward(req, resp);
        } // Trường hợp không có lỗi.
        // Lưu thông tin người dùng vào Session.
        // Và chuyển hướng sang trang userInfo.
        else {
            int userId;
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                // Tạo User trong DB
                userId = UserDAO.createUser(conn, userName, password, email);
                user = new User(userId, userName, password, email);
                HttpSession session = req.getSession();
                MyUtils.storeLoginedUser(session, user);

                // Redirect (Chuyển hướng) sang trang /home.
                resp.sendRedirect(req.getContextPath() + "/home");
            } catch (SQLException e) {
                errorString = e.getMessage();
                System.out.println(errorString);
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/views/signUpView.jsp");

                dispatcher.forward(req, resp);
            }

        }

    }

}
