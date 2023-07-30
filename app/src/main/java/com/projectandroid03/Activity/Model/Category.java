package com.projectandroid03.Activity.Model;

import android.net.Uri;


public class Category {

    private int categoryId;
    private String categoryName;
    private Uri imageUri;




    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.imageUri = imageUri;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return categoryName; // Hiển thị tên danh mục trong Spinner
    }
}
