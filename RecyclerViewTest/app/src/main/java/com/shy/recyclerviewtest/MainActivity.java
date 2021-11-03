package com.shy.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Test test = new Test();
            test.setImg(R.mipmap.ic_launcher);
            test.setText("jysdfs"+i);
            tests.add(test);
        }
        TestAdapter testAdapter = new TestAdapter(tests,this);
        recyclerView.setAdapter(testAdapter);
    }
}