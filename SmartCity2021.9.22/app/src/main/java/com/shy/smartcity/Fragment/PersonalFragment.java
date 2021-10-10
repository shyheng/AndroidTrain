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

import com.shy.smartcity.Activity.FeedbackActivity;
import com.shy.smartcity.Activity.OrderActivity;
import com.shy.smartcity.Activity.PersonalActivity;
import com.shy.smartcity.Activity.UpPasswordActivity;
import com.shy.smartcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ListView  listView = view.findViewById(R.id.lv_per);
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