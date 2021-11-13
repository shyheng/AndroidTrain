package com.example.smartcity.Web;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.smartcity.Json.Token;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public abstract class JsonThread implements Callback {

    public static String ip = "http://124.93.196.45:";
    public static String port = "10001";

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.e("shy", "网络出错");
    }

    public static String token;


    public static void get(String url, JsonThread jsonThread) {
        new OkHttpClient().newCall(
                new Request.Builder()
                        .get()
                        .url(ip + port + url)
                        .build()
        ).enqueue(jsonThread);
    }


    public static void token_post(String url, JsonThread jsonThread, Map<String, String> map) {
        if (token != null) {
            new OkHttpClient().newCall(
                    new Request.Builder().post(
                            RequestBody.create(MediaType.parse("application/json;charset=utf-8"), "{\n" +
                                    "\"username\":\"shyheng\",\n" +
                                    "\"password\":\"123456\"\n" +
                                    "}")).url(ip + port + "/prod-api/api/login").build()).enqueue(new JsonThread() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    FormBody.Builder builder = new FormBody.Builder();
                    for (String key : map.keySet()) {
                        builder.add(key, map.get(key));
                    }
                    new OkHttpClient().newCall(
                            new Request.Builder()
                                    .post(builder.build())
                                    .url(url)
                                    .header("Authorization",
                                            new Gson().fromJson(response.body().string(), Token.class).getToken())
                                    .build()
                    ).enqueue(jsonThread);
                }
            });
        }

    }

    public static void token_get(String url, JsonThread jsonThread) {
        if (token != null) {
            new OkHttpClient().newCall(
                    new Request.Builder().post(
                            RequestBody.create(
                                    MediaType.parse("application/json;charset=utf-8"),
                                    "{\n" +
                                            "\"username\":\"shyheng\",\n" +
                                            "\"password\":\"123456\"\n" +
                                            "}")).url(ip + port + "/prod-api/api/login").build()).enqueue(new JsonThread() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    new OkHttpClient().newCall(
                            new Request.Builder()
                                    .get()
                                    .header("Authorization",
                                            new Gson().fromJson(response.body().string(), Token.class).getToken())
                                    .url(ip + port + url)
                                    .build()
                    ).enqueue(jsonThread);
                }
            });
        }
    }
}
