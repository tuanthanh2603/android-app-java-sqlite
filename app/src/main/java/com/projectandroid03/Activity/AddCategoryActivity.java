package com.projectandroid03.Activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projectandroid03.R;

public class AddCategoryActivity extends AppCompatActivity {
    ActionBar actionBar;
    Button btnChooseImage, btnAddCategory;
    EditText edtCategoryName;
    ImageView imgCategory;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private ActivityResultLauncher<String> pickImageLauncher;
    private Uri selectedImageUri;
    private SQLiteDatabase db;
    CategoryHandler categoryHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Thêm danh mục sản phẩm");
        categoryHandler = new CategoryHandler(this);
        db = categoryHandler.getWritableDatabase();
        categoryHandler.insertSampleData(db);
        addControl();
        addEvent();
    }
    private void addEvent(){
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectionDialog();
            }
        });
        pickImageLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    // Hiển thị ảnh đã chọn vào ImageView
                    selectedImageUri = result;
                    imgCategory.setImageURI(result);
                }
            }
        });
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = edtCategoryName.getText().toString().trim();
                if (!TextUtils.isEmpty(categoryName)) {
                    // Kiểm tra xem người dùng đã chọn ảnh hay chưa
                    if (selectedImageUri != null) {
                        // Thực hiện thêm danh mục vào cơ sở dữ liệu SQLite
                        boolean isAdded = addCategoryToDatabase(categoryName, selectedImageUri);

                        // Xóa nội dung của EditText sau khi thêm thành công
                        edtCategoryName.setText("");

                        // Xử lý thông báo hoặc các hành động khác dựa vào kết quả thêm danh mục
                        if (isAdded) {
                            showToast("Thêm danh mục thành công!");
                        } else {
                            showToast("Thêm danh mục thất bại!");
                        }
                    } else {
                        showToast("Vui lòng chọn ảnh cho danh mục!");
                    }
                } else {
                    showToast("Vui lòng nhập tên danh mục!");
                }
            }
        });

    }

    private boolean addCategoryToDatabase(String categoryName, @NonNull Uri imageUri) {
        String imagePath = imageUri.toString();
        boolean isCategoryAdded = false; // Biến cờ để lưu trạng thái thêm danh mục

        ContentValues values = new ContentValues();
        values.put("category_name", categoryName);
        values.put("category_image", imagePath);
        long newRowId = db.insert("tbl_category", null, values);

        if (newRowId != -1) {

            isCategoryAdded = true;
        } else {
            // Thêm danh mục thất bại

            isCategoryAdded = false;
        }

        return isCategoryAdded;
    }






    private void showImageSelectionDialog(){
        final CharSequence[] options = {"Chọn từ thư viện", "Chụp ảnh"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn hình ảnh");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Chọn từ thư viện")) {
                    pickImageLauncher.launch("image/*");
                } else if (options[item].equals("Chụp ảnh")) {
//                    captureImageLauncher.launch(MediaStore.ACTION_IMAGE_CAPTURE);
                }
            }
        });
        builder.show();
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void addControl(){
        btnAddCategory = (Button) findViewById(R.id.btnAddCategory);
        btnChooseImage = (Button) findViewById(R.id.btnChooseImage);
        edtCategoryName = (EditText) findViewById(R.id.edtCategoryName);
        imgCategory = (ImageView) findViewById(R.id.imgCategory);
    }

}