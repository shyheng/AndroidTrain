package com.shy.smartcity.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.shy.smartcity.Activity.FeedbackActivity;
import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.Activity.MetroActivity;
import com.shy.smartcity.Activity.NewActivity;
import com.shy.smartcity.Activity.OrderActivity;
import com.shy.smartcity.Activity.PersonalActivity;
import com.shy.smartcity.Activity.UpPasswordActivity;
import com.shy.smartcity.Adapter.BannerAdapter;
import com.shy.smartcity.Adapter.NewAdapter;
import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Adapter.ThemeAdapter;
import com.shy.smartcity.Bean.New;
import com.shy.smartcity.Bean.Service;
import com.shy.smartcity.Bean.Theme;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;


public class IndexFragment extends Fragment {


    @Override
    public void onPause() {
        super.onPause();
        test(false);
    }

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    RecyclerView recyclerView_new;
    ViewPager2 viewPager1;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;

    String url = "https://img2.baidu.com/it/u=3048225651,1553788671&fm=26&fmt=auto&gp=0.jpg";

    public static ViewPager2 viewPager2;


    public void test(boolean flg){
        new Thread(){
            @Override
            public void run() {
                while (flg){
                    for (int i = 0; i < 3; i++) {
                        int finalI = i;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        viewPager1.setCurrentItem(finalI);
                        if (i == 2) {
                            i = -1;
                        }
                    }
                }
            }
        }.start();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        EditText input = view.findViewById(R.id.i_i);


//        轮播图
        viewPager1 = view.findViewById(R.id.banner);
        BannerAdapter bannerAdapter = new BannerAdapter(this.getActivity());
        viewPager1.setAdapter(bannerAdapter);
        test(true);

        List<Service> services = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Service service = new Service();
            service.setText("购物");
            service.setImage(R.drawable.img_home1);
            services.add(service);
        }

        Service service = new Service();
        service.setText("地铁");
        service.setImage(R.drawable.dt);
        services.add(service);

        Service service1 = new Service();
        service1.setText("更多");
        service1.setImage(R.drawable.all);
        services.add(service1);

        recyclerView = view.findViewById(R.id.rv1);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(manager);

        ServerAdapter serverAdapter = new ServerAdapter(services, getContext());

