package com.shy.smartcity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shy.smartcity.Activity.FeedbackActivity;
import com.shy.smartcity.Activity.OrderActivity;
import com.shy.smartcity.Activity.Party.PartyActivityActivity;
import com.shy.smartcity.Activity.Party.PartyAdviceActivity;
import com.shy.smartcity.Activity.Party.PartyDynamicActivity;
import com.shy.smartcity.Activity.Party.PartyPhotographActivity;
import com.shy.smartcity.Activity.Party.PartyStudyActivity;
import com.shy.smartcity.Activity.PersonalActivity;
import com.shy.smartcity.Activity.UpPasswordActivity;
import com.shy.smartcity.Adapter.BannerAdapter;
import com.shy.smartcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PartyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PartyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PartyFragment newInstance(String param1, String param2) {
        PartyFragment fragment = new PartyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_party, container, false);

        viewPager2 = view.findViewById(R.id.vp_par);

        BannerAdapter bannerAdapter = new BannerAdapter(getActivity());
        viewPager2.setAdapter(bannerAdapter);
        test(true);

        listView = view.findViewById(R.id.par_lv);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("党建动态");
        strings.add("党员学习");
        strings.add("组织活动");
        strings.add("建言献策");
        strings.add("随手拍");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(getContext(), PartyDynamicActivity.class);
                    startActivity(intent);
                }else if (position == 1){
                    Intent intent = new Intent(getContext(),PartyStudyActivity.class);
                    startActivity(intent);
                }else if (position == 2){
                    Intent intent = new Intent(getContext(), PartyActivityActivity.class);
                    startActivity(intent);
                }else if (position == 3){
                    Intent intent = new Intent(getContext(), PartyAdviceActivity.class);
                    startActivity(intent);
                }else if (position == 4){
                    Intent intent = new Intent(getContext(), PartyPhotographActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}