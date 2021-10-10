package com.shy.smartcity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.shy.smartcity.R;

public class JumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        SharedPreferences sp=getSharedPreferences("name",MODE_PRIVATE);
        boolean is=sp.getBoolean("ok",true);
        if(is){
            SharedPreferences.Editor editor=sp.edit ();
            editor.putBoolean("ok",false);
            editor.apply();;
            startActivity(new Intent(JumpActivity.this,MainActivity.class));
        }else
            startActivity(new Intent (JumpActivity.this,IndexActivity.class));
    }
}