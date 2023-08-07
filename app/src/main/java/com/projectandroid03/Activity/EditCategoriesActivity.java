package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projectandroid03.Activity.Adapter.CategoryAdapter;
import com.projectandroid03.Activity.Adapter.ProductAdapter;
import com.projectandroid03.Activity.Handler.CategoryHandler;
import com.projectandroid03.Activity.Handler.ProductHandler;
import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.R;

import java.util.List;

public class EditCategoriesActivity extends AppCompatActivity {

    ActionBar actionBar;
    private static final int REQUEST_IMAGE_PICK = 1;
    private EditText suaCategoryName;
    private ImageView imgCategory;
    Button suaChooseImage, suaCategory;
    private Uri selectedImageUri;
    ProductHandler productHandler;
    CategoryHandler categoryHandler;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_categories);

        addControl();

        Intent intent = getIntent();
//        Log.d("DEBUG", "category_id: " + intent.getIntExtra("category_id", -1));
//        Log.d("DEBUG", "category_name: " + intent.getStringExtra("category_name"));
//        Log.d("DEBUG", "category_image: " + intent.getStringExtra("category_image"));

        int categoryId = intent.getIntExtra("category_id", -1);
        String categoryName = intent.getStringExtra("category_name");
        String categoryImageString = intent.getStringExtra("category_image");

        suaChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        });
        suaCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryName = suaCategoryName.getText().toString().trim();
                if (!TextUtils.isEmpty(categoryName)) {
                    if (selectedImageUri != null) {
                        boolean isUpdated = categoryHandler.updateCategory(categoryId, categoryName, selectedImageUri);
                        if (isUpdated) {
                            showToast("Danh mục đã được cập nhật");
                            suaCategoryName.setText("");

                            List<Category> updatedCategories = categoryHandler.getAllCategories();
                            categoryAdapter.clear();
                            categoryAdapter.addAll(updatedCategories);
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            showToast("Cập nhật danh mục thất bại");
                        }
                    } else {
                        showToast("Vui lòng chọn ảnh cho danh mục!");
                    }
                } else {
                    showToast("Vui lòng nhập tên danh mục");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();
                if (imageUri != null) {
                    try {
                        selectedImageUri = imageUri;
                        imgCategory.setImageURI(imageUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "Không thể lấy ảnh", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Không thể lấy ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    private void addControl(){
        suaCategoryName = (EditText) findViewById(R.id.suaCategoryName);
        imgCategory = (ImageView) findViewById(R.id.imgCategory);
        suaChooseImage = (Button) findViewById(R.id.suaChooseImage);
        suaCategory = (Button) findViewById(R.id.suaCategory);
    }
}