/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.DAO;

import com.CatLib.Model.OrderModel;
import com.CatLib.Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DuyPhuc
 */
public class OrderDAO {

    public static List<OrderModel> findALlOrder(Connection conn, User user) throws SQLException {
        String sql = "select b.Title, a.authorName, b.Publisher, b.PublishYear, \n"
                + " b.StockQuantity, o.OrderDate, o.ReturnDate from BookOrders o\n"
                + " join Book b on b.BookID = o.BookID\n"
                + " join Book_Author BA on BA.BookID = b.BookID\n"
                + " join Author a on a.AuthorID = BA.AuthorID\n"
                + " where o.UserId = ?\n"
                + " order by orderID desc";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, String.valueOf(user.getUserId()));
        ResultSet rs = pstm.executeQuery();
        List<OrderModel> list = new ArrayList<>();
        while (rs.next()) {
//            int id = Integer.parseInt(rs.getString("bookid"));

            String title = rs.getString("title");
            String description = rs.getString("authorName");
            String publisher = rs.getString("Publisher");
            String publishYear = rs.getString("PublishYear");
            Date orderDate = rs.getDate("OrderDate");
            Date returnDate = rs.getDate("ReturnDate");

//            String url = rs.getString("image_url");
            String url = "image/solanin.jpg";

            OrderModel order = new OrderModel(title, description,
                    publisher, publishYear, orderDate, returnDate, url);
            list.add(order);
        }
        return list;
    }

    public static List<OrderModel> findApprovedOrder(Connection conn, User user) throws SQLException {
        String sql = "select b.Title, a.authorName, b.Publisher, b.PublishYear, \n"
                + " b.StockQuantity, o.OrderDate, o.ReturnDate from BookOrders o\n"
                + " join Book b on b.BookID = o.BookID\n"
                + " join Book_Author BA on BA.BookID = b.BookID\n"
                + " join Author a on a.AuthorID = BA.AuthorID\n"
                + " where o.UserId = ? and o.status = 'approved'"
                + " order by orderID desc";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, String.valueOf(user.getUserId()));
        ResultSet rs = pstm.executeQuery();
        List<OrderModel> list = new ArrayList<>();
        while (rs.next()) {
//            int id = Integer.parseInt(rs.getString("bookid"));

            String title = rs.getString("title");
            String description = rs.getString("authorName");
            String publisher = rs.getString("Publisher");
            String publishYear = rs.getString("PublishYear");
            Date orderDate = rs.getDate("OrderDate");
            Date returnDate = rs.getDate("ReturnDate");

//            String url = rs.getString("image_url");
            String url = "image/solanin.jpg";

            OrderModel order = new OrderModel(title, description,
                    publisher, publishYear, orderDate, returnDate, url);
            list.add(order);
        }
        return list;
    }
    
    public static boolean sendBorrow(Connection conn, int userId, int bookId) {
        String sql = "INSERT INTO BookOrders (UserID, BookID, OrderDate, ReturnDate, "
                + "ActualReturnDate, Bill, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        int borrowDurationDays = 14;
        LocalDate orderDate = LocalDate.now();
        LocalDate returnDate = orderDate.plusDays(borrowDurationDays);
        double defaultBill = 0.0;

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setInt(2, bookId);
            pstm.setDate(3, Date.valueOf(orderDate));
            pstm.setDate(4, Date.valueOf(returnDate));
            pstm.setNull(5, java.sql.Types.DATE); // ActualReturnDate
            pstm.setDouble(6, defaultBill);
            pstm.setNull(7, java.sql.Types.NVARCHAR); // Trigger sẽ tự gán Status

            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;

        }
    }

}
