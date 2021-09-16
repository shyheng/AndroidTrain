package com.hhh.dingdingtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hhh.dingdingtask.R;
import com.hhh.dingdingtask.entity.Item;



import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Item> data;

    public MyAdapter(List<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setChannel(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null ? 0 : data.size();
    }


//    private OnRecyclerItemClick onRecyclerItemClick;
//
//    public void setOnRecyclerItemClick(OnRecyclerItemClick listener){
//        onRecyclerItemClick = listener;
//    }
//
//    public interface OnRecyclerItemClick{
//        void OnRecyclerItemClick(int position);
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Test test;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ViewUtil.waveView(itemView);
            test = itemView.findViewById(R.id.test);
        }
        public void setChannel(Item item) {
            test.setChannel(item);
        }
    }
}
