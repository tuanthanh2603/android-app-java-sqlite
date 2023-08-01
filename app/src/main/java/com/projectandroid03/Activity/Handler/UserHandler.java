package com.projectandroid03.Activity.Handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.Activity.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android04.db";
    private static final int DATABASE_VERSION = 14;

    public UserHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE tbl_user ("
                + "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "user_phone TEXT,"
                + "user_password TEXT"

                + ")";
        db.execSQL(createTableQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 6){
            String createTableQuery = "CREATE TABLE tbl_user ("
                    + "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user_phone TEXT,"
                    + "user_password TEXT"

                    + ")";
            db.execSQL(createTableQuery);

        }

    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query("tbl_user", null, null, null, null, null, null);
            int userIdIndex = cursor.getColumnIndex("user_id");
            int userPhoneIndex = cursor.getColumnIndex("user_phone");
            int userPassIndex = cursor.getColumnIndex("user_password");


            while (cursor.moveToNext()) {
                int userId = cursor.getInt(userIdIndex);
                String userPhone = cursor.getString(userPhoneIndex);
                String userPass = cursor.getString(userPassIndex);


                User user = new User(userId, userPhone, userPass);
                userList.add(user);
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return userList;
    }
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tbl_user", "user_id = ?", new String[]{String.valueOf(user.getUserId())});
        db.close();
    }

    public String getUserIdFromSQLite(String phone, String password) {
        String user_id = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"user_id"};
        String selection = "user_phone = ? AND user_password = ?";
        String[] selectionArgs = {phone, password};
        Cursor cursor = db.query("tbl_user", projection, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            // Kiểm tra xem cột "user_id" có tồn tại trong Cursor hay không
            int userIdColumnIndex = cursor.getColumnIndex("user_id");
            if (userIdColumnIndex != -1) {
                user_id = cursor.getString(userIdColumnIndex);
            }
            cursor.close();
        }
        return user_id;
    }

    public String getUserPhoneById(int userId) {
        String userPhone = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"user_phone"};
        String selection = "user_id=?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.query("tbl_user", projection, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int userPhoneIndex = cursor.getColumnIndex("user_phone");
            if(userPhoneIndex != -1){
                userPhone = cursor.getString(userPhoneIndex);
            }
            cursor.close();

        }


        return userPhone;
    }

    public User getUserById(int userId) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"user_phone", "user_password"};
        String selection = "user_id=?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.query("tbl_user", projection, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int userPhoneIndex = cursor.getColumnIndex("user_phone");
            int userPassIndex = cursor.getColumnIndex("user_password");
            if (userPhoneIndex != -1 && userPassIndex != -1) {
                String userPhone = cursor.getString(userPhoneIndex);
                String userPass = cursor.getString(userPassIndex);
                user = new User(userId, userPhone, userPass);
            }
            cursor.close();
        }

        return user;
    }

}
