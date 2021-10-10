package com.shy.smartcity.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.shy.smartcity.Bean.Move;
import com.shy.smartcity.R;

import java.util.List;

public class ViolationAdapter extends RecyclerView.Adapter<ViolationAdapter.ViolationHolder> {

    private Context context;

    public ViolationAdapter( Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViolationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_violation,null);
        return new ViolationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViolationHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViolationHolder extends RecyclerView.ViewHolder{

        LinearLayout ll;

        public ViolationHolder(@NonNull View view) {
            super(view);

            ll = view.findViewById(R.id.ll_vio);

            ll.setOnClickListener(new View.OnClickListener() {
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
