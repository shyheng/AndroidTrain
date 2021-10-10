package com.shy.smartcity.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shy.smartcity.Fragment.banner.BannerFragment1;
import com.shy.smartcity.Fragment.banner.BannerFragment2;
import com.shy.smartcity.Fragment.banner.BannerFragment3;

public class BannerAdapter extends FragmentStateAdapter {


    public BannerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new BannerFragment1();
        }else if (position == 1){
            return new BannerFragment2();
        }else {
            return new BannerFragment3();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
