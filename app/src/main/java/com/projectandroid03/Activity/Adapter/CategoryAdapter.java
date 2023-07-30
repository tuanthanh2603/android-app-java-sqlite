package com.projectandroid03.Activity.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daimajia.swipe.SwipeLayout;
import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, R.layout.item_category, categories);
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);
        }
        ImageView imgCategory = convertView.findViewById(R.id.imgCategory2);
        TextView tvCategoryName = convertView.findViewById(R.id.tvCategoryName);

        Category category = getItem(position);
//        imgCategory.setImageURI(category.getImageUri());
        tvCategoryName.setText(category.getCategoryName());
//        Picasso.get().load(category.getImageUri()).into(imgCategory);

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onCategoryLongClick(category);
                }
                return true;
            }
        });


        return convertView;
    }

    public void updateCategories(List<Category> updatedCategories) {
        this.categories.clear();
        this.categories.addAll(updatedCategories);
        notifyDataSetChanged();
    }

    public interface OnCategoryLongClickListener {
        void onCategoryLongClick(Category category);
    }
    private OnCategoryLongClickListener longClickListener;

    public void setOnCategoryLongClickListener(OnCategoryLongClickListener listener) {
        this.longClickListener = listener;
    }


}
