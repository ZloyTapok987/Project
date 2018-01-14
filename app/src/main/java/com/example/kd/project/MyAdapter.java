package com.example.kd.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<User> obj;
    MyAdapter(Context context, ArrayList<User> products) {
        ctx = context;
        obj = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return obj.size();
    }

    @Override
    public Object getItem(int i) {
        return obj.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = lInflater.inflate(R.layout.item, viewGroup, false);
        }
        User user=(User)getItem(i);
        ((TextView)view.findViewById(R.id.postion)).setText(""+i);
        ((TextView)view.findViewById(R.id.UserName)).setText(user.getUserName());
        ((TextView)view.findViewById(R.id.MMR)).setText(user.getMMR());
        new DownloadImageTask((ImageView)view.findViewById(R.id.miniphoto)).execute(user.getUrl());
        return view;
    }
}
