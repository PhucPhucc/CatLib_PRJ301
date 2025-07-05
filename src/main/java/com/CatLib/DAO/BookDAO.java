/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.DAO;

import com.CatLib.Model.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DuyPhuc
 */
public class BookDAO {

    public static List<Book> findAllBook(Connection conn) {
        String sql = "select Book.bookid, title, [description], stockQuantity, authorName, imageURL from BOOK\n"
                + "join Book_Author on Book.BookID = Book_Author.BookID\n"
                + "join AUTHOR on AUTHOR.authorId = Book_Author.authorId\n";
        List<Book> list;
        PreparedStatement pstm;
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("bookid"));

                String title = rs.getString("title");
                String description = rs.getString("description");
                String authorName = rs.getString("authorName");
                int stockQuantity = rs.getInt("stockQuantity");
                String url = rs.getString("imageURL");

                Book book = new Book(id, title, description, stockQuantity, authorName, url);
                list.add(book);
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Book findBookById(Connection conn, String bookId) {
//        String sql = "select book_id, title, [description], image_url, author_name \n"
//                + "from Books\n"
//                + "join AUTHORS on books.author_id = AUTHORS.author_id\n"
//                + "where books.book_id = ?";
        String sql = "select * from Book\n"
                + "join Book_Author on Book.BookID = Book_Author.BookID\n"
                + "join AUTHOR on AUTHOR.authorId = Book_Author.authorId\n"
                + "join CATEGORY on CATEGORY.categoryId = Book.categoryId \n"
                + "where book.bookId = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, bookId);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String quantity = rs.getString("stockquantity");
                String description = rs.getString("description");
                String authorName = rs.getString("authorName");
                String categoryName = rs.getString("categoryName");
                String publishDate = rs.getString("publishDate");
                String publisher = rs.getString("publisher");

                String url = rs.getString("imageURL");

                Book book = new Book(Integer.parseInt(bookId),
                        title,
                        Date.valueOf(publishDate),
                        description,
                        publisher,
                        Integer.parseInt(quantity),
                        categoryName,
                        authorName,
                        url);

                return book;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    public static List<Book> searchBookByInput(Connection conn, String input) {
        String sql = "select Book.bookid, title, [description], stockQuantity, authorName, imageURL from BOOK\n"
                + "join Book_Author on Book.BookID = Book_Author.BookID\n"
                + "join AUTHOR on AUTHOR.authorId = Book_Author.authorId\n"
                + "join CATEGORY on CATEGORY.categoryId = Book.categoryId "
                + "WHERE BOOK.title COLLATE Latin1_General_CI_AI LIKE '%' + ? + '%'\n"
                + "OR authorName COLLATE Latin1_General_CI_AI LIKE '%' + ? + '%'\n"
                + "OR CATEGORY.CategoryID = TRY_CAST(? AS INT)";
        try {
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
                String authorName = rs.getString("authorName");
                int stockQuantity = rs.getInt("stockQuantity");
                String url = rs.getString("imageURL");

                Book book = new Book(id, title, description, stockQuantity, authorName, url);
                list.add(book);
            }
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void createBook(Connection conn, Book book, String categoryId, String authorId) {
        String sql = "insert into Book (title, publishDate, publisher, stockQuantity, description, imageURL, categoryId, authorId) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n"
                + "SELECT SCOPE_IDENTITY() AS NewBookID;";
//                + "insert into Book_Author (BookID, AuthorID) VALUES (?, ?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, book.getTitle());
            pstm.setDate(2, book.getPublishDate());
            pstm.setString(3, book.getPublisher());
            pstm.setInt(4, book.getStockQuantity());
            pstm.setString(5, book.getDescription());
            pstm.setString(6, book.getUrlImage());
            pstm.setString(7, categoryId);
            pstm.setString(8, authorId);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int bookId = rs.getInt("NewBookID");
                String newSQL = "insert into Book_Author (BookID, AuthorID) VALUES (?, ?)";
                PreparedStatement pstmNew = conn.prepareStatement(newSQL);
                pstmNew.setInt(1, bookId);
                pstmNew.setString(2, authorId);
                pstmNew.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean updateBook(Connection conn, Book book, String bookId, String categoryId, String authorId) {
        String sql = "UPDATE Book SET "
                + "title = ?, "
                + "publishDate = ?, "
                + "publisher = ?, "
                + "stockQuantity = ?, "
                + "description = ?, "
                + "imageURL = ?, "
                + "categoryId = ?, "
                + "authorId = ? "
                + "WHERE bookID = ?";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, book.getTitle());
            pstm.setDate(2, book.getPublishDate());
            pstm.setString(3, book.getPublisher());
            pstm.setInt(4, book.getStockQuantity());
            pstm.setString(5, book.getDescription());
            pstm.setString(6, book.getUrlImage());
            pstm.setString(7, categoryId);
            pstm.setString(8, authorId);
            pstm.setString(9, bookId); // giả sử có hàm getBookId()

            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Đã xóa sách thành công.");
                // Nếu bảng Book_Author tồn tại và cần cập nhật (nếu có cấu trúc riêng), thực hiện tiếp
                String updateAuthorSQL = "UPDATE Book_Author SET AuthorID = ? WHERE BookID = ?";
                PreparedStatement pstmAuthor = conn.prepareStatement(updateAuthorSQL);
                pstmAuthor.setString(1, authorId);
                pstmAuthor.setInt(2, book.getBookId());
                pstmAuthor.executeUpdate();
                return true;
            } else {
                System.out.println("Không tìm thấy sách với ID này.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBook(Connection conn, String bookId) {
        try {
            // 1. Kiểm tra xem có người đang mượn sách không
            String checkSQL = "SELECT COUNT(*) FROM BookOrders "
                    + "WHERE BookID = ? AND Status IN ('pending', 'approved', 'overdue')";
            PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
            checkStmt.setString(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    System.out.println("Không thể xóa sách này vì hiện đang có người mượn hoặc chờ xử lý.");
                    return false;
                }
            }

            // 2. Xóa trong BookOrders (các đơn đã rejected hoặc returned, nếu có)
            String deleteOrdersSQL = "DELETE FROM BookOrders WHERE BookID = ?";
            PreparedStatement pstmOrders = conn.prepareStatement(deleteOrdersSQL);
            pstmOrders.setString(1, bookId);
            pstmOrders.executeUpdate();

            // 3. Xóa trong Book_Author
            String deleteBookAuthorSQL = "DELETE FROM Book_Author WHERE BookID = ?";
            PreparedStatement pstmBookAuthor = conn.prepareStatement(deleteBookAuthorSQL);
            pstmBookAuthor.setString(1, bookId);
            pstmBookAuthor.executeUpdate();

            // 4. Xóa trong Book
            String deleteBookSQL = "DELETE FROM Book WHERE BookID = ?";
            PreparedStatement pstmBook = conn.prepareStatement(deleteBookSQL);
            pstmBook.setString(1, bookId);
            int affectedRows = pstmBook.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
