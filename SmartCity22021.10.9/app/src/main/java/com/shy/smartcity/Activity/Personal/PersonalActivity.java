package com.shy.smartcity.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

public class PersonalActivity extends AppCompatActivity {

    public static int anInt= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        Button button = findViewById(R.id.p_p);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(PersonalActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        WebView webView = findViewById(R.id.input);

        webView.loadUrl("file:///android_asset/input.html");


        Button button1 = findViewById(R.id.xg);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(PersonalActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });
    }
}