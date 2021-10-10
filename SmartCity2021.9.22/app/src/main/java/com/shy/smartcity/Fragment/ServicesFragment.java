package com.shy.smartcity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.shy.smartcity.Activity.MetroActivity;
import com.shy.smartcity.Adapter.SerAdapter;
import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Bean.Ser;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
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

    public static ViewPager2 viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_services, container, false);


        EditText text = view.findViewById(R.id.inp);
        Button button = view.findViewById(R.id.b_s);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String s = String.valueOf(text.getText());
                if (s.equals("地铁")){
                    Intent intent = new Intent(getContext(), MetroActivity.class);
                    startActivity(intent);
                }else if (s.equals("党建")){
                    viewPager2.setCurrentItem(2);
                }else if (s.equals("个人")){
                    viewPager2.setCurrentItem(4);
                }else if (s.equals("新闻")){
                    viewPager2.setCurrentItem(3);
                }
            }
        });

        List<Ser> sers = new ArrayList<>();
        sers.add(new Ser(R.drawable.dt,"地铁"));
        sers.add(new Ser(R.drawable.party,"党建"));
        sers.add(new Ser(R.drawable.personal,"信息"));
        sers.add(new Ser(R.drawable.news,"新闻"));
        for (int i = 0; i < 8; i++) {
            sers.add(new Ser(R.drawable.img_home1,"购物"));
        }


        RecyclerView recyclerView = view.findViewById(R.id.ser_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        SerAdapter serAdapter = new SerAdapter(sers,getContext());
        recyclerView.setAdapter(serAdapter);
        serAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                if (position==0){
                    Intent intent = new Intent(getContext(), MetroActivity.class);
                    startActivity(intent);
                }else if (position==1){
                    viewPager2.setCurrentItem(2);
                }else if (position==2){
                    viewPager2.setCurrentItem(4);
                }else if (position==3){
                    viewPager2.setCurrentItem(3);
                }
            }
        });

        return view;
    }
}