package com.example.testjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestJson.get("http://124.93.196.45:10001/prod-api/api/metro/notice/"+2, new TestJson()
        {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e("Shy",response.body().string());
            }
        });
    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.fra,fragment)
        .commit();
    }
    public void delFragment(){
        getSupportFragmentManager().beginTransaction()
        .remove(getSupportFragmentManager().findFragmentById(R.id.fra))
        .commit();
    }


    public void rb(View view) {
        showFragment(new BlankFragment());
    }
    public void del(View view) {
        delFragment();
    }



}