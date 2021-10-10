package com.shy.smartcity.Activity.Violation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.R;

public class ViolationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation_details);

        Button button = findViewById(R.id.vio_det);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViolationDetailsActivity.this,ViolationSelectActivity.class);
                startActivity(intent);
            }
        });
    }
}