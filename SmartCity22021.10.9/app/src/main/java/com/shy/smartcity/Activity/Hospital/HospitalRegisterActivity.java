package com.shy.smartcity.Activity.Hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.R;

public class HospitalRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);
        Button button = findViewById(R.id.hos_reg_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalRegisterActivity.this,HospitalSynopsisActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.hop_gh);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalRegisterActivity.this,HospitalInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}