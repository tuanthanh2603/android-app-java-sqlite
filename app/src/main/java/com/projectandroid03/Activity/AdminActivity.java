package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projectandroid03.R;

public class AdminActivity extends AppCompatActivity {
    ActionBar actionBar;
    Button btnaddcate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Admin");
        addControl();
        addEvent();
    }
    private void addEvent(){
        btnaddcate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddCategoryActivity.class);

                // Gửi thông tin hoặc dữ liệu cần thiết nếu cần thiết, ví dụ như dữ liệu của danh mục sản phẩm

                // Bắt đầu mở Activity mới
                startActivity(intent);
            }
        });
    }
    private void addControl(){
        btnaddcate = (Button) findViewById(R.id.button);
    }
}