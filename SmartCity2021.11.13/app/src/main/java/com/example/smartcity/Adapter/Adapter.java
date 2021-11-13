package com.example.smartcity.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class Adapter extends BaseAdapter {

    int i;

    List list;

    public Adapter(int i) {
        this.i = i;
    }

    public Adapter(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null){
            return i;
        }else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
