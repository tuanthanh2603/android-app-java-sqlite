package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projectandroid03.Activity.Handler.UserHandler;
import com.projectandroid03.Activity.Model.User;
import com.projectandroid03.R;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    ActionBar actionBar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách thành viên");
        UserHandler userHandler = new UserHandler(this);
        listView = (ListView) findViewById(R.id.listviewuser);
        List<User> userList = userHandler.getAllUsers();
        List<String> userNames = new ArrayList<>();
        for (User user : userList) {
            userNames.add(user.getUserPhone());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userNames);
        listView.setAdapter(adapter);
    }
}