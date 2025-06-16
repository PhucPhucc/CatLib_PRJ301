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
    private double price; // Sử dụng BigDecimal cho tiền tệ
    private Integer publishYear; // Có thể là null, dùng Integer
    private String description;
    private String publisher;
    private int stockQuantity;
    private String categoryName;
    private String authorName; // Có thể là null, dùng Integer
    private String urlImage;
    
    
    // Constructor
    public Book(int bookId, String title, double price, Integer publishYear, String description,
            String publisher, int stockQuantity, String categoryName, String authorName, String urlImage) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.publishYear = publishYear;
        this.description = description;
        this.publisher = publisher;
        this.stockQuantity = stockQuantity;
        this.categoryName = categoryName;
        this.authorName = authorName;
        this.urlImage = urlImage;
    }

    public Book(int bookId, String title, String description, String urlImage) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
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

    public String getCategoryName() {
        return categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }

    // Setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
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

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
                + ", categoryId=" + categoryName
                + ", authorId=" + authorName
                + '}';
    }
}
