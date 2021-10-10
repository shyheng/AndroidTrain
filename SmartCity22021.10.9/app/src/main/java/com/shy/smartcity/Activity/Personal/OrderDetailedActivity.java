package com.shy.smartcity.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.shy.smartcity.R;

public class OrderDetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed);
        Intent intent = getIntent();

        TextView id = findViewById(R.id.o_id);
        id.setText(intent.getStringExtra("id"));
        TextView type = findViewById(R.id.o_type);
        type.setText(intent.getStringExtra("type"));
        TextView date = findViewById(R.id.o_date);
        date.setText(intent.getStringExtra("date"));
    }
}