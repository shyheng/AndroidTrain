package com.shy.smartcity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.shy.smartcity.Adapter.GideAdapter;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        LayoutInflater inflater = getLayoutInflater().from(this);
        View view1 = inflater.inflate(R.layout.guide_1,null);
        ImageView imageView1 = view1.findViewById(R.id.img1);
        request(imageView1,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.tranbbs.com%2Fuploadfile%2F2015%2F0916%2F1442387838182669.png&refer=http%3A%2F%2Fwww.tranbbs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633867243&t=469a7103ca1bbe8e28c98fa09ef1e617");

        View view2 = inflater.inflate(R.layout.guide_2,null);
        ImageView imageView2 = view2.findViewById(R.id.img2);
        request(imageView2,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi1%2F220110546%2FO1CN01xh6zZD1Fu6jpg6w0q_%21%21220110546.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634036989&t=a65c5123cb8287ce23f7bbaeebf5f933");

        View view3 = inflater.inflate(R.layout.guide_3,null);
        ImageView imageView3 = view3.findViewById(R.id.img3);
        request(imageView3,"https://img2.baidu.com/it/u=3120502424,650868114&fm=26&fmt=auto&gp=0.jpg");



        List<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

        ViewPager viewPager = findViewById(R.id.vp);

        GideAdapter adapter = new GideAdapter(views);
        viewPager.setAdapter(adapter);

        Button g_b = view3.findViewById(R.id.g_b);
        g_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });
    }

    public void request(ImageView image,String url) {
        RequestQueue queue = Volley.newRequestQueue(this);

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                image.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        queue.add(imageRequest);
    }
}