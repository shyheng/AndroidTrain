package com.shy.smartcity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.R;

import kotlin.jvm.internal.MutablePropertyReference0;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp=getSharedPreferences("name",MODE_PRIVATE);
        boolean is=sp.getBoolean("ok",true);
        if(is){
            SharedPreferences.Editor editor=sp.edit ();
            editor.putBoolean("ok",false);
            editor.apply();
            startActivity(new Intent(MainActivity.this,GuideActivity.class));
        }else
            startActivity(new Intent (MainActivity.this,IndexActivity.class));
    }
}