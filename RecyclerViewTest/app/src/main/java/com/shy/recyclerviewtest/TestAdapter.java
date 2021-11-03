package com.shy.recyclerviewtest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {

    List<Test> tests;
    Context context;

    public TestAdapter(List<Test> tests,Context context) {
        this.context = context;
        this.tests = tests;
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestHolder(View.inflate(context,R.layout.itme,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        holder.imageView.setImageResource(tests.get(position).getImg());
        holder.textView.setText(tests.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class TestHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        Button button;
        public TestHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.img);
            textView = view.findViewById(R.id.tv);
            button = view.findViewById(R.id.but);
        }
    }
}
