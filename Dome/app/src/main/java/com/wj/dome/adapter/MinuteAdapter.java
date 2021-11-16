package com.wj.dome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.wj.dome.R;
import com.wj.dome.model.MinuteModel;

import java.util.ArrayList;
import java.util.List;

public class MinuteAdapter extends PagerAdapter {

    private List<MinuteModel> models = new ArrayList<>();
    private List<View> views = new ArrayList<>();
    private Context context;

    public MinuteAdapter(List<MinuteModel> models, Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);

        this.models = models;
        this.context = context;

        for (int i = 0; i < models.size(); i++) {
            System.out.println(models.get(0).getText());
            View view = inflater.inflate(R.layout.item_vp, null);

            TextView textView = view.findViewById(R.id.tv_vp);
            ImageView imageView = view.findViewById(R.id.img_vp);

            textView.setText(models.get(i).getText());
            imageView.setImageResource(models.get(i).getImg());

            views.add(view);
        }
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }
}
