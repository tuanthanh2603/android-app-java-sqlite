package com.projectandroid03.Activity.Model;

public class User {
    private int userId;
    private String userPhone;

    public User(int userId, String userPhone) {
        this.userId = userId;
        this.userPhone = userPhone;
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