        recyclerView.setAdapter(serverAdapter);
        serverAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                if (position == 9) {
                    viewPager2.setCurrentItem(1);
                }
                if (position == 8) {
                    Intent intent = new Intent(getContext(), MetroActivity.class);
                    startActivity(intent);
                }

            }
        });

        List<Theme> themes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Theme theme = new Theme();
            theme.setImg(url);
            theme.setText("标题:智能驾驶-科技发展");
            themes.add(theme);
        }

        recyclerView1 = view.findViewById(R.id.rv2);

        GridLayoutManager manager1 = new GridLayoutManager(getContext(), 2);
        recyclerView1.setLayoutManager(manager1);

        ThemeAdapter themeAdapter = new ThemeAdapter(themes, getContext());
        recyclerView1.setAdapter(themeAdapter);

        themeAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {

            }
        });


        b1 = view.findViewById(R.id.button);
        b2 = view.findViewById(R.id.button2);
        b3 = view.findViewById(R.id.button3);
        b4 = view.findViewById(R.id.button4);
        b5 = view.findViewById(R.id.button5);
        List<New> news = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            New n = new New();
            n.setImg("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp7.itc.cn%2Fq_70%2Fimages03%2F20200614%2F6e089acb694a4eacbe5324e2a339089a.jpeg&refer=http%3A%2F%2Fp7.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040500&t=6433f9b45e813a38a78503aecce1954e");
            n.setTitle("推荐");
            n.setCon("灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点 \u200B");
            n.setNum("1000");
            n.setDate("2021-02-" + i);
            news.add(n);
        }

        b1.setBackgroundResource(R.color.design_default_color_secondary_variant);

        recyclerView_new = view.findViewById(R.id.rv3);

        GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1);
        recyclerView_new.setLayoutManager(manager2);

        NewAdapter newAdapter = new NewAdapter(news, getContext());
        recyclerView_new.setAdapter(newAdapter);

        newAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<New> news = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    New n = new New();
                    n.setImg("https://img2.baidu.com/it/u=1640428960,388886419&fm=253&fmt=auto&app=120&f=JPEG?w=640&h=231");
                    n.setTitle("要闻");
                    n.setCon("灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点 \u200B");
                    n.setNum("1000");
                    n.setDate("2021-9-" + i);
                    news.add(n);
                }

                b1.setBackgroundResource(R.color.white);
                b3.setBackgroundResource(R.color.white);
                b4.setBackgroundResource(R.color.white);
                b5.setBackgroundResource(R.color.white);
                b2.setBackgroundResource(R.color.design_default_color_secondary_variant);
                recyclerView_new = view.findViewById(R.id.rv3);

                GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1);
                recyclerView_new.setLayoutManager(manager2);

                NewAdapter newAdapter = new NewAdapter(news, getContext());
                recyclerView_new.setAdapter(newAdapter);

                newAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
                    @Override
                    public void OnRecyclerItemClick(int position) {

                    }
                });
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<New> news = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    New n = new New();
                    n.setImg("https://img1.baidu.com/it/u=448126660,3808626321&fm=26&fmt=auto&gp=0.jpg");
                    n.setTitle("新思想");
                    n.setCon("灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点 \u200B");
                    n.setNum("1000");
                    n.setDate("2021-9-" + i);
                    news.add(n);
                }
                b1.setBackgroundResource(R.color.white);
                b2.setBackgroundResource(R.color.white);
                b4.setBackgroundResource(R.color.white);
                b5.setBackgroundResource(R.color.white);
                b3.setBackgroundResource(R.color.design_default_color_secondary_variant);
                recyclerView_new = view.findViewById(R.id.rv3);

                GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1);
                recyclerView_new.setLayoutManager(manager2);

                NewAdapter newAdapter = new NewAdapter(news, getContext());
                recyclerView_new.setAdapter(newAdapter);

                newAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
                    @Override
                    public void OnRecyclerItemClick(int position) {

                    }
                });
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<New> news = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    New n = new New();
                    n.setImg("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.scio.gov.cn%2Fxwfbh%2Fgssxwfbh%2Fxwfbh%2Fyunnan%2Fdocument%2F1683163%2FImage%2F8.png&refer=http%3A%2F%2Fwww.scio.gov.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040616&t=1184617e1d84d9ba218452c69580b90f");
                    n.setTitle("云南");
                    n.setCon("灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点 \u200B");
                    n.setNum("1000");
                    n.setDate("2021-9-" + i);
                    news.add(n);
                }
                b1.setBackgroundResource(R.color.white);
                b3.setBackgroundResource(R.color.white);
                b2.setBackgroundResource(R.color.white);
                b5.setBackgroundResource(R.color.white);
                b4.setBackgroundResource(R.color.design_default_color_secondary_variant);
                recyclerView_new = view.findViewById(R.id.rv3);

                GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1);
                recyclerView_new.setLayoutManager(manager2);

                NewAdapter newAdapter = new NewAdapter(news, getContext());
                recyclerView_new.setAdapter(newAdapter);

                newAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
                    @Override
                    public void OnRecyclerItemClick(int position) {

                    }
                });
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<New> news = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    New n = new New();
                    n.setImg("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgss0.baidu.com%2F9vo3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Fd833c895d143ad4b38c58f2385025aafa50f066e.jpg&refer=http%3A%2F%2Fgss0.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040642&t=3e8c466a6377b9e5d904cfd1bb5f79ec");
                    n.setTitle("综合");
                    n.setCon("灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点 \u200B");
                    n.setNum("1000");
                    n.setDate("2021-9-" + i);
                    news.add(n);
                }
                b1.setBackgroundResource(R.color.white);
                b3.setBackgroundResource(R.color.white);
                b4.setBackgroundResource(R.color.white);
                b2.setBackgroundResource(R.color.white);
                b5.setBackgroundResource(R.color.design_default_color_secondary_variant);
                recyclerView_new = view.findViewById(R.id.rv3);

                GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1);
                recyclerView_new.setLayoutManager(manager2);

                NewAdapter newAdapter = new NewAdapter(news, getContext());
                recyclerView_new.setAdapter(newAdapter);

                newAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
                    @Override
                    public void OnRecyclerItemClick(int position) {

                    }
                });
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<New> news = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    New n = new New();
                    n.setImg("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp7.itc.cn%2Fq_70%2Fimages03%2F20200614%2F6e089acb694a4eacbe5324e2a339089a.jpeg&refer=http%3A%2F%2Fp7.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040500&t=6433f9b45e813a38a78503aecce1954e");
                    n.setTitle("推荐");
                    n.setCon("灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点 \u200B");
                    n.setNum("1000");
                    n.setDate("2021-02-" + i);
                    news.add(n);
                }
                b2.setBackgroundResource(R.color.white);
                b3.setBackgroundResource(R.color.white);
                b4.setBackgroundResource(R.color.white);
                b5.setBackgroundResource(R.color.white);
                b1.setBackgroundResource(R.color.design_default_color_secondary_variant);
                recyclerView_new = view.findViewById(R.id.rv3);

                GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1);
                recyclerView_new.setLayoutManager(manager2);

                NewAdapter newAdapter = new NewAdapter(news, getContext());
                recyclerView_new.setAdapter(newAdapter);

                newAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
                    @Override
                    public void OnRecyclerItemClick(int position) {

                    }
                });
            }
        });

        if (PersonalActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            PersonalActivity.anInt = 0;
        }

        if (UpPasswordActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            UpPasswordActivity.anInt = 0;
        }

        if (OrderActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            OrderActivity.anInt = 0;
        }

        if (FeedbackActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            FeedbackActivity.anInt = 0;
        }

        if (NewActivity.anInt == 1) {
            viewPager2.setCurrentItem(3);
            NewActivity.anInt = 0;
        }
        return view;
    }

}