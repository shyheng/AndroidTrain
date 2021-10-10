package com.shy.smartcity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import com.shy.smartcity.Activity.Hospital.HospitalIndexActivity;
import com.shy.smartcity.Activity.Move.MoveActivity;
import com.shy.smartcity.Activity.Personal.FeedbackActivity;
import com.shy.smartcity.Activity.Personal.OrderActivity;
import com.shy.smartcity.Activity.Personal.PersonalActivity;
import com.shy.smartcity.Activity.Personal.UpPasswordActivity;
import com.shy.smartcity.Activity.Violation.ViolationActivity;
import com.shy.smartcity.Adapter.GideAdapter;
import com.shy.smartcity.Adapter.NewAdapter;
import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Adapter.ThemeAdapter;
import com.shy.smartcity.Bean.New;
import com.shy.smartcity.R;
import com.shy.smartcity.Bean.Service;
import com.shy.smartcity.Bean.Theme;
import com.shy.smartcity.web.RequestImage;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    RecyclerView recyclerView_new;
    ViewPager viewPager1;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    String url = "https://img2.baidu.com/it/u=3048225651,1553788671&fm=26&fmt=auto&gp=0.jpg";

    public static ViewPager2 viewPager2;

    @Override
    public void onResume() {
        super.onResume();
        flg = true;
        banner();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        EditText input = view.findViewById(R.id.i_i);
        viewPager1 = view.findViewById(R.id.banner);

        LayoutInflater inflaterBanner = getLayoutInflater().from(getContext());

        View view1 = inflaterBanner.inflate(R.layout.banner1, null);
        ImageView imageView1 = view1.findViewById(R.id.i1);
        RequestImage.request(imageView1, "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.tranbbs.com%2Fuploadfile%2F2015%2F0916%2F1442387838182669.png&refer=http%3A%2F%2Fwww.tranbbs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633867243&t=469a7103ca1bbe8e28c98fa09ef1e617", getContext());

        View view2 = inflaterBanner.inflate(R.layout.banner2, null);
        ImageView imageView2 = view2.findViewById(R.id.i2);
        RequestImage.request(imageView2, "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi1%2F220110546%2FO1CN01xh6zZD1Fu6jpg6w0q_%21%21220110546.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634036989&t=a65c5123cb8287ce23f7bbaeebf5f933", getContext());

        View view3 = inflaterBanner.inflate(R.layout.banner3, null);
        ImageView imageView3 = view3.findViewById(R.id.i3);
        RequestImage.request(imageView3, "https://img2.baidu.com/it/u=3120502424,650868114&fm=26&fmt=auto&gp=0.jpg", getContext());

        List<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

        GideAdapter adapter = new GideAdapter(views);
        viewPager1.setAdapter(adapter);
        flg = true;
        banner();

        List<Service> services = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Service service = new Service();
            service.setText("购物");
            service.setImage(R.drawable.img);
            services.add(service);
        }

        Service service4 = new Service();
        service4.setText("门诊");
        service4.setImage(R.drawable.hospital);
        services.add(service4);

        Service service3 = new Service();
        service3.setText("违章");
        service3.setImage(R.drawable.violation);
        services.add(service3);

        Service service2 = new Service();
        service2.setText("活动");
        service2.setImage(R.drawable.act);
        services.add(service2);

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
                if (position == 7){
                    Intent intent = new Intent(getContext(), MoveActivity.class);
                    startActivity(intent);
                }
                if (position == 6){
                    Intent intent = new Intent(getContext(), ViolationActivity.class);
                    startActivity(intent);
                }
                if (position == 5){
                    Intent intent = new Intent(getContext(), HospitalIndexActivity.class);
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

        news(view);


        if (PersonalActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            PersonalActivity.anInt = 0;
            flg = false;
        }

        if (UpPasswordActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            UpPasswordActivity.anInt = 0;
            flg = false;
        }

        if (OrderActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            OrderActivity.anInt = 0;
            flg = false;
        }

        if (FeedbackActivity.anInt == 1) {
            viewPager2.setCurrentItem(4);
            FeedbackActivity.anInt = 0;
            flg = false;
        }

        return view;
    }

    public void news(View view) {
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
    }

    static boolean flg;

    public void banner() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 1;
                while (flg) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int finalI = i;
                    i++;
                    if (i > 2) {
                        i = 0;
                    }
                    if (flg){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewPager1.setCurrentItem(finalI);
                            }
                        });
                    }
                }
            }
        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        flg = false;

    }

}