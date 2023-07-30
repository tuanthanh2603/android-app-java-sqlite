package com.projectandroid03.Activity.Model;

import android.net.Uri;

public class Product {
    private int product_id;
    private int category_id;
    private String product_name;
    private String product_price;
    private String product_desc;
    private Uri product_image;

    public Product(int product_id,int category_id, String product_name,  String product_price, String product_desc, Uri product_image) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_desc = product_desc;
        this.product_image = product_image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public Uri getProduct_image() {
        return product_image;
    }

    public void setProduct_image(Uri product_image) {
        this.product_image = product_image;
    }

}
