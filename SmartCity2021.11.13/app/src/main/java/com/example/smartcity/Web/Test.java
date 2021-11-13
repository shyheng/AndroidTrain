package com.example.smartcity.Web;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.smartcity.Json.Token;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class Test implements Callback {

    public static String ip = "http://124.93.196.45:";
    public static String port = "10001";
    public static String token_1;

    private static Handler handler  = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            token_1 = (String) msg.obj;
        }
    };


    public static void shy(){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                , "{\n" +
                        "\"username\":\"shyheng\",\n" +
                        "\"password\":\"123456\"\n" +
                        "}");
        Request request = new Request.Builder().post(body).url(ip + port + "/prod-api/api/login").build();
        Call call = client.newCall(request);
        Callback callback = new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                System.out.println(token);
                String token = new Gson().fromJson(response.body().string(), Token.class).getToken();
                Message message = handler.obtainMessage();
                message.obj = token;
                handler.sendMessage(message);

            }
        };

//        悲观锁
//        CAS
        call.enqueue(callback);


    }
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

    }

    public static void token_get(String url, JsonThread jsonThread){
        new OkHttpClient().newCall(new Request.Builder().header("Authorization",token_1).get().url(ip+port+url).build()).enqueue(jsonThread);
    }

    public static void toen_get(String url, JsonThread jsonThread) {
        if (token_1 != null) {
            System.out.println(token_1);
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
                                            token_1)
                                    .url(ip + port + url)
                                    .build()
                    ).enqueue(jsonThread);
                }
            });
        }
    }

}
