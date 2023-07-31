package com.projectandroid03.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projectandroid03.Activity.Adapter.CategoryAdapter;
import com.projectandroid03.Activity.Adapter.CategoryAdapterClient;
import com.projectandroid03.Activity.Handler.CategoryHandler;
import com.projectandroid03.Activity.Model.Category;
import com.projectandroid03.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView listView;
    private CategoryHandler categoryHandler;
    private CategoryAdapterClient adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCategory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCategory.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCategory newInstance(String param1, String param2) {
        FragmentCategory fragment = new FragmentCategory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        listView = (ListView) rootView.findViewById(R.id.listViewCate);
        categoryHandler = new CategoryHandler(getContext());
        List<Category> categoryList = categoryHandler.getAllCategories();
        CategoryAdapterClient categoryAdapter = new CategoryAdapterClient(getContext(),categoryList);
        listView.setAdapter(categoryAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category selectedCategory = categoryAdapter.getCategoryAtPosition(position);
                if(selectedCategory != null){
                    Intent intent = new Intent(getContext(), ListProductClient.class);
                    intent.putExtra("selectedCategoryId", selectedCategory);
                    startActivity(intent);
                }

            }
        });

        return rootView;
    }
}