/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.DAO;

import com.CatLib.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DuyPhuc
 */
public class UserDAO {

    public static User findUser(Connection conn, //
            String userName, String password) throws SQLException {

        String sql = "Select username, password, full_name, role, is_active from Users u " //
                + " where u.username = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String fullName = rs.getString("full_name");
            String role = rs.getString("role");
            boolean isActive = rs.getString("is_active").equals("1");

            User user = new User(userName, password, fullName, role, isActive);

            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String userName) throws SQLException {

        String sql = "Select * from Users u " //
                + " where u.username = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

//         public User(int userId, String username, String password, String fullName,
//            String role, boolean isActive, String email, String phone) {
        
        if (rs.next()) {
            String password = rs.getString("password");
            String fullName = rs.getString("full_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone_number");
            String role = rs.getString("role");
            boolean isActive = rs.getString("is_active").equals("1");
            User user = new User(userName, password, fullName, role, isActive, email, phone);

            return user;
        }
        return null;
    }

    public static void createUser(Connection conn, String userName, String password, String fullName) throws SQLException {

        String sql = "INSERT INTO Users (username, password, full_name) VALUES (?, ?, ?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        pstm.setString(3, fullName);

        pstm.executeUpdate();

    }
}
