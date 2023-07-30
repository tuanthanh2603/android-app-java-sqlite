package com.projectandroid03.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;

public class ProductHandler  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android04.db";
    private static final int DATABASE_VERSION = 4;

    public ProductHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery3 = "CREATE TABLE tbl_product ("
                + "product_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "product_name TEXT,"
                + "category_id INTEGER,"
                + "product_price TEXT,"
                + "product_desc TEXT,"
                + "product_image TEXT"
                + ")";
        db.execSQL(createTableQuery3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // Phương thức để thêm sản phẩm vào bảng "tbl_product".
    public boolean addProductToDataBase(int categoryId, String productName, String productPrice, String productDescription, @NonNull Uri productImageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category_id", categoryId);
        values.put("product_name", productName);
        values.put("product_price", productPrice);
        values.put("product_desc", productDescription);
        values.put("product_image", productImageUri.toString());

        long newRowId = db.insert("tbl_product", null, values);
        db.close();

        return newRowId != -1;
    }
}
