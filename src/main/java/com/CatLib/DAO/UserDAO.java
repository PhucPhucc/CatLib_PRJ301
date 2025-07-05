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
            String userName, String password) {

        String sql = "Select userId, username, password, full_name, role, is_active from Users u " //
                + " where u.username = ? and u.password= ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int userId = Integer.parseInt(rs.getString("userId"));
                String fullName = rs.getString("full_name");
                String role = rs.getString("role");
                boolean isActive = rs.getString("is_active").equals("1");

                User user = new User(userId, userName, password, fullName, role, isActive);

                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    public static User findUser(Connection conn, String userName) {

        String sql = "Select * from Users u " //
                + " where u.username = ?";
        try {
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
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    public static int createUser(Connection conn, String userName, String password, String email) {
        String sql = "INSERT INTO Users (username, password, email, role) VALUES (?, ?, ?, ?) "
                + "SELECT SCOPE_IDENTITY() AS NewUserID;";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setString(4, "user");

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("NewUserID");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return -1;
    }

}
