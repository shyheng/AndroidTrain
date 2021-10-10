package com.shy.smartcity.Activity.Hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalDepartmentActivity extends AppCompatActivity {

    public static String department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_department);

        Button button = findViewById(R.id.hos_dep_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalDepartmentActivity.this,HospitalInfoActivity.class);
                startActivity(intent);
            }
        });

        TextView name = findViewById(R.id.dep_name);
        name.setText(HospitalInfoActivity.name);

        TextView sex = findViewById(R.id.dep_sex);
        sex.setText(HospitalInfoActivity.sex);

        TextView sfz = findViewById(R.id.dep_sfz);
        sfz.setText(HospitalInfoActivity.sfz);

        List<String> list = new ArrayList<>();
        list.add("急诊");
        list.add("神经内科");
        list.add("心肾内科");
        list.add("呼吸消化科");
        list.add("慢性病科");
        list.add("普外科");
        list.add("骨外科");
        list.add("妇产科");
        list.add("儿科");
        list.add("眼耳鼻喉科");
        list.add("口腔科皮肤科");

        ListView listView = findViewById(R.id.dep_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HospitalDepartmentActivity.this,HospitalDepartmentInfoActivity.class);
                department = list.get(position);
                startActivity(intent);
            }
        });
    }
}