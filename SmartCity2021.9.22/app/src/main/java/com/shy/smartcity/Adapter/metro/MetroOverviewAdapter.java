package com.shy.smartcity.Adapter.metro;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.smartcity.Bean.Metro;
import com.shy.smartcity.R;

import java.util.List;

public class MetroOverviewAdapter extends RecyclerView.Adapter<MetroOverviewAdapter.MetroOverviewHolder> {

    List<Metro> metros;
    Context context;

    public MetroOverviewAdapter(List<Metro> metros, Context context) {
        this.metros = metros;
        this.context = context;
    }

    @NonNull
    @Override
    public MetroOverviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MetroOverviewHolder(View.inflate(context, R.layout.item_metro,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MetroOverviewHolder holder, int position) {
        holder.img.setImageResource(metros.get(position).getImg());
        holder.text.setText(metros.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return metros.size();
    }

    class MetroOverviewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView text;

        public MetroOverviewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.col);
            text = view.findViewById(R.id.met);

        }
    }
}
