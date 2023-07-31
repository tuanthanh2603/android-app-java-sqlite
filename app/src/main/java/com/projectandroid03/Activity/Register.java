package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projectandroid03.Activity.Handler.UserHandler;
import com.projectandroid03.R;

public class Register extends AppCompatActivity {
    ActionBar actionBar;
    Button btndangky;
    EditText edtphone, edtpass;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Đăng ký");
        UserHandler userHandler = new UserHandler(this);
        db = userHandler.getWritableDatabase();
        addControl();
        addEvent();
    }
    private void addEvent(){
        btndangky.setOnClickListener(view ->{
            String phone = edtphone.getText().toString().trim();
            String password = edtpass.getText().toString().trim();

            insertData(phone, password);
        });


    }
    private  void insertData(String phone, String password){
        if (isPhoneExist(phone)) {
            // Số điện thoại đã tồn tại
            showToast("Số điện thoại đã tồn tại!");
        } else {
            // Số điện thoại chưa tồn tại, tiến hành chèn dữ liệu
            ContentValues values = new ContentValues();
            values.put("user_phone", phone);
            values.put("user_password", password);

            long newRowId = db.insert("tbl_user", null, values);
            if (newRowId != -1) {
                showToast("Đăng ký thành công!");
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            } else {
                showToast("Đăng ký thất bại!");
            }
        }
    }

    private boolean isPhoneExist(String phone){
        String[] projection = { "user_phone"};
        String selection = "user_phone=?";
        String[] selectionArgs = {phone};
        Cursor cursor = db.query("tbl_user", projection, selection, selectionArgs, null, null, null);
        boolean phoneExists = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }

        return phoneExists;
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void addControl(){
        btndangky = (Button) findViewById(R.id.btndangky);
        edtpass = (EditText) findViewById(R.id.edtpass);
        edtphone = (EditText) findViewById(R.id.edtphone);
    }
}