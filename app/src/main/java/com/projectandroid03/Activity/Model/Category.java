package com.projectandroid03.Activity.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Category implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(categoryName);
//        dest.writeString(imageUri.toString());
    }
    protected Category(Parcel in) {
        categoryId = in.readInt();
        categoryName = in.readString();
//        String imageUriString = in.readString();
//        imageUri = Uri.parse(imageUriString);

    }
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
