package com.shy.smartcity.Activity.Hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.Adapter.HospitalAdapter;
import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Bean.Hospital;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_index);

        Button button = findViewById(R.id.hos_ind_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalIndexActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.hos_ind_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        List<Hospital> hospitals = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            hospitals.add(new Hospital("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20180625%2F59745f5dcbcf4312aab05fb0fb581925.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636203685&t=c9fa393e18a12f6c752e4ac6f22d84d3",
                    "北京协和医院","五星级"));

            hospitals.add(new Hospital("https://img1.baidu.com/it/u=1775137543,1730147470&fm=26&fmt=auto",
                    "北京同仁医院","五星级"));
        }

        HospitalAdapter hospitalAdapter = new HospitalAdapter(hospitals,this);
        recyclerView.setAdapter(hospitalAdapter);

        hospitalAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                Intent intent = new Intent(HospitalIndexActivity.this,HospitalSynopsisActivity.class);
                startActivity(intent);
            }
        });

    }
}