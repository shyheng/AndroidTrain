package com.wj.dome;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.wj.dome.ui.main.MainActivity;

public class MyApp extends Application {
    /*
    * 初始化程序
    * */
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences("shy", Context.MODE_PRIVATE);
        if (preferences.getBoolean("shy",false)){
            Intent intent = new Intent(MyApp.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        System.out.println("onCreate");
    }

}
