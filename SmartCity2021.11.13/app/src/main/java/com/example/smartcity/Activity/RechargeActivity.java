package com.example.smartcity.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.Json.Recharge;
import com.example.smartcity.R;
import com.example.smartcity.Web.JsonThread;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button fh_rech;
    private EditText et_phone;
    private Button but_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initView();

    }

    private void initView() {
        fh_rech = (Button) findViewById(R.id.fh_rech);
        et_phone = (EditText) findViewById(R.id.et_phone);
        but_phone = (Button) findViewById(R.id.but_phone);

        fh_rech.setOnClickListener(this);
        but_phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fh_rech:
                finish();
                break;
            case R.id.but_phone:
                if (!submit()) {
                    recharge(Integer.parseInt(et_phone.getText().toString().trim()));
                }
                break;
        }
    }

    private boolean submit() {
        // validate
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入支付金额", Toast.LENGTH_SHORT).show();
            return TextUtils.isEmpty(phone);
        }
        return TextUtils.isEmpty(phone);
    }

    private void recharge(int m){
        Map map = new HashMap();
        map.put("paymentType","电子支付");
        map.put("phonenumber","15898125172");
        map.put("rechargeAmount",m);
        map.put("type","1");
        JsonThread.token_post("/prod-api/api/living/phone/recharge", new JsonThread() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Recharge recharge = new Gson().fromJson(response.body().string(),Recharge.class);
                System.out.println();
            }
        },map);
    }
}