package com.shy.smartcity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Adapter.metro.IcoAdapter;
import com.shy.smartcity.Bean.Ico;
import com.shy.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class MetroActivity extends AppCompatActivity {

    Button exit;
    Button ove;

    TextView text;

    RecyclerView recyclerView;

    List<Ico> icos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        exit = findViewById(R.id.e_met);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetroActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        ove = findViewById(R.id.l_met);
        ove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetroActivity.this, Metro_OverviewActivity.class);
                startActivity(intent);
            }
        });

        text = findViewById(R.id.t_met);

        icos = new ArrayList<>();
        icos.add(new Ico(R.drawable.xq, "西山公园", R.color.white));
        icos.add(new Ico(R.drawable.xq, "车家壁", R.color.white));
        icos.add(new Ico(R.drawable.xq, "普坪村", R.color.white));
        icos.add(new Ico(R.drawable.xq, "石咀", R.color.white));
        icos.add(new Ico(R.drawable.xq, "大渔路", R.color.white));
        icos.add(new Ico(R.drawable.xq, "西部汽车站", R.color.white));
        icos.add(new Ico(R.drawable.xq, "眠山", R.color.white));
        icos.add(new Ico(R.drawable.xq, "昌源中路", R.color.white));
        icos.add(new Ico(R.drawable.xq, "西苑", R.color.white));
        icos.add(new Ico(R.drawable.xq, "梁家河", R.color.white));
        icos.add(new Ico(R.drawable.xq, "市体育馆", R.drawable.dqwz));
        icos.add(new Ico(R.drawable.xq, "潘家湾", R.color.white));
        icos.add(new Ico(R.drawable.xq, "五一路", R.color.white));
        icos.add(new Ico(R.drawable.xq, "东风广场", R.color.white));
        icos.add(new Ico(R.drawable.xq, "拓东体育馆", R.color.white));
        icos.add(new Ico(R.drawable.xq, "大树营", R.color.white));
        icos.add(new Ico(R.drawable.xq, "金马寺", R.color.white));
        icos.add(new Ico(R.drawable.xq, "太平村", R.color.white));
        icos.add(new Ico(R.drawable.xq, "虹桥", R.color.white));
        icos.add(new Ico(R.drawable.xq, "东部汽车站", R.color.white));

        recyclerView = findViewById(R.id.rv_met);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        IcoAdapter icoAdapter = new IcoAdapter(icos, this);
        recyclerView.setAdapter(icoAdapter);
        icoAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                on(position);
            }
        });



    }

    public void on(int position){
        for (int i = 0; i < icos.size(); i++) {
            if (icos.get(i).getIco() == R.drawable.dqwz){
                icos.set(i,new Ico(R.drawable.xq, icos.get(i).getText(), R.color.white));
            }
        }
        text.setText(icos.get(position).getText());
        icos.set(position, new Ico(R.drawable.xq, icos.get(position).getText(), R.drawable.dqwz));
        IcoAdapter icoAdapter = new IcoAdapter(icos, getApplicationContext());
        recyclerView.setAdapter(icoAdapter);
        icoAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                on(position);
            }
        });
    }
}
