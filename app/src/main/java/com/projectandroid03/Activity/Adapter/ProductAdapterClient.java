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

public class ProductAdapterClient extends ArrayAdapter<Product> {
    private Context context;
    private  List<Product> products;
    public ProductAdapterClient(@NonNull Context context, List<Product> products) {
        super(context, R.layout.item_product_client, products);
        this.context = context;
        this.products = products;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product_client, parent, false);
        }
        ImageView imgProduct = convertView.findViewById(R.id.imageProductClient);
        TextView tvProductNameClient = convertView.findViewById(R.id.nameProductClient);
        TextView tvProductPriceClient = convertView.findViewById(R.id.priceProductClient);



        Product product = getItem(position);


        tvProductNameClient.setText(product.getProduct_name());
        tvProductPriceClient.setText(product.getProduct_price());

        return convertView;
    }
}
