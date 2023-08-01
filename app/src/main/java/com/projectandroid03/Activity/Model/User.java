package com.projectandroid03.Activity.Model;

public class User {
    private int userId;
    private String userPhone;
    private String userPassword;

    public User(int userId, String userPhone, String userPassword) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }

    public User(String userId, String userPhone, String userPass) {
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
