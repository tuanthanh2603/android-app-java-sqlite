package com.projectandroid03.Activity;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectandroid03.R;

public class ProductDetailActivity extends AppCompatActivity {

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
        int selectedProductId = getIntent().getIntExtra("selectedProductId", -1);

        int selectedUserId = getIntent().getIntExtra("selectedUserId", -1);



        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textViewProductId = findViewById(R.id.idproduct);
        textViewProductId.setText("Product ID:  " + selectedProductId);

        TextView textViewUserId = findViewById(R.id.iduser);
        textViewUserId.setText("User ID:  " + selectedUserId);

        addControl();

    }
    private void addControl(){

    }
}