package com.projectandroid03.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
//    private String userId;

    private ProductHandler productHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_client);
        getSupportActionBar().hide();

        ImageView imageView = (ImageView) findViewById(R.id.imageView5);




        int selectedCategoryId = getIntent().getIntExtra("selectedCategoryId", -1);

        TextView textViewCategoryId = findViewById(R.id.textViewCategoryId);
        textViewCategoryId.setText("Category ID:  " + selectedCategoryId);


        String userId = getIntent().getStringExtra("selectedUserId");
        TextView textViewUserId = findViewById(R.id.textViewUserId);
        textViewUserId.setText("User ID: " + userId);




//        Log.d("Selected Category ID", String.valueOf(selectedCategoryId));



        productHandler = new ProductHandler(this);
        List<Product> productList = productHandler.getProductsByCategoryId(selectedCategoryId);
//        gridView = (GridView) findViewById(R.id.gridViewProductClient);
//        listView = (ListView) findViewById(R.id.listViewProductClient);

//        int numberOfColumns = 2;
//        gridView.setNumColumns(numberOfColumns);
//        gridView.setAdapter(productAdapterClient);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProductClient);
        ProductAdapterClient productAdapterClient = new ProductAdapterClient(this, productList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapterClient);



//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                showToast("Bạn đã chọn sản phẩm: " );
//            }
//        });


//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Product selectedProduct = productList.get(position);
//                showToast("Bạn đã chọn sản phẩm: " + selectedProduct.getProduct_name());
//
//
//                Intent intent = new Intent(ListProductClient.this, ProductDetailActivity.class);
//                intent.putExtra("selectedProductId", selectedProduct.getProduct_id());
//                startActivity(intent);
//            }
//        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();
            }
        });






    }
    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}