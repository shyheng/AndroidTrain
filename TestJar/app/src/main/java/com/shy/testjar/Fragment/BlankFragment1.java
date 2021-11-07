package com.shy.testjar.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.shy.testjar.Progress;
import com.shy.testjar.R;
import com.shy.testjar.SMsg;
import com.shy.testjar.TestService;
import com.shy.testjar.TestTeama;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BlankFragment1 extends Fragment {

    TextView tv1;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank1, container, false);
        tv1 = view.findViewById(R.id.tv_1);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        TestService.api = "/prod-api/api/common/gps/area";
        Intent intent = new Intent(getContext(), TestService.class);
        getActivity().startService(intent);

        EventBus.getDefault().register(this);
        Progress.show(getContext());
        return view;
    }



    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void text(SMsg msg){
        TestTeama teama = new Gson().fromJson(msg.s,TestTeama.class);
        tv1.setText(teama.getData().get(0).getCityName());
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(View.inflate(getContext(),R.layout.itme,null)){};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView textView = holder.itemView.findViewById(R.id.text1);
                textView.setText(teama.getData().get(position).getName());
            }

            @Override
            public int getItemCount() {
                return teama.getData().size();
            }
        });
        Progress.diss();
    }


    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}