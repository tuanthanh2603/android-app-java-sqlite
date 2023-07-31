package com.projectandroid03.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.Activity.ProductDetailActivity;
import com.projectandroid03.R;

import java.util.List;

public class ProductAdapterClient extends RecyclerView.Adapter<ProductAdapterClient.ViewHolder> {
    private static Context context;
    private static List<Product> products;
    public ProductAdapterClient(@NonNull Context context, List<Product> products) {

        this.context = context;
        this.products = products;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_client, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvProductNameClient.setText(product.getProduct_name());
        holder.tvProductPriceClient.setText(product.getProduct_price());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if(convertView == null){
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product_client, parent, false);
//        }
//        ImageView imgProduct = convertView.findViewById(R.id.imageProductClient);
//        TextView tvProductNameClient = convertView.findViewById(R.id.nameProductClient);
//        TextView tvProductPriceClient = convertView.findViewById(R.id.priceProductClient);
//
//
//
//        Product product = getItem(position);
//
//
//        tvProductNameClient.setText(product.getProduct_name());
//        tvProductPriceClient.setText(product.getProduct_price());
//
//        return convertView;
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvProductNameClient;
        TextView tvProductPriceClient;

        public ViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imageProductClient);
            tvProductNameClient = itemView.findViewById(R.id.nameProductClient);
            tvProductPriceClient = itemView.findViewById(R.id.priceProductClient);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        Product selectedProductId = products.get(position);


                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        intent.putExtra("selectedProductId", selectedProductId.getProduct_id());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

}
