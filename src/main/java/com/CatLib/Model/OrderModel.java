/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Model;

import java.time.LocalDate;

/**
 *
 * @author DuyPhuc
 */
public class OrderModel {

    private int orderId;
    private Integer userId; // Có thể là null nếu không có UserID hợp lệ, dùng Integer
    private Integer bookId; // Có thể là null nếu không có BookID hợp lệ, dùng Integer
    private LocalDate orderDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate; // Có thể là null
    private long bill; // Sử dụng BigDecimal cho tiền tệ
    private String status;

    // Constructor
    public OrderModel(int orderId, Integer userId, Integer bookId, LocalDate orderDate,
            LocalDate returnDate, LocalDate actualReturnDate, long bill, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.bookId = bookId;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
        this.actualReturnDate = actualReturnDate;
        this.bill = bill;
        this.status = status;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public long getBill() {
        return bill;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public void setBill(long bill) {
        this.bill = bill;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderModel{"
                + "orderId=" + orderId
                + ", userId=" + userId
                + ", bookId=" + bookId
                + ", orderDate=" + orderDate
                + ", returnDate=" + returnDate
                + ", actualReturnDate=" + actualReturnDate
                + ", bill=" + bill
                + ", status='" + status + '\''
                + '}';
    }
}
