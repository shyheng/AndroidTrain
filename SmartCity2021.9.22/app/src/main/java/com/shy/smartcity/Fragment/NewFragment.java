package com.shy.smartcity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.Activity.NewActivity;
import com.shy.smartcity.Adapter.BannerAdapter;
import com.shy.smartcity.Adapter.NewAdapter;
import com.shy.smartcity.Adapter.NewsAdapter;
import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Bean.New;
import com.shy.smartcity.Bean.News;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class NewFragment extends Fragment {


    @Override
    public void onPause() {
        super.onPause();
        test(false);
    }

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
                    viewPager2.setCurrentItem(finalI);
                    if (i == 2) {
                        i = -1;
                    }
                }
                }
            }
        }.start();
    }

    ViewPager2 viewPager2;

    RecyclerView recyclerView;


    Button bu1;
    Button bu2;
    Button bu3;
    Button bu4;
    Button bu5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        bu1 = view.findViewById(R.id.bu1);
        bu2 = view.findViewById(R.id.bu2);
        bu3 = view.findViewById(R.id.bu3);
        bu4 = view.findViewById(R.id.bu4);
        bu5 = view.findViewById(R.id.bu5);

        viewPager2 = view.findViewById(R.id.vp_news);

        BannerAdapter bannerAdapter = new BannerAdapter(getActivity());
        viewPager2.setAdapter(bannerAdapter);
        test(true);

        bu2.setBackgroundResource(R.color.white);
        bu1.setBackgroundResource(R.color.teal_200);
        bu3.setBackgroundResource(R.color.white);
        bu4.setBackgroundResource(R.color.white);
        bu5.setBackgroundResource(R.color.white);

        List<News> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new News(
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp7.itc.cn%2Fq_70%2Fimages03%2F20200614%2F6e089acb694a4eacbe5324e2a339089a.jpeg&refer=http%3A%2F%2Fp7.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040500&t=6433f9b45e813a38a78503aecce1954e",
                    "推荐"+i,"100"+i,"1"+i,
                    "灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点"
            ));
        }
        rec(view,list);

        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bu2.setBackgroundResource(R.color.white);
                bu1.setBackgroundResource(R.color.teal_200);
                bu3.setBackgroundResource(R.color.white);
                bu4.setBackgroundResource(R.color.white);
                bu5.setBackgroundResource(R.color.white);
                List<News> list = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    list.add(new News(
                            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp7.itc.cn%2Fq_70%2Fimages03%2F20200614%2F6e089acb694a4eacbe5324e2a339089a.jpeg&refer=http%3A%2F%2Fp7.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040500&t=6433f9b45e813a38a78503aecce1954e",
                            "推荐"+i,"100"+i,"1"+i,
                            "灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点"
                    ));
                }
                rec(view,list);
            }
        });

        bu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bu1.setBackgroundResource(R.color.white);
                bu2.setBackgroundResource(R.color.teal_200);
                bu3.setBackgroundResource(R.color.white);
                bu4.setBackgroundResource(R.color.white);
                bu5.setBackgroundResource(R.color.white);
                List<News> list = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    list.add(new News(
                            "https://img2.baidu.com/it/u=1640428960,388886419&fm=253&fmt=auto&app=120&f=JPEG?w=640&h=231",
                            "要闻"+i,"100"+i,"1"+i,
                            "灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点"
                    ));
                }
                rec(view,list);
            }
        });

        bu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bu1.setBackgroundResource(R.color.white);
                bu3.setBackgroundResource(R.color.teal_200);
                bu2.setBackgroundResource(R.color.white);
                bu4.setBackgroundResource(R.color.white);
                bu5.setBackgroundResource(R.color.white);
                List<News> list = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    list.add(new News(
                            "https://img1.baidu.com/it/u=448126660,3808626321&fm=26&fmt=auto&gp=0.jpg",
                            "新思想"+i,"100"+i,"1"+i,
                            "灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点"
                    ));
                }
                rec(view,list);
            }
        });

        bu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bu1.setBackgroundResource(R.color.white);
                bu4.setBackgroundResource(R.color.teal_200);
                bu3.setBackgroundResource(R.color.white);
                bu2.setBackgroundResource(R.color.white);
                bu5.setBackgroundResource(R.color.white);
                List<News> list = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    list.add(new News(
                            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.scio.gov.cn%2Fxwfbh%2Fgssxwfbh%2Fxwfbh%2Fyunnan%2Fdocument%2F1683163%2FImage%2F8.png&refer=http%3A%2F%2Fwww.scio.gov.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040616&t=1184617e1d84d9ba218452c69580b90f",
                            "云南"+i,"100"+i,"1"+i,
                            "灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点"
                    ));
                }
                rec(view,list);
            }
        });

        bu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bu1.setBackgroundResource(R.color.white);
                bu5.setBackgroundResource(R.color.teal_200);
                bu3.setBackgroundResource(R.color.white);
                bu4.setBackgroundResource(R.color.white);
                bu2.setBackgroundResource(R.color.white);
                List<News> list = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    list.add(new News(
                            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgss0.baidu.com%2F9vo3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Fd833c895d143ad4b38c58f2385025aafa50f066e.jpg&refer=http%3A%2F%2Fgss0.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634040642&t=3e8c466a6377b9e5d904cfd1bb5f79ec",
                            "综合"+i,"100"+i,"1"+i,
                            "灿都”进入东海后，将给本市带来明显风雨影响。上海中心气象台已于12日10时发布台风蓝色预警信号。明天早晨起受台风本体影响，风雨均趋于增大。预计14~15日“灿都”在杭州湾到上海附近回旋少动，风雨持续时间长，致灾风险高！大家务必做好台风防御准备！一起看短片牢记要点"
                    ));
                }
                rec(view,list);
            }
        });
        return view;
    }

    public void rec(View view,List<News> list){
        recyclerView = view.findViewById(R.id.rv_new);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        NewsAdapter newsAdapter = new NewsAdapter(list,getContext());
        recyclerView.setAdapter(newsAdapter);

        newsAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                Intent intent = new Intent(getContext(), NewActivity.class);
                intent.putExtra("tel",list.get(position).getTal());
                intent.putExtra("url",list.get(position).getImg());
                intent.putExtra("con",list.get(position).getCon());

                startActivity(intent);

            }
        });
    }
}