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

import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.R;

import java.util.List;

public class CategoryAdapterClient extends ArrayAdapter {
    public CategoryAdapterClient(@NonNull Context context, List<Category> categoryList) {
        super(context, 0, categoryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category_client, parent, false);
        }
        ImageView imgCategory = convertView.findViewById(R.id.imageCategoryClient);
        TextView nameCategory = convertView.findViewById(R.id.nameCategoryClient);
        Category category = (Category) getItem(position);

        nameCategory.setText(category.getCategoryName());



        return convertView;

    }
}
