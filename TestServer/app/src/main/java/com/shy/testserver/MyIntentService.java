package com.shy.testserver;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;

import androidx.annotation.Nullable;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super("shy");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        OkHttpClient client = new OkHttpClient();
        Request request = (new Request.Builder()).get().url("http://124.93.196.45:10001/prod-api/press/press/list").build();
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        String resBody = null;
        try {
            resBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map result = gson.fromJson(resBody, (new TypeToken<Map>() {
        }).getType());
        List<Map<String, Object>> maps = (List<Map<String, Object>>) result.get("rows");


        Intent intent1 = new Intent();
        intent1.setAction(Constants.ACTION_SEND_MSG);
        intent1.putExtra("shy",resBody);
        sendBroadcast(intent1);
//        System.out.println(resBody);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        System.out.println("停止");
    }
}