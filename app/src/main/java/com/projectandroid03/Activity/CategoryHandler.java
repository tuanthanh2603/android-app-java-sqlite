package com.projectandroid03.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CategoryHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android03.db";
    private static final int DATABASE_VERSION = 5;
    public CategoryHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE tbl_category ("
                + "category_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "category_name TEXT,"
                + "category_image TEXT"
                + ")";
        db.execSQL(createTableQuery);
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertSampleData(SQLiteDatabase db) {
        // Tạo dữ liệu mẫu
        ContentValues values = new ContentValues();
        values.put("category_name", "Danh mục 1");
        values.put("category_image", "đường_dẫn_ảnh_1");
        db.insert("tbl_category", null, values);

        values.clear(); // Xóa dữ liệu cũ để thêm dữ liệu mới
        values.put("category_name", "Danh mục 2");
        values.put("category_image", "đường_dẫn_ảnh_2");
        db.insert("tbl_category", null, values);

        // Thêm dữ liệu mẫu khác nếu cần
    }
}
