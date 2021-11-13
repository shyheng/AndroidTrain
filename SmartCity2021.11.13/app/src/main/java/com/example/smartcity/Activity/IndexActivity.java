package com.example.smartcity.Activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity.Fragment.JfFragment;
import com.example.smartcity.R;
import com.example.smartcity.Fragment.SyFragment;
import com.example.smartcity.Fragment.WdFragment;
import com.example.smartcity.Fragment.ZhylFragment;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity {

    private RadioButton sy;
    private RadioButton jf;
    private RadioButton zhyl;
    private RadioButton wd;
    private RadioGroup rg;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
        fragment();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        sy = (RadioButton) findViewById(R.id.sy);
        jf = (RadioButton) findViewById(R.id.jf);
        zhyl = (RadioButton) findViewById(R.id.zhyl);
        wd = (RadioButton) findViewById(R.id.wd);
        rg = (RadioGroup) findViewById(R.id.rg);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    public void fragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SyFragment());
        fragments.add(new JfFragment());
        fragments.add(new ZhylFragment());
        fragments.add(new WdFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        rg.check(R.id.sy);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int i = 0;
                switch (checkedId){
                    case R.id.sy:
                        i = 0;
                        rg.check(R.id.sy);
                        break;
                    case R.id.jf:
                        i = 1;
                        rg.check(R.id.jf);
                        break;
                    case R.id.zhyl:
                        i = 2;
                        rg.check(R.id.zhyl);
                        break;
                    case R.id.wd:
                        i = 3;
                        rg.check(R.id.wd);
                        break;
                }
                if (vp.getCurrentItem() != i){
                    vp.setCurrentItem(i);
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg.check(R.id.sy);
                        break;
                    case 1:
                        rg.check(R.id.jf);
                        break;
                    case 2:
                        rg.check(R.id.zhyl);
                        break;
                    case 3:
                        rg.check(R.id.wd);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}