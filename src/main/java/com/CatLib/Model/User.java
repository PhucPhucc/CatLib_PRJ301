package com.CatLib.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DuyPhuc
 */
public class User {

    private int userId; // IDENTITY(1,1) nghĩa là nó sẽ tự động tăng trong DB, nhưng vẫn cần trường này trong model
    private String username;
    private String password;
    private String fullName; // full_name trong SQL
    private String role;
    private boolean isActive; // is_active trong SQL
    private String email;
    private String phone;

    public User(int userId, String username, String password, String fullName, String role, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.isActive = isActive;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = "user";
        this.isActive = true;
    }

    
    
    public User() {
    }

    public User(int userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = email;
        this.role = "user";
        this.isActive = true;
    }

    // Constructor
    public User(String username, String password, String fullName,
            String role, boolean isActive, String email, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.isActive = isActive;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", username='" + username + '\''
                + ", fullName='" + fullName + '\''
                + ", role='" + role + '\''
                + ", isActive=" + isActive
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
