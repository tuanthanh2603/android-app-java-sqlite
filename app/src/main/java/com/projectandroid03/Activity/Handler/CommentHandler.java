package com.projectandroid03.Activity.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.projectandroid03.Activity.Model.Comment;

import java.util.ArrayList;
import java.util.List;

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

    public List<Comment> getAllComment(int selectedProductId) {
        List<Comment> commentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try{
            String[] projection = {
                    "comment_id",
                    "user_id",
                    "product_id",
                    "comment_desc"
            };
            String selection = "product_id=?";
            String[] selectionArgs = {String.valueOf(selectedProductId)};
            cursor = db.query("tbl_comment", projection, selection, selectionArgs, null, null, null);
            int commentIdIndex = cursor.getColumnIndex("comment_id");
            int userIdIndex = cursor.getColumnIndex("user_id");
            int productIdIndex = cursor.getColumnIndex("product_id");
            int commentDescIndex = cursor.getColumnIndex("comment_desc");

            while (cursor.moveToNext()){
                int commentId = cursor.getInt(commentIdIndex);
                int userId = cursor.getInt(userIdIndex);
                int productId = cursor.getInt(productIdIndex);
                String commentDesc = cursor.getString(commentDescIndex);

                Comment comment = new Comment(commentId, userId, productId, commentDesc);
                commentList.add(comment);

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return  commentList;
    }
}
