package com.shy.smartcity.Adapter.metro;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.smartcity.Adapter.ServerAdapter;
import com.shy.smartcity.Bean.Ico;
import com.shy.smartcity.R;

import java.util.List;

public class IcoAdapter extends RecyclerView.Adapter<IcoAdapter.IcoHolder> {

    List<Ico> icos;
    Context context;

    public IcoAdapter(List<Ico> icos, Context context) {
        this.icos = icos;
        this.context = context;
    }

    @NonNull
    @Override
    public IcoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_ico,null);
        return new IcoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IcoHolder holder, int position) {
        holder.img.setImageResource(icos.get(position).getImg());
        holder.text.setText(icos.get(position).getText());
        holder.ico.setImageResource(icos.get(position).getIco());
    }

    @Override
    public int getItemCount() {
        return icos.size();
    }

    class IcoHolder extends RecyclerView.ViewHolder{

        LinearLayout ll_met;
        ImageView img;
        TextView text;
        ImageView ico;

        public IcoHolder(@NonNull View view) {
            super(view);
            ll_met = view.findViewById(R.id.ll_met);
            img = view.findViewById(R.id.img_met);
            text = view.findViewById(R.id.text_met);
            ico = view.findViewById(R.id.img_ico);

            ll_met.setOnClickListener(new View.OnClickListener() {
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
