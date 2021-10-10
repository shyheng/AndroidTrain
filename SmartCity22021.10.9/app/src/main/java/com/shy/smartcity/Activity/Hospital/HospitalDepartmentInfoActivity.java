package com.shy.smartcity.Activity.Hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shy.smartcity.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HospitalDepartmentInfoActivity extends AppCompatActivity {

    public static String nian;
    public static String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_department_info);

        Button button = findViewById(R.id.hos_dep_info_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalDepartmentInfoActivity.this,HospitalDepartmentActivity.class);
                startActivity(intent);
            }
        });
        TextView textView  = findViewById(R.id.dep_zj_text);
        List<String> list = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String yew = format.format(date);
        for (int i = 0; i < 9; i++) {
            list.add(yew+" 下午"+"1"+i+":00 "+HospitalDepartmentActivity.department);
        }

        ListView listView = findViewById(R.id.dep_info_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,list);

        listView.setAdapter(adapter);

        Button zj = findViewById(R.id.dep_zj);
        Button pt = findViewById(R.id.dep_pt);

        pt.setBackgroundResource(R.color.purple_200);
        zj.setBackgroundResource(R.color.white);

        zj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zj.setBackgroundResource(R.color.purple_200);
                pt.setBackgroundResource(R.color.white);
                textView.setText("暂无数据");
                List<String> list = new ArrayList<>();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,list);
                listView.setAdapter(adapter);
            }
        });




        pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pt.setBackgroundResource(R.color.purple_200);
                zj.setBackgroundResource(R.color.white);
                textView.setText("");
                listView.setAdapter(adapter);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] s = list.get(position).split(" ");
                nian = s[0];
                time = s[1];
                Intent intent = new Intent(HospitalDepartmentInfoActivity.this,HospitalConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}