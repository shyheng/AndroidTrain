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
import com.shy.smartcity.Bean.New;
import com.shy.smartcity.Bean.News;
import com.shy.smartcity.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<News> data;
    private Context context;

    public NewsAdapter(List<News> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_news,null);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        request(holder.img, data.get(position).getImg());
        holder.tal.setText(data.get(position).getTal());
        holder.num.setText(data.get(position).getNum());
        holder.date.setText(data.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{

        LinearLayout ll;
        ImageView img;
        TextView tal;
        TextView num;
        TextView date;
        public NewsHolder(@NonNull View view) {
            super(view);
            ll = view.findViewById(R.id.ll_news);
            img = view.findViewById(R.id.ns_img);
            tal = view.findViewById(R.id.ns_t);
            num = view.findViewById(R.id.ns_num);
            date = view.findViewById(R.id.ns_date);
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
