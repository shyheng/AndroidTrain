package com.shy.smartcity.Activity.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

public class CommunityVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_vehicle);

        Button button = findViewById(R.id.com_veh_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityVehicleActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.com_veh_tj);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityVehicleActivity.this,IndexActivity.class);
                Toast.makeText(CommunityVehicleActivity.this,"提交成功！",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }
}