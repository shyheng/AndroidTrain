package com.shy.smartcity.Activity.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

public class CommunityBusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_business);

        Button button = findViewById(R.id.com_bus_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityBusinessActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });
    }
}