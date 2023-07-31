package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projectandroid03.Activity.Handler.UserHandler;
import com.projectandroid03.R;

public class Login extends AppCompatActivity {
    ActionBar actionBar;
    Button btndangnhap;
    EditText edtphone, edtpass;
    UserHandler dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Đăng nhập");
        dbHelper = new UserHandler(this);
        addControl();
        addEvent();
    }
    private void addEvent(){
        btndangnhap.setOnClickListener(view ->{
            String phone = edtphone.getText().toString().trim();
            String password = edtpass.getText().toString().trim();
            if (checkLoginCredentials(phone, password)) {
                showToast("Đăng nhập thành công!");

                if ("0877717575".equals(phone)) {
                    // Nếu phone là "0877717575", chuyển hướng tới AdminActivity
                    Intent intent = new Intent(Login.this, AdminActivity.class);
                    intent.putExtra("user_phone", phone);
                    intent.putExtra("user_password", password);
                    startActivity(intent);
                } else {
                    // Nếu không, chuyển hướng tới ProfileActivity
                    Intent intent = new Intent(Login.this, ProfileActivity.class);
                    intent.putExtra("user_phone", phone);
                    intent.putExtra("user_password", password);
                    startActivity(intent);
                }

                finish();
            } else {
                showToast("Số điện thoại hoặc mật khẩu không đúng!");
            }







        });
    }

    private boolean checkLoginCredentials(String phone, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"user_phone", "user_password"};
        String selection = "user_phone=? AND user_password=?";
        String[] selectionArgs = {phone, password};
        Cursor cursor = db.query("tbl_user", projection, selection, selectionArgs, null, null, null);

        // Kiểm tra xem dữ liệu đã tìm thấy hay không
        boolean loginSuccessful = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }

        return loginSuccessful;
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private void addControl(){
        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        edtpass = (EditText) findViewById(R.id.edtpass);
        edtphone = (EditText) findViewById(R.id.edtphone);
    }

}