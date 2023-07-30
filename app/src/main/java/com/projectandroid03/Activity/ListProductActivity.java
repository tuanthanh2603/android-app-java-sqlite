package com.projectandroid03.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import com.projectandroid03.Activity.Adapter.ProductAdapter;
import com.projectandroid03.Activity.Model.Product;

import com.projectandroid03.R;

import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    ActionBar actionBar;
    private ListView listView;
    private ProductHandler productHandler;
    private ProductAdapter productAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Danh sách sản phẩm");
        listView = (ListView) findViewById(R.id.listViewProduct);

        productHandler = new ProductHandler(this);
        List<Product> products = productHandler.getAllProduct();
        productAdapter = new ProductAdapter(this, products);
        listView.setAdapter(productAdapter);




    }


}