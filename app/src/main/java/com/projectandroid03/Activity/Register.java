package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projectandroid03.Activity.Handler.UserHandler;
import com.projectandroid03.R;

public class Register extends AppCompatActivity {
    ActionBar actionBar;
    Button btndangky;
    EditText edtphone, edtpass, edtcheck;
    SQLiteDatabase db;
    ImageView showpass, showcomfig;

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
            String comfig = edtcheck.getText().toString().trim();
            if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                showToast("Vui lòng nhập đầy đủ thông tin");
            } else if (phone.length() != 10) {
                showToast("Số điện thoại không chính xác!");
            } else if (password.length() <= 3 ) {
                showToast("Mật khẩu quá ngắn!");
            }else if (!comfig.equals(password)) {
                showToast("Xác nhận mật khẩu không khớp!");
            } else if (!isValidString(password)) {
                showToast("Mật khẩu không được chứa kí tự đặc biệt!");
            } else {
                insertData(phone, password);
            }


        });
        showpass.setOnClickListener(new View.OnClickListener() {
            private boolean isPasswordVisible = false;
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Nếu mật khẩu đang được hiển thị, ẩn mật khẩu
                    edtpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isPasswordVisible = false;
                } else {
                    // Nếu mật khẩu đang ẩn, hiển thị mật khẩu
                    edtpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isPasswordVisible = true;
                }
            }
        });
        showcomfig.setOnClickListener(new View.OnClickListener() {
            private boolean isPasswordVisible = false;
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Nếu mật khẩu đang được hiển thị, ẩn mật khẩu
                    edtcheck.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isPasswordVisible = false;
                } else {
                    // Nếu mật khẩu đang ẩn, hiển thị mật khẩu
                    edtcheck.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isPasswordVisible = true;
                }
            }
        });


    }
    private boolean isValidString(String input) {
        String regex = "^[a-zA-Z0-9]+$";
        return input.matches(regex);
    }
    private  void insertData(String phone, String password){
        if (isPhoneExist(phone)) {

            showToast("Số điện thoại đã tồn tại!");
        } else {

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
        edtcheck = (EditText) findViewById(R.id.edtcheckpass);
        showpass = (ImageView) findViewById(R.id.imageView4);
        showcomfig = (ImageView) findViewById(R.id.imageView5);
    }
}