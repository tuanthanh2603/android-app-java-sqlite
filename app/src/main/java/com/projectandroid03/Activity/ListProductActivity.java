package com.projectandroid03.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projectandroid03.Activity.Adapter.ProductAdapter;
import com.projectandroid03.Activity.Handler.ProductHandler;
import com.projectandroid03.Activity.Model.Product;

import com.projectandroid03.R;

import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    ActionBar actionBar;
    private ListView listView1;
    private ProductHandler productHandler;
    private ProductAdapter productAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Danh sách sản phẩm");
        listView1 = (ListView) findViewById(R.id.listViewProduct);

        productHandler = new ProductHandler(this);
        List<Product> products = productHandler.getAllProduct();
        productAdapter = new ProductAdapter(this, products);
        listView1.setAdapter(productAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = productAdapter.getItem(position);
                Intent intent = new Intent(ListProductActivity.this, EditProductActivity.class);
                intent.putExtra("product_id", selectedProduct.getProduct_id());
                intent.putExtra("category_id", selectedProduct.getCategory_id());
                intent.putExtra("product_name", selectedProduct.getProduct_name());
                intent.putExtra("product_price", selectedProduct.getProduct_price());
                intent.putExtra("product_desc", selectedProduct.getProduct_desc());

                String productImageString = selectedProduct.getProduct_image().toString();
                intent.putExtra("product_image", productImageString);

                startActivity(intent);
            }
        });


    }


}