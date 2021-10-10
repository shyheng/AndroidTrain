package com.shy.smartcity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.webkit.WebView;

import com.shy.smartcity.Adapter.metro.MetroOverviewAdapter;
import com.shy.smartcity.Bean.Metro;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class Metro_OverviewActivity extends AppCompatActivity {

    WebView webView;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_overview);

        List<Metro> metros = new ArrayList<>();
        metros.add(new Metro(R.color.yh,"地铁一号线"));
        metros.add(new Metro(R.color.eh,"地铁二号线"));
        metros.add(new Metro(R.color.sh,"地铁三号线"));
        metros.add(new Metro(R.color.xh,"地铁四号线"));
        metros.add(new Metro(R.color.lh,"地铁六号线"));


        recyclerView = findViewById(R.id.rv_met_ove);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        recyclerView.setAdapter(new MetroOverviewAdapter(metros,this));

        webView = findViewById(R.id.wv);
////        本地
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("file:///android_asset/test.html");
    }
}