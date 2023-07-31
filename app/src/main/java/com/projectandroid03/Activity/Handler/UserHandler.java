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


            while (cursor.moveToNext()) {
                int userId = cursor.getInt(userIdIndex);
                String userPhone = cursor.getString(userPhoneIndex);


                User user = new User(userId, userPhone);
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
}
