package com.shy.smartcity.Activity.Community;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.Adapter.ExpressAdapter;
import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Bean.Express;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityExpressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_express);

        Button button = findViewById(R.id.com_exp_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityExpressActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.com_exp_saoma);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//用来打开相机的Intent
                startActivityForResult(takePhotoIntent,REQ_CODE);//启动相机
            }
        });

        List<Express> expresses = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            expresses.add(new Express("https://img.alicdn.com/imgextra/i1/842994830/O1CN01AAHoVA1lYBZbIjeku_!!0-item_pic.jpg_430x430q90.jpg",
                    "上衣","侃侃衣诚"));
        }

        RecyclerView recyclerView = findViewById(R.id.com_exp_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        ExpressAdapter expressAdapter = new ExpressAdapter(expresses, getApplicationContext());
        recyclerView.setAdapter(expressAdapter);
        expressAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {

            }
        });

    }

    private static int REQ_CODE = 1;
}