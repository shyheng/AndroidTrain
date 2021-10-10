package com.shy.smartcity.Activity.Violation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

public class ViolationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation);

        Button button1 = findViewById(R.id.vio);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViolationActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.vio_select);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViolationActivity.this,ViolationSelectActivity.class);
                startActivity(intent);
            }
        });
    }
}