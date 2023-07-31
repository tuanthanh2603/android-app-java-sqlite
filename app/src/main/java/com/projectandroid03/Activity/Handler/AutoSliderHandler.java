package com.projectandroid03.Activity.Handler;

import android.os.Handler;

import androidx.viewpager.widget.ViewPager;

public class AutoSliderHandler {

    private static final long SLIDE_DELAY = 10000;
    private ViewPager viewPager;
    private int count;
    private Handler handler;

    public AutoSliderHandler(ViewPager viewPager, int count) {
        this.viewPager = viewPager;
        this.count = count;
        handler = new Handler();
    }

    public void startAutoSlider() {
        handler.postDelayed(autoSliderRunnable, SLIDE_DELAY);
    }

    public void stopAutoSlider() {
        handler.removeCallbacks(autoSliderRunnable);
    }

    private Runnable autoSliderRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPager.getCurrentItem();
            currentItem = (currentItem + 1) % count;
            viewPager.setCurrentItem(currentItem, true);
            handler.postDelayed(this, SLIDE_DELAY);
        }
    };
}

