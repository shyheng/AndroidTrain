package com.example.smartcity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.Json.Record;
import com.example.smartcity.R;
import com.example.smartcity.Web.JsonThread;
import com.example.smartcity.Web.Test;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class LivingActivity extends AppCompatActivity implements View.OnClickListener {

    public static String Iphone;

    private Button fh;
    private RadioButton rg_1;
    private RadioButton rg_2;
    private RadioButton rg_3;
    private RadioGroup rg;
    private EditText phone;
    private Button select_phone;
    private ListView lv_phone;
    private Button but_chek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living);
        initView();
        Test.token_get("/prod-api/api/living/phone/record/list", new JsonThread() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String s = response.body().string();
                Record record = new Gson().fromJson(s, Record.class);
                List<String> list = new ArrayList<>();
                for (int i = 0; i < record.getRows().size(); i++) {
                    list.add(record.getRows().get(i).getPaymentType() + " " + record.getRows().get(i).getRechargeTime());
                }
                if (list.isEmpty()) {
                    list.add("暂未记录");
                }
                runOnUiThread(() -> {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(LivingActivity.this,
                            android.R.layout.simple_expandable_list_item_1, list);
                    lv_phone.setAdapter(adapter);
                });
            }
        });


    }

    private void initView() {
        fh = (Button) findViewById(R.id.fh);
        rg_1 = (RadioButton) findViewById(R.id.rg_1);
        rg_2 = (RadioButton) findViewById(R.id.rg_2);
        rg_3 = (RadioButton) findViewById(R.id.rg_3);
        rg = (RadioGroup) findViewById(R.id.rg);
        phone = (EditText) findViewById(R.id.phone);
        select_phone = (Button) findViewById(R.id.select_phone);
        lv_phone = (ListView) findViewById(R.id.lv_phone);
        fh.setOnClickListener(this);
        select_phone.setOnClickListener(this);
        but_chek = (Button) findViewById(R.id.but_chek);
        but_chek.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fh:
                finish();
                break;
            case R.id.select_phone:
                submit();
                break;
            case R.id.but_chek:
                if (!submit()){
                    Iphone = phone.getText().toString().trim();
                    Intent intent = new Intent(LivingActivity.this,RechargeActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean submit() {

        String phoneString = phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return TextUtils.isEmpty(phoneString);
        }
        return TextUtils.isEmpty(phoneString);
    }
}