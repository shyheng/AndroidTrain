package com.shy.smartcity.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.smartcity.Bean.Ser;
import com.shy.smartcity.R;

import java.util.List;

public class SerAdapter extends RecyclerView.Adapter<SerAdapter.SerHolder> {

    List<Ser> sers;
    Context context;

    public SerAdapter(List<Ser> sers, Context context) {
        this.sers = sers;
        this.context = context;
    }

    @NonNull
    @Override
    public SerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SerHolder(View.inflate(context, R.layout.item_ser,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SerHolder holder, int position) {
        holder.img.setImageResource(sers.get(position).getImg());
        holder.text.setText(sers.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return sers.size();
    }

    class SerHolder extends RecyclerView.ViewHolder{

        LinearLayout ll_ser;
        ImageView img;
        TextView text;

        public SerHolder(@NonNull View view) {
            super(view);
            ll_ser = view.findViewById(R.id.ll_ser);
            img = view.findViewById(R.id.ser_img);
            text = view.findViewById(R.id.ser_text);

            ll_ser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerItemClick.OnRecyclerItemClick(getAdapterPosition());
                }
            });

        }
    }
    private ServerAdapter.OnRecyclerItemClick onRecyclerItemClick;

    public void setOnRecyclerItemClick(ServerAdapter.OnRecyclerItemClick listener){
        onRecyclerItemClick = listener;
    }

    public interface OnRecyclerItemClick{
        void OnRecyclerItemClick(int position);
    }
}
