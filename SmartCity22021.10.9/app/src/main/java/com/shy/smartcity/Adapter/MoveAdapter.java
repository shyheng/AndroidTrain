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
import com.shy.smartcity.Bean.New;
import com.shy.smartcity.R;

import java.util.List;

public class MoveAdapter extends RecyclerView.Adapter<MoveAdapter.MoveHolder> {

    private List<Move> data;
    private Context context;

    public MoveAdapter(List<Move> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MoveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_move,null);
        return new MoveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveHolder holder, int position) {
        holder.img.setImageResource(data.get(position).getImg());
        holder.tal.setText(data.get(position).getName());
        holder.con.setText(data.get(position).getPer());
        holder.num.setText(data.get(position).getDz());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MoveHolder extends RecyclerView.ViewHolder{

        LinearLayout ll;
        ImageView img;
        TextView tal;
        TextView con;
        TextView num;
        public MoveHolder(@NonNull View view) {
            super(view);
            ll = view.findViewById(R.id.ll_mov);
            img = view.findViewById(R.id.img_move);
            tal = view.findViewById(R.id.text_move);
            con = view.findViewById(R.id.textp_move);
            num = view.findViewById(R.id.textd_move);
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
