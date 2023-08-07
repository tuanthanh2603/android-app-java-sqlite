package com.projectandroid03.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.projectandroid03.Activity.Adapter.ProductAdapter;
import com.projectandroid03.Activity.Adapter.SlidingBannerAdapter;
import com.projectandroid03.Activity.Handler.AutoSliderHandler;
import com.projectandroid03.Activity.Handler.ProductHandler;
import com.projectandroid03.Activity.Model.Product;
import com.projectandroid03.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {
    private ListView listViewProducts;
    private List<Product> allProducts;
    private ProductAdapter productAdapter;
    EditText searchEditText;

    //Khai báo cho sliding banner
    private ViewPager bannerViewPager;
    private int[] imageIds = {R.drawable.banner2, R.drawable.banner3};
    private AutoSliderHandler autoSliderHandler;


    private void initListView(View rootView) {
        listViewProducts = rootView.findViewById(R.id.listViewProducts);
        listViewProducts.setVisibility(View.GONE); // Ẩn ListView khi ban đầu
    }
    private void showListView() {
        listViewProducts.setVisibility(View.VISIBLE);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //NOTE: inflate chuyển đổi tệp fragment_home thành đối tượng VIEW chứa giao diện
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //NOTE: findViewById() không tồn tại trong Fragment. Phương thức findViewById() chỉ có sẵn trong Activity.
        //sử dụng getView() trong Fragment để tìm các View trong layout của Fragment.
        bannerViewPager = rootView.findViewById(R.id.bannerViewPager);
        SlidingBannerAdapter adapter = new SlidingBannerAdapter(requireContext(), imageIds);
        bannerViewPager.setAdapter(adapter);
        // Tạo và sử dụng class AutoSliderHandler
        autoSliderHandler = new AutoSliderHandler(bannerViewPager, adapter.getCount());
        autoSliderHandler.startAutoSlider();

        // Khởi tạo ProductHandler
        ProductHandler productHandler = new ProductHandler(requireContext());

        // Lấy danh sách tất cả sản phẩm từ cơ sở dữ liệu
        allProducts = productHandler.getAllProduct();

        // Tìm AutoCompleteTextView trong layout của FragmentHome
        AutoCompleteTextView autoCompleteTextViewSearch = rootView.findViewById(R.id.autoCompleteTextViewSearch);

        // Khởi tạo Adapter cho AutoCompleteTextView
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, new ArrayList<>());

        // Thiết lập Adapter cho AutoCompleteTextView
        autoCompleteTextViewSearch.setAdapter(autoCompleteAdapter);
        // Thêm sự kiện lắng nghe khi người dùng nhập nội dung vào AutoCompleteTextView
        autoCompleteTextViewSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Không cần xử lý trước khi thay đổi nội dung
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Lấy nội dung tìm kiếm từ `autoCompleteTextViewSearch`
                String searchText = charSequence.toString().trim();

                // Thực hiện đề xuất các tên sản phẩm dựa trên nội dung tìm kiếm
                performSuggestion(searchText, autoCompleteAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Không cần xử lý sau khi thay đổi nội dung
            }
        });
        // Khởi tạo ListView và ProductAdapter
        listViewProducts = rootView.findViewById(R.id.listViewProducts);
        productAdapter = new ProductAdapter(requireContext(), allProducts);
        listViewProducts.setAdapter(productAdapter);

        performSearch("");

        autoCompleteTextViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy tên sản phẩm từ mục được chọn
                String selectedProductName = (String) parent.getItemAtPosition(position);

                // Tạo intent để chuyển sang ProductDetailActivity
                Intent intent = new Intent(requireContext(), ProductDetailActivity.class);
                intent.putExtra("selectedProductName", selectedProductName);
                intent.putExtra("selectedProductId", getProductIDByName(selectedProductName));


                // Chuyển sang ProductDetailActivity
                startActivity(intent);
            }
        });

        return rootView;
    }
    private int getProductIDByName(String productName) {
        int productId = -1;
        for (Product product : allProducts) {
            if (product.getProduct_name().equalsIgnoreCase(productName)) {
                productId = product.getProduct_id();
                break;
            }
        }
        return productId;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Hủy bỏ việc auto sliding khi Fragment bị hủy
        autoSliderHandler.stopAutoSlider();
    }
    private void performSuggestion(String searchText, ArrayAdapter<String> autoCompleteAdapter) {
        // Xóa dữ liệu cũ của Adapter
        autoCompleteAdapter.clear();

        // Lặp qua từng sản phẩm và kiểm tra nội dung tìm kiếm
        for (Product product : allProducts) {
            if (product.getProduct_name().toLowerCase().contains(searchText.toLowerCase())) {
                // Nếu tên sản phẩm chứa nội dung tìm kiếm, thêm vào Adapter để đề xuất
                autoCompleteAdapter.add(product.getProduct_name());
            }
        }

        // Hiển thị đề xuất lựa chọn
        autoCompleteAdapter.notifyDataSetChanged();
    }
    // Phương thức thực hiện tìm kiếm sản phẩm
    private void performSearch(String searchText) {
        // Tạo một danh sách mới để lưu các sản phẩm tìm kiếm được
        List<Product> searchResult = new ArrayList<>();

        // Lặp qua từng sản phẩm và kiểm tra nội dung tìm kiếm
        for (Product product : allProducts) {
            if (product.getProduct_name().toLowerCase().contains(searchText.toLowerCase())) {
                // Nếu tên sản phẩm chứa nội dung tìm kiếm, thêm sản phẩm này vào kết quả tìm kiếm
                searchResult.add(product);
            }
        }

        // Nếu kết quả tìm kiếm không rỗng, hiển thị ListView và cập nhật dữ liệu
        if (!searchResult.isEmpty()) {
            showListView();
            productAdapter.setProducts(searchResult);
            productAdapter.notifyDataSetChanged();
        } else {
            // Nếu không có kết quả tìm kiếm, ẩn ListView
            listViewProducts.setVisibility(View.GONE);
        }
    }


}