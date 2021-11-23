package com.example.testjson;

import android.app.Application;
import android.util.Log;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TestJson.token();
    }
}
