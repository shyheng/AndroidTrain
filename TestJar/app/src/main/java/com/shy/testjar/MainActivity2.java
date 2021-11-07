package com.shy.testjar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shy.testjar.Fragment.BlankFragment1;
import com.shy.testjar.Fragment.BlankFragment2;
import com.shy.testjar.Fragment.BlankFragment3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ViewPager2 vp;
    private TabLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List<String> list = new ArrayList<>();
        list.add("首页");
        list.add("新闻");
        list.add("安卓");

        vp = findViewById(R.id.vp);
        vp.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case 0:
                        return new BlankFragment1();
                    case 1:
                        return new BlankFragment2();
                    default:
                        return new BlankFragment3();
                }
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });


        tl = findViewById(R.id.tl);

        tl.setTabMode(TabLayout.MODE_AUTO);

        new TabLayoutMediator(tl, vp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position));
            }
        }).attach();

    }
}