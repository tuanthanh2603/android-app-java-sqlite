package com.projectandroid03.Activity;

import static com.projectandroid03.R.id.tvidp;

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

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        TextView textViewCategoryId = findViewById(R.id.tvidp);
        textViewCategoryId.setText("Product ID =  " + selectedProductId);
    }
}