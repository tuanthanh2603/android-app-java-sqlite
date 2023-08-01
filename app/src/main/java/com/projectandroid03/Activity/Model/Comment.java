package com.projectandroid03.Activity.Model;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Comment {
    private int comment_id;
    private int user_id;
    private int product_id;
    private String comment_desc;

    public Comment(int comment_id, int user_id, int product_id, String comment_desc) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.comment_desc = comment_desc;
    }



    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getComment_desc() {
        return comment_desc;
    }

    public void setComment_desc(String comment_desc) {
        this.comment_desc = comment_desc;
    }
}
