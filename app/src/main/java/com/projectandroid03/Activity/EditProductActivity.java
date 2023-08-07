package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projectandroid03.Activity.Adapter.ProductAdapter;
import com.projectandroid03.Activity.Handler.CategoryHandler;
import com.projectandroid03.Activity.Handler.ProductHandler;
import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.R;

import java.util.List;

public class EditProductActivity extends AppCompatActivity {
    ActionBar actionBar;
    private static final int REQUEST_IMAGE_PICK = 1;
    private Spinner suaCategory;
    private EditText suaProductName;
    private EditText suaProductPrice;
    private EditText suaProductDescription;
    private ImageView suaimgProduct;
    TextView tv1, tv2;
    Button editChooseImage2, editProduct;
    private Uri selectedImageUri;
    ProductHandler productHandler;
    CategoryHandler categoryHandler;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Sửa sản phẩm");

        addControl();

        Intent intent = getIntent();
//        Log.d("DEBUG", "product_id: " + intent.getIntExtra("product_id", -1));
//        Log.d("DEBUG", "category_id: " + intent.getIntExtra("category_id", -1));
//        Log.d("DEBUG", "product_name: " + intent.getStringExtra("product_name"));
//        Log.d("DEBUG", "product_price: " + intent.getStringExtra("product_price"));
//        Log.d("DEBUG", "product_desc: " + intent.getStringExtra("product_desc"));
//        Log.d("DEBUG", "product_image: " + intent.getStringExtra("product_image"));

        int productId = intent.getIntExtra("product_id", -1);
        int categoryId = intent.getIntExtra("category_id", -1);
        String productName = intent.getStringExtra("product_name");
        String productPrice = intent.getStringExtra("product_price");
        String productDescription = intent.getStringExtra("product_desc");
        String productImageString = intent.getStringExtra("product_image");


        CategoryHandler categoryHandler = new CategoryHandler(this);
        List<Category> categoryList = categoryHandler.getAllCategoriesSpinner();

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suaCategory.setAdapter(adapter);

        productHandler = new ProductHandler(this);
        editChooseImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        });
        editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category selectedCategory = (Category) suaCategory.getSelectedItem();
                int categoryId = selectedCategory.getCategoryId();
                String productName = suaProductName.getText().toString().trim();
                String productPrice = suaProductPrice.getText().toString().trim();
                String productDescription = suaProductDescription.getText().toString().trim();

                if (!TextUtils.isEmpty(productName)) {
                    if (selectedImageUri != null) {
                        boolean isUpdated = productHandler.updateProduct(productId, categoryId, productName, productPrice, productDescription, selectedImageUri);
                        if (isUpdated) {
                            showToast("Sản phẩm đã được cập nhật");
                            suaProductName.setText("");
                            suaProductPrice.setText("");
                            suaProductDescription.setText("");

                            List<Product> updatedProducts = productHandler.getAllProduct();
                            productAdapter.clear();
                            productAdapter.addAll(updatedProducts);
                            productAdapter.notifyDataSetChanged();
                        } else {
                            showToast("Cập nhật sản phẩm thất bại");
                        }
                    } else {
                        showToast("Vui lòng chọn ảnh cho sản phẩm!");
                    }
                } else {
                    showToast("Vui lòng nhập tên sản phẩm");
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
                        suaimgProduct.setImageURI(imageUri);
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
        suaCategory = (Spinner) findViewById(R.id.suaCategory);
        suaProductName = (EditText) findViewById(R.id.suaProductName);
        suaProductPrice = (EditText) findViewById(R.id.suaProductPrice);
        suaProductDescription = (EditText) findViewById(R.id.suaProductDescription);
        suaimgProduct = (ImageView) findViewById(R.id.suaimgProduct);
        editChooseImage2 = (Button) findViewById(R.id.editChooseImage2);
        editProduct = (Button) findViewById(R.id.editProduct);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
    }
}