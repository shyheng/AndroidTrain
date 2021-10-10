package com.shy.smartcity.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.Adapter.OrderAdapter;
import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Bean.Order;
import com.shy.smartcity.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    public static int anInt= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Button button = findViewById(R.id.o_p);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anInt = 1;
                Intent intent = new Intent(OrderActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        List<Order> orders = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            orders.add(new Order("100000"+i,"市价单",format.format(new Date())));
        }

        RecyclerView recyclerView = findViewById(R.id.o_rv);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        OrderAdapter orderAdapter = new OrderAdapter(orders,this);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.setOnRecyclerItemClick(new ServerAdapter.OnRecyclerItemClick() {
            @Override
            public void OnRecyclerItemClick(int position) {
                Intent intent = new Intent(OrderActivity.this,OrderDetailedActivity.class);
                intent.putExtra("id",orders.get(position).getId());
                intent.putExtra("type",orders.get(position).getType());
                intent.putExtra("date",orders.get(position).getDate());
                startActivity(intent);
            }
        });

    }
}