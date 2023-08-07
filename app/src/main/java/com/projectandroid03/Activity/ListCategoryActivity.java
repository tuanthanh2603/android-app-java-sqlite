package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projectandroid03.Activity.Adapter.CategoryAdapter;
import com.projectandroid03.Activity.Handler.CategoryHandler;
import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.R;

import java.util.List;

public class ListCategoryActivity extends AppCompatActivity {
    ActionBar actionBar;
    private ListView listView;
    private CategoryAdapter categoryAdapter;
    private CategoryHandler categoryHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Danh sách danh mục");
        listView = findViewById(R.id.listViewCategories);

        categoryHandler = new CategoryHandler(this);
        List<Category> categories = categoryHandler.getAllCategories();
        categoryAdapter = new CategoryAdapter(this, categories);
        listView.setAdapter(categoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListView", "Item clicked at position: " + position);
                Category selectedCategory = categoryAdapter.getItem(position);
                Intent intent = new Intent(ListCategoryActivity.this, EditProductActivity.class);
                intent.putExtra("category_id", selectedCategory.getCategoryId());
                intent.putExtra("category_name", selectedCategory.getCategoryName());

                String categoryImageString = selectedCategory.getImageUri().toString();
                intent.putExtra("category_image", categoryImageString);
                startActivity(intent);
            }
        });
        categoryAdapter.setOnCategoryLongClickListener(new CategoryAdapter.OnCategoryLongClickListener() {
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
                    categoryAdapter.updateCategories(updatedCategories);
                }
            }
        });
        builder.setNegativeButton("Huỷ", null);
        builder.show();
    }
}