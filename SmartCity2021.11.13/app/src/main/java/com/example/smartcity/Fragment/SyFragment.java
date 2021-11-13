package com.example.smartcity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smartcity.Activity.LivingActivity;
import com.example.smartcity.Adapter.Adapter;
import com.example.smartcity.Json.Category;
import com.example.smartcity.Web.JsonThread;
import com.example.smartcity.Json.Press;
import com.example.smartcity.Json.TodayWeather;
import com.example.smartcity.R;
import com.example.smartcity.Web.RequestImage;
import com.example.smartcity.Web.Test;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


public class SyFragment extends Fragment {

    private ViewFlipper vf;
    private GridView jf_gr;
    private TextView tv_w;
    private GridView gr_cat;
    private Button g_but;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sy, container, false);
        vf = view.findViewById(R.id.vf);
        jf_gr = view.findViewById(R.id.jf_gr);
        tv_w = view.findViewById(R.id.tv_w);
        gr_cat = view.findViewById(R.id.gr_cat);
        g_but = view.findViewById(R.id.g_but);
        g_but.setOnClickListener((view1)->{
//            Intent intent = new Intent(getContext(),);
//            startActivity(intent);
        });
        banner();
        service();
//        Map<String,String> map = new HashMap<>();
//        map.put("money","50");
//        JsonThread.post("/prod-api/api/common/balance/recharge", new JsonThread() {
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                System.out.println(response.body().string());
//            }
//        },map);
        weather();
        news();
        return view;
    }



    private void news() {
        JsonThread.get("/prod-api/api/living/press/press/list", new JsonThread() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Press press = new Gson().fromJson(json, Press.class);
                getActivity().runOnUiThread(() -> {
                    gr_cat.setAdapter(new Adapter(3) {
                        class ViewHolder {
                            public View rootView;
                            public ImageView img_cat;
                            public TextView tv_cat_title;
                            public TextView tv_cat_date;

                            public ViewHolder(View rootView) {
                                this.rootView = rootView;
                                this.img_cat = (ImageView) rootView.findViewById(R.id.img_cat);
                                this.tv_cat_title = (TextView) rootView.findViewById(R.id.tv_cat_title);
                                this.tv_cat_date = (TextView) rootView.findViewById(R.id.tv_cat_date);
                            }

                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            ViewHolder holder = new ViewHolder(View.inflate(getContext(), R.layout.item_cat, null));
                            holder.tv_cat_title.setText(press.getRows().get(position).getTitle());
                            holder.tv_cat_date.setText(press.getRows().get(position).getPublishDate());
                            RequestImage.request(holder.img_cat, press.getRows().get(position).getCover(), getContext());
                            return holder.rootView;
                        }
                    });
                });
            }
        });
    }

    private void weather() {
        JsonThread.get("/prod-api/api/common/weather/today", new JsonThread() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                getActivity().runOnUiThread(() -> {
                    TodayWeather weather = new Gson().fromJson(json, TodayWeather.class);
                    TodayWeather.DataBean data = weather.getData();

                    tv_w.setText("当前温度：" + data.getTemperature() + "  当前湿度：" + data.getHumidity() + "   当前空气质量：" + data.getAir());
                    tv_w.setSelected(true);
                });

            }
        });
    }

    private void service() {
        JsonThread.get("/prod-api/api/living/category/list", new JsonThread() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                getActivity().runOnUiThread(() -> {
                    Category jf = new Gson().fromJson(json, Category.class);
                    jf_gr.setAdapter(new Adapter(jf.getData()) {
                        class ViewHolder {
                            public View rootView;
                            public ImageView jf_img;
                            public TextView jf_tv;

                            public ViewHolder(View rootView) {
                                this.rootView = rootView;
                                this.jf_img = (ImageView) rootView.findViewById(R.id.jf_img);
                                this.jf_tv = (TextView) rootView.findViewById(R.id.jf_tv);
                            }

                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            ViewHolder holder = new ViewHolder(View.inflate(getContext(), R.layout.itme_jf, null));
                            RequestImage.request(holder.jf_img, jf.getData().get(position).getImgUrl(), getContext());
                            holder.jf_tv.setText(jf.getData().get(position).getLiveName());
                            return holder.rootView;
                        }
                    });
                });
                jf_gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0:
                                Intent intent = new Intent(getContext(), LivingActivity.class);
                                startActivity(intent);
                                break;
                        }
                    }
                });
            }
        });
    }

    private void banner() {

        LayoutInflater layoutInflater = getLayoutInflater().from(getContext());

        View view1 = layoutInflater.inflate(R.layout.bann1, null);
        ImageView img1 = view1.findViewById(R.id.img1);
        RequestImage.request(img1, "/prod-api/profile/upload/image/2021/05/12/4aa010a5-2787-4aeb-aecb-6032d0d327cb.jpg", getContext());

        View view2 = layoutInflater.inflate(R.layout.bann2, null);
        ImageView img2 = view2.findViewById(R.id.img2);
        RequestImage.request(img2, "/prod-api/profile/upload/image/2021/05/12/4a980d40-a1e7-4eab-84f8-c4a3eb873bee.jpg", getContext());

        View view3 = layoutInflater.inflate(R.layout.bann3, null);
        ImageView img3 = view3.findViewById(R.id.img3);
        RequestImage.request(img3, "/prod-api/profile/upload/image/2021/05/12/c81fdbd5-3257-4f98-83eb-9c4bdfbd044a.jpg", getContext());

        vf.addView(view1);
        vf.addView(view2);
        vf.addView(view3);
        vf.setFlipInterval(3000);
        vf.startFlipping();

    }
}