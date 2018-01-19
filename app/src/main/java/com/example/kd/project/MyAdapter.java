package com.example.kd.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kd.project.R;
import com.example.kd.project.User;
import com.example.kd.project.VKManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<User> users;

    MyAdapter(Context context, ArrayList<User> products) {
        ctx = context;
        this.users = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return users.size();
    }

    // элемент по позиции
    @Override

    public Object getItem(int position) {
        return users.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder
    {
        public Context c;
        public TextView place;
        public ImageView photo;
        public TextView name;
        public TextView MMR;
        public View view;
        public ViewHolder() {
        }
    }
    // пункт списка
    @Override
    public synchronized View getView(int i, View v, ViewGroup parent) {
        User a=users.get(i);
        if (a.v==null) {
            int z=i+1;
            v = lInflater.inflate(R.layout.item, parent, false);
            Picasso.with(v.getContext()).load(R.drawable.loading).resize(180, 180).into((ImageView) v.findViewById(R.id.photo));
            VKManager.setPhotoByUserId(v.getContext(), users.get(i).getId(), (ImageView) v.findViewById(R.id.photo), 2, 180, 180);
            ((TextView) v.findViewById(R.id.place)).setText("" + z);
            ((TextView) v.findViewById(R.id.mmr)).setText("" + a.getMMR());
            ((TextView) v.findViewById(R.id.name)).setText("" + a.getUserName());
            (users.get(i)).V(v);
        }
        else
        {
            v=a.getView();
        }

        /*
        ViewHolder holder;

        if (v==null) {
            v = lInflater.inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.place = (TextView) v.findViewById(R.id.place);
            holder.name = (TextView) v.findViewById(R.id.name);
            holder.MMR = (TextView) v.findViewById(R.id.mmr);
            holder.photo = (ImageView) v.findViewById(R.id.photo);
            v.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)v.getTag();
        }
        int z=i+1;
        holder.place.setText(z+"");
        User a=users.get(i);
        holder.name.setText(a.getUserName());
        holder.MMR.setText(a.getMMR());
        Picasso.with(v.getContext()).load(R.drawable.doshik).resize(180,180).into(holder.photo);
        if (a.bitmap==null)
        {
            VKManager.setPhotoByUserId(ctx,a.getId(),(ImageView) v.findViewById(R.id.photo),0,180, 180);
            a.bitmap=v.getDrawingCache();
            users.get(i).bitmap=a.bitmap;
        }
        else ((ImageView) v.findViewById(R.id.photo)).setImageBitmap(a.bitmap);
        */
        return v;
    }

}