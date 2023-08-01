package com.projectandroid03.Activity;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectandroid03.Activity.Handler.ProductHandler;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.R;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView nameProduct, priceProduct, descProduct;
    private ImageView imageProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().hide();
        ImageView imageView = (ImageView) findViewById(R.id.imageView5);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });






//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//        TextView textViewProductId = findViewById(R.id.idproduct);
//        textViewProductId.setText("Product ID:  " + selectedProductId);

//        String userId = getIntent().getStringExtra("selectedUserId");
//
//        TextView textViewUserId = findViewById(R.id.iduser);
//        textViewUserId.setText("User ID:  " + userId);

        addControl();
        Intent intent = getIntent();
        if(intent != null){
            String userId = getIntent().getStringExtra("selectedUserId");
            int selectedProductId = getIntent().getIntExtra("selectedProductId", -1);
            TextView textViewProductId = findViewById(R.id.idproduct);
            textViewProductId.setText("Product ID:  " + selectedProductId);
            TextView textViewUserId = findViewById(R.id.iduser);
            textViewUserId.setText("User ID:  " + userId);

            ProductHandler productHandler = new ProductHandler(this);
            Product selectedProduct = productHandler.getProductById(selectedProductId);
            if(selectedProduct != null){
                nameProduct.setText(selectedProduct.getProduct_name());
                priceProduct.setText(selectedProduct.getProduct_price() + " đ");
                descProduct.setText(selectedProduct.getProduct_desc());
            }
        }

    }

    private void addControl(){
        imageProduct = (ImageView) findViewById(R.id.imageProduct3);
        nameProduct = (TextView) findViewById(R.id.nameProduct3);
        priceProduct = (TextView) findViewById(R.id.priceProduct3);
        descProduct = (TextView) findViewById(R.id.descProduct3);
    }
}