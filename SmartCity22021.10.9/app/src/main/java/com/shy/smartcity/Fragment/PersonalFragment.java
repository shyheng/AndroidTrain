package com.shy.smartcity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shy.smartcity.Activity.Personal.FeedbackActivity;
import com.shy.smartcity.Activity.Personal.OrderActivity;
import com.shy.smartcity.Activity.Personal.PersonalActivity;
import com.shy.smartcity.Activity.Personal.UpPasswordActivity;
import com.shy.smartcity.R;

import java.util.ArrayList;

public class PersonalFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        ListView listView = view.findViewById(R.id.lv_per);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("个人信息");
        strings.add("订单列表");
        strings.add("修改密码");
        strings.add("意见反馈");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(getContext(), PersonalActivity.class);
                    startActivity(intent);
                }else if (position == 2){
                    Intent intent = new Intent(getContext(), UpPasswordActivity.class);
                    startActivity(intent);
                }else if (position == 1){
                    Intent intent = new Intent(getContext(), OrderActivity.class);
                    startActivity(intent);
                }else if (position == 3){
                    Intent intent = new Intent(getContext(), FeedbackActivity.class);
                    startActivity(intent);
                }
            }
        });


        ImageView img = view.findViewById(R.id.img_per);
        TextView uer = view.findViewById(R.id.user);
        TextView id = view.findViewById(R.id.id);

        Button exit = view.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit.setText("请登录");
                img.setImageResource(R.color.white);
                uer.setText("请登录，登录后使用");
                id.setText("");
            }
        });

        return view;
    }
}