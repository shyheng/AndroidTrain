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
import com.shy.smartcity.Bean.Hospital;
import com.shy.smartcity.R;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalHolder> {

    private List<Hospital> data;
    private Context context;

    public HospitalAdapter(List<Hospital> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public HospitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_hospital,null);
        return new HospitalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalHolder holder, int position) {
        request(holder.img,data.get(position).getImg());
        holder.name.setText(data.get(position).getName());
        holder.star.setText(data.get(position).getStar());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HospitalHolder extends RecyclerView.ViewHolder{

        LinearLayout ll;
        ImageView img;
        TextView name;
        TextView star;
        public HospitalHolder(@NonNull View view) {
            super(view);
            ll = view.findViewById(R.id.ll_hop);
            img = view.findViewById(R.id.hos_img);
            name = view.findViewById(R.id.hos_name);
            star = view.findViewById(R.id.hos_star);
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
    public ImageView request(ImageView image,String url) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                image.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        queue.add(imageRequest);

        return image;
    }
}
