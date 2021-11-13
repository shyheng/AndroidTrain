package com.example.smartcity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.smartcity.Adapter.Adapter;
import com.example.smartcity.Json.Category;
import com.example.smartcity.Web.JsonThread;
import com.example.smartcity.R;
import com.example.smartcity.Web.RequestImage;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class JfFragment extends Fragment {

    private GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jf, container, false);
        gv = view.findViewById(R.id.gv1);
        new Thread(() -> {
            JsonThread.get("/prod-api/api/living/category/list", new JsonThread() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Category category = new Gson().fromJson(response.body().string(), Category.class);
                    getActivity().runOnUiThread(()->{
                        gv.setAdapter(new Adapter(category.getData()) {
                            class ViewHolder {
                                public View rootView;
                                public ImageView jf_img;
                                public TextView jf_tv;
                                public ViewHolder(View rootView) {
                                    this.rootView = rootView;
                                    this.jf_img = (ImageView) rootView.findViewById(R.id.jf_img);
                                    this.jf_tv = (TextView) rootView.findViewById(R.id.jf_tv);
                                }

                            }

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                ViewHolder holder = new ViewHolder(View.inflate(getContext(), R.layout.itme_jf, null));
                                holder.jf_tv.setText(category.getData().get(position).getLiveName());
                                RequestImage.request(holder.jf_img, category.getData().get(position).getImgUrl(), getContext());
                                return holder.rootView;
                            }
                        });
                    });
                }
            });
        }).start();

        return view;
    }
}