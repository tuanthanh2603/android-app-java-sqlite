package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private List<User> userList = new ArrayList<>();

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
        addEvent();
    }
    private void addEvent(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
                String selectedUserPhone = adapter.getItem(position);
                if (selectedUserPhone.equals("0877717575")) {

                    new AlertDialog.Builder(ListUserActivity.this)
                            .setTitle("Thông báo")
                            .setMessage("Không được phép xoá thành viên này.")
                            .setPositiveButton("OK", null)
                            .show();

                } else {

                    new AlertDialog.Builder(ListUserActivity.this)
                            .setTitle("Xác nhận xoá")
                            .setMessage("Bạn có chắc chắn muốn xoá thành viên này?")
                            .setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Xử lý xoá user tại vị trí position
                                    deleteUserFromSQLite(position);
                                }
                            })
                            .setNegativeButton("Hủy", null)
                            .show();
                }
                return true;
            }
        });

    }

    private void deleteUserFromSQLite(int position) {
        UserHandler userHandler = new UserHandler(this);
        List<User> userList = userHandler.getAllUsers();
        if(position >= 0 && position < userList.size()){
            User userToDelete = userList.get(position);
            userHandler.deleteUser(userToDelete);
            userList.remove(position);
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
            adapter.remove(adapter.getItem(position));
            adapter.notifyDataSetChanged();
        }
    }


}