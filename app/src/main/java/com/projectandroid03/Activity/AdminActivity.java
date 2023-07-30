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
    Button btnaddcate, btnlistcate, btnaddproduct, btnlistproduct, btnlistuser, btndangxuat;
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

                // Bắt đầu mở Activity mới
                startActivity(intent);
            }
        });
        btnlistcate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ListCategoryActivity.class);
                startActivity(intent);
            }
        });
        btnaddproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
        btnlistproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ListProductActivity.class);
                startActivity(intent);
            }
        });
        btnlistuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ListUserActivity.class);
                startActivity(intent);
            }
        });
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void addControl(){

        btnaddcate = (Button) findViewById(R.id.button);
        btnlistcate = (Button) findViewById(R.id.button3);
        btnaddproduct = (Button) findViewById(R.id.button2);
        btnlistproduct = (Button) findViewById(R.id.button4);
        btnlistuser = (Button) findViewById(R.id.button5);
        btndangxuat = (Button) findViewById(R.id.button6);
    }
}