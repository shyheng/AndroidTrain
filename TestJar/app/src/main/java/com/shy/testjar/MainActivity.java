package com.shy.testjar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button but;
    public static String ip = "http://124.93.196.45:10001";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        TestService.api = "/prod-api/api/metro/rotation/list";
        Intent intent = new Intent(MainActivity.this,TestService.class);
        startService(intent);
        
        EventBus.getDefault().register(MainActivity.this);
        Progress.show(this);
    }

    private void initView() {
        tv = findViewById(R.id.tv);
        but = findViewById(R.id.but);
        but.setOnClickListener((view)->{
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void text(SMsg msg){
        Shy shy = new Gson().fromJson(msg.s,Shy.class);
        tv.setText(String.valueOf(shy.getRows().get(0).getId()));
        Progress.diss();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(MainActivity.this);
    }
}