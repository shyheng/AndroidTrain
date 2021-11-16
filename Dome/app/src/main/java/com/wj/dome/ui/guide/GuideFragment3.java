package com.wj.dome.ui.guide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wj.dome.R;
import com.wj.dome.ui.main.MainActivity;

public class GuideFragment3 extends Fragment {

    private Button but;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide3, container, false);

        but = view.findViewById(R.id.but_g);

        but.setOnClickListener((view1)->{
            startActivity(new Intent(getActivity(), MainActivity.class));
            SharedPreferences preferences = getContext().getSharedPreferences("shy", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("shy",true);
            editor.commit();
        });

        return view;
    }
}