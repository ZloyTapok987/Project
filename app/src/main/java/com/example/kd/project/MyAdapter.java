package com.example.kd.project;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kd.project.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder>{
    ArrayList<User> users;
    MyAdapter(ArrayList<User> users){
        this.users = users;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int i) {
        holder.UserName.setText((users.get(i).getUserName()));
        holder.MMR.setText((users.get(i)).getMMR());
        
        VKManager.setPhotoByUserId(holder.view.getContext(),(users.get(i)).getId(),(ImageView)holder.view.findViewById(R.id.person_photo),160,160);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView UserName;
        TextView MMR;
        ImageView personPhoto;
        View view;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            UserName = (TextView)itemView.findViewById(R.id.person_name);
            MMR = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            view=itemView;
        }
    }
}