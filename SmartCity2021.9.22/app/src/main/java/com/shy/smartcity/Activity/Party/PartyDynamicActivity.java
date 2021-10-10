package com.shy.smartcity.Activity.Party;

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

public class PartyDynamicActivity extends AppCompatActivity {

    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_dynamic);

        button = findViewById(R.id.p_d_b);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartyDynamicActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add("热线网友\n这个好，这也太棒了");
        }

        listView =findViewById(R.id.par_dyn_lv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);
    }
}