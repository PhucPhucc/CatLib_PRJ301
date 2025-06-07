/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CatLib.Model;

import java.math.BigDecimal;

/**
 *
 * @author DuyPhuc
 */
public class Book {

    private int bookId;
    private String title;
    private long price; // Sử dụng BigDecimal cho tiền tệ
    private Integer publishYear; // Có thể là null, dùng Integer
    private String description;
    private String publisher;
    private int stockQuantity;
    private int categoryId;
    private Integer authorId; // Có thể là null, dùng Integer

    // Constructor
    public Book(int bookId, String title, long price, Integer publishYear, String description,
            String publisher, int stockQuantity, int categoryId, Integer authorId) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.publishYear = publishYear;
        this.description = description;
        this.publisher = publisher;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public long getPrice() {
        return price;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public String getDescription() {
        return description;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    // Setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{"
                + "bookId=" + bookId
                + ", title='" + title + '\''
                + ", price=" + price
                + ", publishYear=" + publishYear
                + ", description='" + description + '\''
                + ", publisher='" + publisher + '\''
                + ", stockQuantity=" + stockQuantity
                + ", categoryId=" + categoryId
                + ", authorId=" + authorId
                + '}';
    }
}
