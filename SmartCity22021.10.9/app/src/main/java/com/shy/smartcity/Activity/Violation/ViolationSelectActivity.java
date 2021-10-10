package com.shy.smartcity.Activity.Violation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Adapter.ViolationAdapter;
import com.shy.smartcity.R;

public class ViolationSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation_select);

        Button button = findViewById(R.id.vio_sel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViolationSelectActivity.this,ViolationActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.vio_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        ViolationAdapter violationAdapter = new ViolationAdapter(this);

        recyclerView.setAdapter(violationAdapter);

        violationAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                Intent intent = new Intent(ViolationSelectActivity.this,ViolationDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}