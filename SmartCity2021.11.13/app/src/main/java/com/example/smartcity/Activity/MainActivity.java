package com.example.smartcity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.Web.JsonThread;
import com.example.smartcity.Web.Test;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView img_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Test.shy();
    }

    private void initView() {
        img_main = (ImageView) findViewById(R.id.img_main);
        img_main.setImageResource(R.drawable.gg);


        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(()->{
                    Intent intent = new Intent(MainActivity.this,IndexActivity.class);
                    startActivity(intent);
                });
            }
        }.start();
    }
}