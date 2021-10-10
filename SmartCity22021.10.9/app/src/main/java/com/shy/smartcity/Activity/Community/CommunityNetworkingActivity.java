package com.shy.smartcity.Activity.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

import java.util.ArrayList;

public class CommunityNetworkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_networking);

        Button button = findViewById(R.id.com_net_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityNetworkingActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add("热心网友\n这个好，这也太棒了");
        }

        ListView listView = findViewById(R.id.com_net_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,strings);
        listView.setAdapter(adapter);



    }
}