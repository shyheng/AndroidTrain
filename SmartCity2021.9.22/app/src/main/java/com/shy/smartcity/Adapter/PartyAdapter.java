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
import com.shy.smartcity.Bean.Party;
import com.shy.smartcity.R;

import java.util.List;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.PartyHolder> {

    private List<Party> data;
    private Context context;

    public PartyAdapter(List<Party> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public PartyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_par_stu,null);
        return new PartyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyHolder holder, int position) {
        request(holder.img, data.get(position).getImg());
        holder.tel.setText(data.get(position).getTel());
        holder.bf.setText(data.get(position).getBf());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PartyHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tel;
        TextView bf;
        public PartyHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.par_stu_img);
            tel = view.findViewById(R.id.par_stu_tel);
            bf = view.findViewById(R.id.par_stu_bf);

        }
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
