package com.example.testjson;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class TestJson implements Callback {

    public static String token;
    public final static String Shy = "shy";


    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.e("shy", String.valueOf(e));
    }

    public static void get(String url, TestJson json) {
        if (token != null) {
            new OkHttpClient()
                    .newCall(new Request.Builder()
                            .get()
                            .header("Authorization",token)
                            .url(url)
                            .build()
                    ).enqueue(json);
        }
    }

    public static void token() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", "shyheng");
        jsonObject.addProperty("password", "123456");
        TestJson.post("http://124.93.196.45:10001/prod-api/api/login", jsonObject, new TestJson() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String s = response.body().string();
                Token token = new Gson().fromJson(s,Token.class);
                this.token = token.getToken();
            }
        });
    }

    public static void post(String url, JsonObject object, TestJson json) {
        if (token==null){
            new OkHttpClient().newCall(new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"), object.toString())).build()).enqueue(json);
        }else {
            new OkHttpClient().newCall(new Request.Builder().url(url).header("Authorization",token).post(RequestBody.create(MediaType.parse("application/json"), object.toString())).build()).enqueue(json);
        }
    }

}
