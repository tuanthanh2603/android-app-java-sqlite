package com.projectandroid03.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.projectandroid03.Activity.Adapter.CategoryAdapterClient;
import com.projectandroid03.Activity.Adapter.ProductAdapter;
import com.projectandroid03.Activity.Adapter.ProductAdapterClient;
import com.projectandroid03.Activity.Handler.CategoryHandler;
import com.projectandroid03.Activity.Handler.ProductHandler;
import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.R;

import java.util.List;

public class ListProductClient extends AppCompatActivity {
    private ListView listView;
    private ProductAdapterClient adapterClient;

    private ProductHandler productHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_client);
        getSupportActionBar().hide();

//        Category selectedCategory = (Category) getIntent().getSerializableExtra("selectedCategory");

        listView = (ListView) findViewById(R.id.listViewProductClient);
        productHandler = new ProductHandler(this);
        List<Product> productList = productHandler.getAllProduct();
        ProductAdapterClient productAdapterClient = new ProductAdapterClient(this, productList);
        listView.setAdapter(productAdapterClient);


    }
}