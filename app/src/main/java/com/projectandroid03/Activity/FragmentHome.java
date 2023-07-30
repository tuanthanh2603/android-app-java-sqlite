package com.projectandroid03.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectandroid03.Activity.Adapter.SlidingBannerAdapter;
import com.projectandroid03.Activity.Handler.AutoSliderHandler;
import com.projectandroid03.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    //Khai báo cho sliding banner
    private ViewPager bannerViewPager;
    private int[] imageIds = {R.drawable.banner2, R.drawable.banner3};
    private AutoSliderHandler autoSliderHandler;

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

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Hủy bỏ việc auto sliding khi Fragment bị hủy
        autoSliderHandler.stopAutoSlider();
    }

}