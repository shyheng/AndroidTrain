package com.shy.smartcity.Activity.Party;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.Adapter.PartyAdapter;
import com.shy.smartcity.Bean.Party;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class PartyStudyActivity extends AppCompatActivity {

    Button button;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_study);

        button = findViewById(R.id.p_s_b);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartyStudyActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        List<Party> parties = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parties.add(new Party("https://img0.baidu.com/it/u=585527081,2514796754&fm=26&fmt=auto",
                    "学习强国",i+"%"));
        }

        recyclerView = findViewById(R.id.par_stu_rv);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        recyclerView.setAdapter(new PartyAdapter(parties,this));
    }
}