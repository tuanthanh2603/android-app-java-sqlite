package com.projectandroid03.Activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.projectandroid03.Activity.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android04.db";
    private static final int DATABASE_VERSION = 4;
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
            // Xử lý lỗi nếu có
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


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean deleteCategory(int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("tbl_category", "category_id = ?", new String[] { String.valueOf(categoryId) });
        return rowsAffected > 0;
    }
}
