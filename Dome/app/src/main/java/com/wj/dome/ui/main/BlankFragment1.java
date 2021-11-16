package com.wj.dome.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wj.dome.R;
import com.wj.dome.ui.guide.GuideActivity;
import com.wj.dome.ui.minute.MinuteActivity;


public class BlankFragment1 extends Fragment {

    private Button main;
    private Button minute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank1, container, false);

        main = view.findViewById(R.id.main_g);
        minute = view.findViewById(R.id.main_minute);

        main.setOnClickListener((view1)->{
            startActivity(new Intent(getContext(), GuideActivity.class));
        });

        minute.setOnClickListener((view1)->{
            startActivity(new Intent(getContext(), MinuteActivity.class));
        });

        return view;
    }
}