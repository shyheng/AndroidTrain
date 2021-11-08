package com.shy.testserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.but);
        TextView textView = findViewById(R.id.tv);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_SEND_MSG);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                textView.setText(intent.getStringExtra("shy"));
            }
        };
        registerReceiver(receiver, intentFilter);

        Intent intent = new Intent(MainActivity.this, MyIntentService.class);
        startService(intent);

    }

}