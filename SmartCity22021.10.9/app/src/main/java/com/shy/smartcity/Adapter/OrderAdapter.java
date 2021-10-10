package com.shy.smartcity.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.smartcity.Bean.Order;
import com.shy.smartcity.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    List<Order> orders;
    Context context;

    public OrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderHolder(View.inflate(context, R.layout.item_order, null));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        holder.id.setText(orders.get(position).getId());
        holder.type.setText(orders.get(position).getType());
        holder.date.setText(orders.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_ord;
        TextView id;
        TextView type;
        TextView date;

        public OrderHolder(@NonNull View view) {
            super(view);

            ll_ord = view.findViewById(R.id.ll_ord);
            id = view.findViewById(R.id.d_id);
            type = view.findViewById(R.id.d_type);
            date = view.findViewById(R.id.d_date);

            ll_ord.setOnClickListener(new View.OnClickListener() {
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
