package com.shy.smartcity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.shy.smartcity.Activity.Community.CommunityBusinessActivity;
import com.shy.smartcity.Activity.Community.CommunityExpressActivity;
import com.shy.smartcity.Activity.Community.CommunityNetworkingActivity;
import com.shy.smartcity.Activity.Community.CommunityPropertyActivity;
import com.shy.smartcity.Activity.Community.CommunityVehicleActivity;
import com.shy.smartcity.Adapter.GideAdapter;
import com.shy.smartcity.R;
import com.shy.smartcity.web.RequestImage;

import java.util.ArrayList;
import java.util.List;


public class CommunityFragment extends Fragment {


    ViewPager viewPager1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_community, container, false);

        viewPager1= view.findViewById(R.id.comm_banner);
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

        List<String> list = new ArrayList<>();
        list.add("物业服务");
        list.add("快件管理");
        list.add("友邻社交");
        list.add("商业推广");
        list.add("车辆管理");

        ListView listView = view.findViewById(R.id.comm_lv);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(getContext(), CommunityPropertyActivity.class);
                    startActivity(intent);
                }
                if (position == 1){
                    Intent intent = new Intent(getContext(), CommunityExpressActivity.class);
                    startActivity(intent);
                }
                if (position == 2){
                    Intent intent = new Intent(getContext(), CommunityNetworkingActivity.class);
                    startActivity(intent);
                }
                if (position == 3){
                    Intent intent = new Intent(getContext(), CommunityBusinessActivity.class);
                    startActivity(intent);
                }
                if (position == 4){
                    Intent intent = new Intent(getContext(), CommunityVehicleActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
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

    @Override
    public void onResume() {
        super.onResume();
        flg = true;
        banner();
    }
}