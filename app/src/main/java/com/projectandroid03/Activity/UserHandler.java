package com.projectandroid03.Activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "android03.db";
    private static final int DATABASE_VERSION = 5;

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
        if (oldVersion < 4 && newVersion >= 5) {
            String addColumnQuery = "ALTER TABLE tbl_users ADD COLUMN user_name TEXT DEFAULT 'Khách hàng'";
            db.execSQL(addColumnQuery);
        }
    }
}
