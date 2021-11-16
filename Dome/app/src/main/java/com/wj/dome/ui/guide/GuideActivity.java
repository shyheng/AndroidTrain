package com.wj.dome.ui.guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.wj.dome.R;
import com.wj.dome.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = findViewById(R.id.guide);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GuideFragment1());
        fragments.add(new GuideFragment2());
        fragments.add(new GuideFragment3());

        GuideAdapter adapter = new GuideAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }
}