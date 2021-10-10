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
import com.shy.smartcity.R;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewHolder> {

    private List<New> data;
    private Context context;

    public NewAdapter(List<New> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_new,null);
        return new NewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder holder, int position) {
        request(holder.img, data.get(position).getImg());
        holder.tal.setText(data.get(position).getTitle());
        holder.con.setText(data.get(position).getCon());
        holder.num.setText(data.get(position).getNum());
        holder.date.setText(data.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewHolder extends RecyclerView.ViewHolder{

        LinearLayout ll;
        ImageView img;
        TextView tal;
        TextView con;
        TextView num;
        TextView date;
        public NewHolder(@NonNull View view) {
            super(view);
            ll = view.findViewById(R.id.ll_n);
            img = view.findViewById(R.id.n_img);
            tal = view.findViewById(R.id.n_t);
            con = view.findViewById(R.id.n_n);
            num = view.findViewById(R.id.n_num);
            date = view.findViewById(R.id.n_date);
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
