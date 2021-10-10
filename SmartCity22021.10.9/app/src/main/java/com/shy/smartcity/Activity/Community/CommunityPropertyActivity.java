package com.shy.smartcity.Activity.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityPropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_property);

        Button button = findViewById(R.id.com_pro_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityPropertyActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        List<String> list = new ArrayList<>();
        list.add("物业 123456");
        list.add("物业 1256");
        list.add("物业 12531256");
        list.add("赵升勇 13294902283");

        ListView listView = findViewById(R.id.com_pro_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+list.get(position)));
                startActivity(intent);
            }
        });

    }
}