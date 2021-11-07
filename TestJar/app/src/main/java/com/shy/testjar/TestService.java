package com.shy.testjar;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TestService extends IntentService {

    public static String api;

    public TestService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        OkHttpClient client = new OkHttpClient();
        Request request = new  Request.Builder().get().url(MainActivity.ip+api).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("1网络发生错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                EventBus.getDefault().postSticky(new SMsg(json));
            }
        });


    }
}