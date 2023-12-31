package com.projectandroid03.Activity.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.projectandroid03.Activity.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android05.db";
    private static final int DATABASE_VERSION = 14;
    public CategoryHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery2 = "CREATE TABLE tbl_category ("
                + "category_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "category_name TEXT,"
                + "category_image TEXT"
                + ")";
        db.execSQL(createTableQuery2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion > 13){
            String createTableQuery2 = "CREATE TABLE tbl_category ("
                    + "category_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "category_name TEXT,"
                    + "category_image TEXT"
                    + ")";
            db.execSQL(createTableQuery2);

        }

    }
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query("tbl_category", null, null, null, null, null, null);
            int categoryIdIndex = cursor.getColumnIndex("category_id");
            int categoryNameIndex = cursor.getColumnIndex("category_name");
            int categoryImageIndex = cursor.getColumnIndex("category_image");

            while (cursor.moveToNext()) {
                int categoryId = cursor.getInt(categoryIdIndex);
                String categoryName = cursor.getString(categoryNameIndex);
                String imagePath = cursor.getString(categoryImageIndex);
                Uri imageUri = Uri.parse(imagePath);

                Category category = new Category(categoryId, categoryName);
                categories.add(category);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return categories;
    }
    public List<Category> getAllCategoriesSpinner(){
        List<Category> categoryList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tbl_category", null, null, null, null, null, null);
        int categoryIdIndex = cursor.getColumnIndex("category_id");
        int categoryNameIndex = cursor.getColumnIndex("category_name");
        int categoryImageIndex = cursor.getColumnIndex("category_image");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int categoryId = cursor.getInt(categoryIdIndex);
                String categoryName = cursor.getString(categoryNameIndex);
                categoryList.add(new Category(categoryId, categoryName));
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return categoryList;
    }

    public boolean deleteCategory(int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("tbl_category", "category_id = ?", new String[] { String.valueOf(categoryId) });
        return rowsAffected > 0;
    }
    public String getCategoryNameByCategoryId(int categoryId) {
        String categoryName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String[] projection = {"category_name"};
            String selection = "category_id = ?";
            String[] selectionArgs = {String.valueOf(categoryId)};

            cursor = db.query("tbl_category", projection, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                int categoryNameIndex = cursor.getColumnIndex("category_name");
                categoryName = cursor.getString(categoryNameIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return categoryName;
    }

    public boolean updateCategory(int categoryId, String categoryName, @NonNull Uri categoryImageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("category_id", categoryId);
        values.put("category_name", categoryName);
        values.put("category_image", categoryImageUri.toString());

        Log.d("CategoryUpdate", "category_id: " + categoryId);

        String selection = "category_id=?";
        String[] selectionArgs = {String.valueOf(categoryId)};

        try {
            int rowsAffected = db.update("tbl_category", values, selection, selectionArgs);
            db.close();
            return rowsAffected != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
