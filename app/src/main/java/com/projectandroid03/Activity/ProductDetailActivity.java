package com.projectandroid03.Activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projectandroid03.Activity.Adapter.CommentAdapter;
import com.projectandroid03.Activity.Handler.CommentHandler;
import com.projectandroid03.Activity.Handler.ProductHandler;
import com.projectandroid03.Activity.Model.Comment;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.Activity.Model.User;
import com.projectandroid03.R;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView nameProduct, priceProduct, descProduct;
    private ImageView imageProduct;
    private ListView listView;
    private RecyclerView recyclerView;
    private Button btnAddComment;
    private EditText edtComment;
    private int selectedProductId;
    private String userId;

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
            userId = getIntent().getStringExtra("selectedUserId");
            selectedProductId = getIntent().getIntExtra("selectedProductId", -1);
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


        CommentHandler commentHandler = new CommentHandler(this);
        List<Comment> commentList = commentHandler.getAllComment(selectedProductId);
        RecyclerView recyclerView1 = findViewById(R.id.listComment);
        List<User> userList = new ArrayList<>();
        CommentAdapter commentAdapter = new CommentAdapter(this, commentList, userList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(commentAdapter);
        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = edtComment.getText().toString().trim();


                if (userId != null && !userId.isEmpty()) {

                    long commentId = commentHandler.addComment(selectedProductId, comment, Integer.parseInt(userId));

                    if (commentId != -1) {
                        showToast("Bình luận thành công!");

                        edtComment.setText("");
                        List<Comment> commentList = commentHandler.getAllComment(selectedProductId);
                        commentAdapter.updateData(commentList);
                    } else {
                        showToast("Bình luận thất bại!");
                    }
                } else {

                    showToast("Bạn cần đăng nhập để thêm bình luận!");

                     Intent intent = new Intent(ProductDetailActivity.this, Login.class);
                     startActivity(intent);
                }
            }
        });



    }
    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private void addControl(){
        imageProduct = (ImageView) findViewById(R.id.imageProduct3);
        nameProduct = (TextView) findViewById(R.id.nameProduct3);
        priceProduct = (TextView) findViewById(R.id.priceProduct3);
        descProduct = (TextView) findViewById(R.id.descProduct3);
        btnAddComment = (Button) findViewById(R.id.button10);
        recyclerView = (RecyclerView) findViewById(R.id.listComment);
        edtComment = (EditText) findViewById(R.id.edtComment);

    }
}