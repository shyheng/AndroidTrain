package com.shy.smartcity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.R;

public class UpPasswordActivity extends AppCompatActivity {

    public static int anInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_password);

        Button button = findViewById(R.id.u_p);
        Button button1 = findViewById(R.id.qd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(UpPasswordActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(UpPasswordActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });
    }
}