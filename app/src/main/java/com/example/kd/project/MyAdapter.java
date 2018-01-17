package com.example.kd.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kd.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    ArrayList<User> users;

    MyAdapter(ArrayList<User> users)
    {
        this.users=users;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int i) {
        int z=i+1;
        holder.place.setText(z+"");
        User a=users.get(i);
        holder.name.setText(a.getUserName());
        holder.MMR.setText(a.getMMR());

        Picasso.with(holder.c).load(R.drawable.doshik).resize(180,180).into(holder.photo);

        Log.d("asd", "" + i);

        VKManager.setPhotoByUserId(holder.c,users.get(i).getId(),holder.photo,2,180, 180);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                view.getContext().startActivity(intent);
            }
        });
    }


    
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Context c;
        public TextView place;
        public ImageView photo;
        public TextView name;
        public TextView MMR;
        public View view;
        public ViewHolder(View v) {
            super(v);
            this.c=v.getContext();
            this.place=(TextView)v.findViewById(R.id.place);
            this.photo=(ImageView)v.findViewById(R.id.photo);
            this.name=(TextView)v.findViewById(R.id.name);
            this.MMR=(TextView)v.findViewById(R.id.mmr);
            this.view=v;
        }
    }
}