package com.projectandroid03.Activity.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CommentHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android07.db";
    private static final int DATABASE_VERSION = 1;

    public CommentHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE tbl_comment ("
                + "comment_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "product_id INTEGER,"
                + "user_id INTEGER,"
                + "comment_desc TEXT"

                + ")";
        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long addComment(int productId, String comment, int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_id", productId);
        values.put("user_id", userId);
        values.put("comment_desc", comment);
        long id = db.insert("tbl_comment", null, values);
        db.close();
        return id;
    }
}
