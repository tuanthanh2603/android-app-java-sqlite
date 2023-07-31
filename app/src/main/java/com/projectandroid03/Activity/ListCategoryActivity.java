package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;

import com.projectandroid03.Activity.Adapter.CategoryAdapter;
import com.projectandroid03.Activity.Handler.CategoryHandler;
import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.R;

import java.util.List;

public class ListCategoryActivity extends AppCompatActivity {
    ActionBar actionBar;

    private CategoryHandler categoryHandler;
    private ListView listView;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Danh sách danh mục");
        listView = findViewById(R.id.listViewCategories);

        categoryHandler = new CategoryHandler(this);
        List<Category> categories = categoryHandler.getAllCategories();
        adapter = new CategoryAdapter(this, categories);
        listView.setAdapter(adapter);

        adapter.setOnCategoryLongClickListener(new CategoryAdapter.OnCategoryLongClickListener() {
            @Override
            public void onCategoryLongClick(Category category) {
                showDeleteConfirmationDialog(category);
            }
        });


    }

    private void showDeleteConfirmationDialog(Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa danh mục");
        builder.setMessage("Bạn có chắc chắn muốn xóa danh mục này?");
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isDeleted = categoryHandler.deleteCategory(category.getCategoryId());
                if (isDeleted) {
                    List<Category> updatedCategories = categoryHandler.getAllCategories();
                    adapter.updateCategories(updatedCategories);
                }
            }
        });
        builder.setNegativeButton("Huỷ", null);
        builder.show();
    }


}