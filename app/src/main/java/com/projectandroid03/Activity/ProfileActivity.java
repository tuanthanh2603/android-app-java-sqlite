package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projectandroid03.Activity.Handler.UserHandler;
import com.projectandroid03.R;

public class ProfileActivity extends AppCompatActivity {
    ActionBar actionBar;
    private EditText edtname, edtphone, edtpass;
    private Button btnLuu, btnDangxuat, btnHome;
    private TextView iduser2;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Chỉnh sửa thông tin cá nhân");

        addControl();
        addEvent();
        Intent intent = getIntent();
        if (intent.hasExtra("user_phone")) {
            String userPhone = intent.getStringExtra("user_phone");
            String userPass = intent.getStringExtra("user_password");
            String userId = intent.getStringExtra("selectedUserId");
//            String userName = getUserNameFromDatabase(userPhone);
            iduser2.setText("User ID: "+userId);
            edtphone.setText(userPhone);
            edtpass.setText(userPass);
//            edtname.setText(userName);


        }


        addEvent();
    }
    private void addEvent(){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = getIntent().getStringExtra("selectedUserId");


                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);

                intent.putExtra("selectedUserId", userId);
                startActivity(intent);


            }
        });
        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPhone = edtphone.getText().toString().trim();
                String newPassword = edtpass.getText().toString().trim();
                if(!newPhone.isEmpty() && !newPassword.isEmpty()){
                    updateUserInfo(newPhone, newPassword);
                    showToast("Lưu thông tin thành công!");
                } else{
                    showToast("Vui lòng điền đầy đủ thông tin!");
                }
            }
        });

    }

    private void updateUserInfo(String newPhone, String newPassword) {
        UserHandler userHandler = new UserHandler(this);
        SQLiteDatabase db = userHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_phone", newPhone);
        values.put("user_password", newPassword);
        String selection = "user_phone = ?";
        String[] selectionArgs = {newPhone};

        int rowsUpdated = db.update("tbl_user", values, selection, selectionArgs);

        db.close();
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    private void addControl(){
        edtphone = (EditText) findViewById(R.id.edtphone);
//        edtname = (EditText) findViewById(R.id.edtname);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnDangxuat = (Button) findViewById(R.id.btnDangxuat);
        iduser2 = (TextView) findViewById(R.id.iduser2);
        btnHome = (Button) findViewById(R.id.btnHome);

    }
}