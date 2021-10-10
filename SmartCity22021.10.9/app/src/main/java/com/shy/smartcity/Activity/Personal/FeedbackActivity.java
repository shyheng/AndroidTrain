package com.shy.smartcity.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

public class FeedbackActivity extends AppCompatActivity {
    public static int anInt= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Button button = findViewById(R.id.f_e);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(FeedbackActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.tj);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(FeedbackActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });
    }
}