package com.projectandroid03.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

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
    private GridView gridView;
    private ProductAdapterClient adapterClient;

    private ProductHandler productHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_client);
        getSupportActionBar().hide();



        int selectedCategoryId = getIntent().getIntExtra("selectedCategoryId", -1);

        TextView textViewCategoryId = findViewById(R.id.textViewCategoryId);
        textViewCategoryId.setText("Category ID =  " + selectedCategoryId);
//        Log.d("Selected Category ID", String.valueOf(selectedCategoryId));



        productHandler = new ProductHandler(this);
        List<Product> productList = productHandler.getProductsByCategoryId(selectedCategoryId);
        gridView = (GridView) findViewById(R.id.girdViewProductClient);
        ProductAdapterClient productAdapterClient = new ProductAdapterClient(this, productList);
        int numberOfColumns = 2;
        gridView.setNumColumns(numberOfColumns);
        gridView.setAdapter(productAdapterClient);


    }
}