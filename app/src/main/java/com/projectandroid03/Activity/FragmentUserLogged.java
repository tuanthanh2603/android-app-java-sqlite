package com.projectandroid03.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.projectandroid03.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUserLogged#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUserLogged extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout linearLayout;
    private String userId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentUserLogged() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUserLogged.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUserLogged newInstance(String param1, String param2) {
        FragmentUserLogged fragment = new FragmentUserLogged();
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
        View view = inflater.inflate(R.layout.fragment_user_logged, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.edit);
        userId = getArguments().getString("selectedUserId");
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("selectedUserId", userId);
                startActivity(intent);

            }
        });

        return view;

    }

    private void showToast(String message) {
        Context context = requireContext();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}