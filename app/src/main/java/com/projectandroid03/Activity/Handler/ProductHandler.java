package com.projectandroid03.Activity.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.projectandroid03.Activity.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android06.db";
    private static final int DATABASE_VERSION = 14;

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
        if(oldVersion < 6){
            String createTableQuery = "CREATE TABLE tbl_product ("
                    + "product_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "product_name TEXT,"
                    + "category_id INTEGER,"
                    + "product_price TEXT,"
                    + "product_desc TEXT,"
                    + "product_image TEXT"
                    + ")";
            db.execSQL(createTableQuery);
        }

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
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query("tbl_product", null, null, null, null, null, null);
            int productIdIndex = cursor.getColumnIndex("product_id");
            int productNameIndex = cursor.getColumnIndex("product_name");
            int productPriceIndex = cursor.getColumnIndex("product_price");
            int productImageIndex = cursor.getColumnIndex("product_image");
            int categoryIdIndex = cursor.getColumnIndex("category_id");
            int productDescIndex = cursor.getColumnIndex("product_desc");

            while (cursor.moveToNext()) {
                int productId = cursor.getInt(productIdIndex);
                int categoryId = cursor.getInt(categoryIdIndex);

                String productName = cursor.getString(productNameIndex);
                String productPrice = cursor.getString(productPriceIndex);
                String productImage = cursor.getString(productImageIndex);
                String productDesc = cursor.getString(productDescIndex);
                Uri imageUri = Uri.parse(productImage);

                Product product = new Product(productId, categoryId, productName, productPrice, productDesc, imageUri);
                products.add(product);
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return products;
    }

    public List<Product> getProductsByCategoryId(int selectedCategoryId) {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String[] projection = {
                    "product_id",
                    "product_name",
                    "product_price",
                    "product_image",
                    "category_id",
                    "product_desc"
            };
            String selection = "category_id=?";
            String[] selectionArgs = {String.valueOf(selectedCategoryId)}; // Đưa giá trị selectedCategoryId vào selectionArgs

            cursor = db.query("tbl_product", projection, selection, selectionArgs, null, null, null);
            int productIdIndex = cursor.getColumnIndex("product_id");
            int productNameIndex = cursor.getColumnIndex("product_name");
            int productPriceIndex = cursor.getColumnIndex("product_price");
            int productImageIndex = cursor.getColumnIndex("product_image");
            int categoryIdIndex = cursor.getColumnIndex("category_id");
            int productDescIndex = cursor.getColumnIndex("product_desc");

            while (cursor.moveToNext()) {
                int productId = cursor.getInt(productIdIndex);
                int categoryId = cursor.getInt(categoryIdIndex);

                String productName = cursor.getString(productNameIndex);
                String productPrice = cursor.getString(productPriceIndex);
                String productImage = cursor.getString(productImageIndex);
                String productDesc = cursor.getString(productDescIndex);
                Uri imageUri = Uri.parse(productImage);

                Product product = new Product(productId, categoryId, productName, productPrice, productDesc, imageUri);
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return productList;
    }
}
