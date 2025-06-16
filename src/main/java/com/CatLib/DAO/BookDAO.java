/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.DAO;

import com.CatLib.Model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DuyPhuc
 */
public class BookDAO {

    public static List<Book> findAllBook(Connection conn) throws SQLException {
        String sql = "select bookid, title, [description], URL_Image from book";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
            int id = Integer.parseInt(rs.getString("bookid"));

            String title = rs.getString("title");
            String description = rs.getString("description");
//            String url = rs.getString("image_url");
            String url = "image/solanin.jpg";

            Book book = new Book(id, title, description, url);
            list.add(book);
        }
        return list;
    }

    public static Book findBookById(Connection conn, String bookId) throws SQLException {
//        String sql = "select book_id, title, [description], image_url, author_name \n"
//                + "from Books\n"
//                + "join AUTHORS on books.author_id = AUTHORS.author_id\n"
//                + "where books.book_id = ?";
        String sql = "select * from Book\n"
                + "join Book_Author on Book.BookID = Book_Author.BookID\n"
                + "join AUTHOR on AUTHOR.authorId = Book_Author.authorId\n"
                + "join CATEGORY on CATEGORY.categoryId = Book.categoryId \n"
                + "where book.bookId = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, bookId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String title = rs.getString("title");
            String quantity = rs.getString("stockquantity");
            String description = rs.getString("description");
            String authorName = rs.getString("authorName");
            String categoryName = rs.getString("categoryName");
            String publishYear = rs.getString("publishYear");
            String publisher = rs.getString("publisher");
            String price = rs.getString("price");

//            String url = rs.getString("image_url");
            String url = "image/solanin.jpg";

//            public Book(int bookId, String title, long price, Integer publishYear, 
//                    String description,
//            String publisher, int stockQuantity, int categoryId, Integer authorId, 
//            String urlImage) {
            Book book = new Book(Integer.parseInt(bookId),
                    title,
                    Double.parseDouble(price),
                    Integer.valueOf(publishYear),
                    description,
                    publisher,
                    Integer.parseInt(quantity),
                    categoryName,
                    authorName,
                    url);

            return book;
        }

        return null;
    }

    public static List<Book> searchBookByInput(Connection conn, String input) throws SQLException {
        String sql = "select Book.bookid, title, [description], URL_Image from BOOK\n"
                + "join Book_Author on Book.BookID = Book_Author.BookID\n"
                + "join AUTHOR on AUTHOR.authorId = Book_Author.authorId\n"
                + "join CATEGORY on CATEGORY.categoryId = Book.categoryId "
                + "WHERE BOOK.title COLLATE Latin1_General_CI_AI LIKE '%' + ? + '%'\n"
                + "OR authorName COLLATE Latin1_General_CI_AI LIKE '%' + ? + '%'\n"
                + "OR categoryName = ?;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, input);
        pstm.setString(2, input);
        pstm.setString(3, input);

        ResultSet rs = pstm.executeQuery();
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
            int id = Integer.parseInt(rs.getString("bookId"));

            String title = rs.getString("title");
            String description = rs.getString("description");
//            String url = rs.getString("image_url");
            String url = "image/solanin.jpg";

            Book book = new Book(id, title, description, url);
            list.add(book);
        }
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

}
