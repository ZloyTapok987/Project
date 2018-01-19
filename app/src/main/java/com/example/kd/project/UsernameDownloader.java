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
    TextView view1 = null,view2 = null,view3 = null;
    String pos = null;

    UsernameDownloader(ArrayList<User> users, MyAdapter adapter)
    {
        this.users = users;
        this.adapter = adapter;
    }

    UsernameDownloader(ArrayList<User> users, TextView view1, TextView view2, TextView view3, String pos)
    {
        this.users = users;
        this.view1 = view1;
        this.view2 = view2;
        this.view3 = view3;
        this.pos = pos;
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
        if(view1 != null && view2 != null && view3 != null) {
            view1.setText(users.get(0).UserName);
            view2.setText("Рейтинг: " + users.get(0).MMR);
            view3.setText("Место: " + pos);
        }
        if(adapter != null) {
            adapter.users = users;
            adapter.notifyDataSetChanged();
        }
    }
}