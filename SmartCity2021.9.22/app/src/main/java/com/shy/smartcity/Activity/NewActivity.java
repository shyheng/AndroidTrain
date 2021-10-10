package com.shy.smartcity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.shy.smartcity.R;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    public static int anInt = 0;

    ImageView image;
    TextView textView;
    TextView con;
    ListView listView;

    ImageView i1;
    ImageView i2;
    ImageView i3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button b_e = findViewById(R.id.n_e);
        b_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(NewActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        image = findViewById(R.id.new_img);
        request(intent.getStringExtra("url"));

        textView = findViewById(R.id.text_new);
        textView.setText(intent.getStringExtra("tel"));

        con = findViewById(R.id.con_new);
        con.setText(intent.getStringExtra("con"));

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add("热线网友\n这个好，这也太棒了");
        }

        listView = findViewById(R.id.lv_new);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);


        i1 = findViewById(R.id.im1);
        i2 = findViewById(R.id.im2);
        i3 = findViewById(R.id.im3);

        imgRequest(i1,
                "https://search-operate.cdn.bcebos.com/2278a38e654d7160ef5a7b084217a660.jpg");

        imgRequest(i2,
                "https://t11.baidu.com/it/u=2925288351,2304405600&fm=55&app=54&fmt=auto?w=1140&h=640");
        imgRequest(i3,
                "https://t11.baidu.com/it/u=2487170001,3075912551&fm=55&app=54&fmt=auto?w=1140&h=640");
    }

    public void request(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
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

    public void imgRequest(ImageView image, String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
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