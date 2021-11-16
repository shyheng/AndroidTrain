package com.wj.dome.ui.minute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wj.dome.R;
import com.wj.dome.adapter.MinuteAdapter;
import com.wj.dome.model.MinuteModel;

import java.util.ArrayList;
import java.util.List;

public class MinuteActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minute);

        viewPager = findViewById(R.id.vp_minute);

        List<MinuteModel> models = new ArrayList<>();
        models.add(new MinuteModel(R.drawable.banner_1,"第一张轮播图"));
        models.add(new MinuteModel(R.drawable.banner_2,"第二张轮播图"));
        models.add(new MinuteModel(R.drawable.banner_3,"第三张轮播图"));
        models.add(new MinuteModel(R.drawable.banner_4,"第四张轮播图"));

        MinuteAdapter minuteAdapter = new MinuteAdapter(models,MinuteActivity.this);
        viewPager.setAdapter(minuteAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MinuteActivity.this,"第"+(position+1)+"张",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}