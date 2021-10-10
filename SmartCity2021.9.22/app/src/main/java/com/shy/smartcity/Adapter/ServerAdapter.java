package com.shy.smartcity.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.smartcity.Bean.Service;
import com.shy.smartcity.R;

import java.util.List;


public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ServerHolder> {
    private List<Service> data;
    private Context context;

    public ServerAdapter(List<Service> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ServerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_server,null);
        return new ServerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServerHolder holder, int position) {
        holder.img.setImageResource(data.get(position).getImage());
        holder.text.setText(data.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ServerHolder extends RecyclerView.ViewHolder {

        LinearLayout ll;
        ImageView img;
        TextView text;
        public ServerHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            img = itemView.findViewById(R.id.i_img);
            text = itemView.findViewById(R.id.i_s);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerItemClick.OnRecyclerItemClick(getAdapterPosition());
                }
            });
        }


    }
    private OnRecyclerItemClick onRecyclerItemClick;

    public void setOnRecyclerItemClick(OnRecyclerItemClick listener){
        onRecyclerItemClick = listener;
    }

    public interface OnRecyclerItemClick{
        void OnRecyclerItemClick(int position);
    }
}
