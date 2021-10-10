package com.shy.smartcity.Activity.Hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shy.smartcity.Adapter.GideAdapter;
import com.shy.smartcity.R;
import com.shy.smartcity.web.RequestImage;

import java.util.ArrayList;
import java.util.List;

public class HospitalSynopsisActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_synopsis);

        Button button = findViewById(R.id.hos_syn_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalSynopsisActivity.this,HospitalIndexActivity.class);
                startActivity(intent);
            }
        });

        viewPager = findViewById(R.id.hos_syn_vp);

        LayoutInflater inflaterBanner = getLayoutInflater().from(this);
        View view1 = inflaterBanner.inflate(R.layout.banner1, null);
        ImageView imageView1 = view1.findViewById(R.id.i1);
        RequestImage.request(imageView1, "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.tranbbs.com%2Fuploadfile%2F2015%2F0916%2F1442387838182669.png&refer=http%3A%2F%2Fwww.tranbbs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633867243&t=469a7103ca1bbe8e28c98fa09ef1e617", this);

        View view2 = inflaterBanner.inflate(R.layout.banner2, null);
        ImageView imageView2 = view2.findViewById(R.id.i2);
        RequestImage.request(imageView2, "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi1%2F220110546%2FO1CN01xh6zZD1Fu6jpg6w0q_%21%21220110546.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634036989&t=a65c5123cb8287ce23f7bbaeebf5f933", this);

        View view3 = inflaterBanner.inflate(R.layout.banner3, null);
        ImageView imageView3 = view3.findViewById(R.id.i3);
        RequestImage.request(imageView3, "https://img2.baidu.com/it/u=3120502424,650868114&fm=26&fmt=auto&gp=0.jpg", this);

        List<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

        GideAdapter adapter = new GideAdapter(views);
        viewPager.setAdapter(adapter);
        flg = true;
        banner();

        Button button1 = findViewById(R.id.hos_syn_reg);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalSynopsisActivity.this,HospitalRegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    boolean flg;

    public void banner() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 1;
                while (flg) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int finalI = i;
                    i++;
                    if (i > 2) {
                        i = 0;
                    }
                    if (flg){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(finalI);
                            }
                        });
                    }
                }
            }
        }.start();
    }
}