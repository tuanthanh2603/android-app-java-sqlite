package com.projectandroid03.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import android.widget.SearchView;

//import com.projectandroid03.Controller.CategoryHandler;
//import com.projectandroid03.Model.Category;
import com.projectandroid03.R;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Xóa thiết kế action_bar mặc định của framelayout
        getSupportActionBar().hide();

        actionBar = getSupportActionBar();

        frameLayout = findViewById(R.id.frameFragment);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_home) {
                    loadFragment(new FragmentHome());
                    return true;
                } else if (id == R.id.item_category) {
                    loadFragment(new FragmentCategory());
                    return true;
                } else if (id == R.id.item_order) {
                    loadFragment(new FragmentOrder());
                    return true;
                } else if (id == R.id.item_account) {
                    loadFragment(new FragmentAccount());
                    return true;
                }
                return false;
            }
        });
        // Mặc định hiển thị FragmentHome
        loadFragment(new FragmentHome());



//        addControls();
//        ActionBars();
//        setupBannerViewFlipper();
//        categoryHandler = new CategoryHandler(getApplicationContext(), CategoryHandler.DB_NAME, null,1);
//        categoryHandler.onCreate(db);
//        categoryHandler.initData();
        ///

//        lsCategory = categoryHandler.loadData();
//        valuesLView = convertCategoryToString(lsCategory);
//        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, valuesLView);
//        listViewHome.setAdapter(adapter);



    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



//    public void loadFragment(Fragment fragment){
//
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.frameFragment,fragment);
//        ft.commit();
//
//    }

//    ArrayList<String> convertCategoryToString(ArrayList<Category> lsCategory) {
//        ArrayList<String> lsCate = new ArrayList<>();
//        for(Category cate: lsCategory){
//            String s = String.valueOf(cate.getCategoryId()) + " " + cate.getCategoryName();
//            lsCate.add(s);
//        }
//        return lsCate;
//    }


//    private void addControls(){
//        toolbarHome = (Toolbar) findViewById(R.id.toolbarhome);
//        viewFlipperHome = (ViewFlipper) findViewById(R.id.viewfipperhome);
//        recyclerViewHome = (RecyclerView) findViewById(R.id.recyclerviewhome);
//        listViewHome = (ListView) findViewById(R.id.listviewhome);
//        navigationViewHome = (NavigationView) findViewById(R.id.navigationviewhome);
//        drawerLayoutHome = (DrawerLayout) findViewById(R.id.drawerhome);
//
//
//    }




//    private void setupBannerViewFlipper() {
//        int[] bannerImages = {
//                R.drawable.banner1,
//                R.drawable.banner2,
//                R.drawable.banner3
//        };
//
//        for (int i = 0; i < bannerImages.length; i++) {
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(bannerImages[i]);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipperHome.addView(imageView);
//        }
//
//        viewFlipperHome.setFlipInterval(3000);
//        viewFlipperHome.setAutoStart(true);
//        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
//        Animation slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
//        viewFlipperHome.setInAnimation(slideIn);
//        viewFlipperHome.setOutAnimation(slideOut);
//    }


//    private void ActionBars(){
//        setSupportActionBar(toolbarHome);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbarHome.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
//        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayoutHome.openDrawer(GravityCompat.START);
//            }
//        });
//    }



}