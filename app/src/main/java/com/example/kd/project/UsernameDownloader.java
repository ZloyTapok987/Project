package com.example.kd.project;

import android.widget.ImageView;
import android.widget.TextView;

import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;

/**
 * Created by Alex on 17.01.2018.
 */

class UsernameDownloader extends VKRequest.VKRequestListener
{
    ArrayList<User> users;
    MyAdapter adapter = null;
    TextView img = null;

    UsernameDownloader(ArrayList<User> users, MyAdapter adapter)
    {
        this.users = users;
        this.adapter = adapter;
    }

    UsernameDownloader(ArrayList<User> users, TextView img)
    {
        this.users = users;
        this.img = img;
    }

    @Override
    public void onComplete(VKResponse response) {
        super.onComplete(response);
        VKList<VKApiUser> users1 = (VKList<VKApiUser>)response.parsedModel;
        for(int i = 0;i<users1.size();i++)
        {
            User t = users.get(i);
            VKApiUser us = users1.get(i);
            t.UserName(us.first_name + " " + us.last_name);
            users.set(i, t);
        }
        if(img != null) {
            img.setText(users.get(0).UserName + " MMR: " + users.get(0).MMR);
        }
        if(adapter != null) {
            adapter.users = users;
            adapter.notifyDataSetChanged();
        }
    }
}