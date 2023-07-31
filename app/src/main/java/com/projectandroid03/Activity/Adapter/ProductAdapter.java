package com.projectandroid03.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.projectandroid03.Activity.Handler.CategoryHandler;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.R;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> products;

    public ProductAdapter(@NonNull Context context, List<Product> products) {
        super(context, R.layout.item_product, products);
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);

        }
        ImageView imgProduct = convertView.findViewById(R.id.imageProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductPrice = convertView.findViewById(R.id.tvProductPrice);
        TextView tvCategoryProduct = convertView.findViewById(R.id.tvCategoryProduct);


        Product product = getItem(position);
        int categoryId = product.getCategory_id();

        CategoryHandler categoryHandler = new CategoryHandler(this.getContext());
        String categoryName = categoryHandler.getCategoryNameByCategoryId(categoryId);



        // Hiển thị thông tin sản phẩm và tên danh mục tương ứng
//        imgProduct.setImageURI(product.getProduct_image());

        tvProductName.setText(product.getProduct_name());
        tvProductPrice.setText("Giá: " + product.getProduct_price());
        tvCategoryProduct.setText("Danh mục: " + categoryName);

//        Picasso.get().load(product.getProduct_image()).into(imgProduct);
//        String imageUri = "content://com.android.providers.downloads.documents/document/msf%3A49";
//        Glide.with(this.getContext()).load(Uri.parse(imageUri)).into(imgProduct);


        return convertView;


    }
}
