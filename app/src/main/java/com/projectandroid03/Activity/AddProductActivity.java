package com.projectandroid03.Activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.R;

import java.io.File;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    ActionBar actionBar;
    EditText edtProductName, edtProductPrice, edtProductDescription;
    TextView tv2, tv3;
    ImageView imgProduct;
    Button btnChooseImage2, btnAddProduct;
    Spinner spinnerCategory;
    private static final int PICK_IMAGE_REQUEST = 123;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 1;
    private ActivityResultLauncher<String> pickImageLauncher;
    private Uri selectedImageUri;
    private SQLiteDatabase db;
    ProductHandler productHandler;
    CategoryHandler categoryHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Thêm sản phẩm");

        productHandler = new ProductHandler(this);
        db = productHandler.getWritableDatabase();

        addControl();
        addEvent();
        CategoryHandler categoryHandler = new CategoryHandler(this);
        List<Category> categoryList = categoryHandler.getAllCategoriesSpinner();

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // Kiểm tra xem cơ sở dữ liệu đã tồn tại chưa

    }


    private void addEvent(){


        btnChooseImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);

            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
                int categoryId = selectedCategory.getCategoryId();
                String productName = edtProductName.getText().toString().trim();
                String productPrice = edtProductPrice.getText().toString().trim();
                String productDescription = edtProductDescription.getText().toString().trim();
                if(!TextUtils.isEmpty(productName)){
                    if(selectedImageUri != null){
                        boolean isAdded = productHandler.addProductToDataBase(categoryId, productName, productPrice, productDescription, selectedImageUri);
                        if(isAdded){
                            showToast("Thêm sản phẩm thành công");
                            edtProductName.setText("");
                            edtProductPrice.setText("");
                            edtProductDescription.setText("");
                        } else {
                            showToast("Thêm sản phẩm thất bại");

                            String ct = String.valueOf(categoryId);
                            tv3.setText(ct);

                            String uriString = selectedImageUri.toString();
                            tv2.setText(uriString);

                        }


                    } else {
                        showToast("Vui lòng chọn ảnh cho sản phẩm!");
                    }

                }else {
                    showToast("Vui lòng nhập tên sản phẩm");
                }



            }
        });

    }

//    private boolean addProductToDataBase(int categoryId, String productName, String productPrice, String productDescription, Uri selectedImageUri) {
//        String imagePath = selectedImageUri.toString();
//        boolean isProductAdded = false;
//        ContentValues values = new ContentValues();
//        values.put("product_name", productName);
//        values.put("category_id", categoryId);
//        values.put("product_price", productPrice);
//        values.put("product_desc", productDescription);
//        values.put("product_image", imagePath);
//        long newRowId = db.insert("tbl_product", null, values);
//        if(newRowId != -1){
//            isProductAdded = true;
//        }else {
//
//            isProductAdded = false;
//        }
//        return isProductAdded;
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            // Lấy URI của ảnh đã chọn
            if (data != null) {
                Uri imageUri = data.getData();
                try {
                    // Hiển thị ảnh lên ImageView
                    selectedImageUri = imageUri;
                    imgProduct.setImageURI(imageUri);


                } catch (Exception e) {
                    e.printStackTrace();
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
        edtProductName = (EditText) findViewById(R.id.edtProductName);
        edtProductPrice = (EditText) findViewById(R.id.edtProductPrice);
        edtProductDescription = (EditText) findViewById(R.id.edtProductDescription);
        imgProduct = (ImageView) findViewById(R.id.imgProduct);
        btnChooseImage2 = (Button) findViewById(R.id.btnChooseImage2);
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
    }
}