package com.example.kd.project;

import android.widget.ImageView;

import com.vk.sdk.VKAccessToken;

public class User
{
    String UserName=new String();
    String MMR=new String();
    String id=new String();
    VKAccessToken res;
    public void UserName(String userName)
    {
        this.UserName=userName;
    }
    public void Res(){this.res=res;}
    public void MMR(String MMR)
    {
        this.MMR=MMR;
    }
    public void Id(String id)
    {
        this.id=id;
    }
    public String getUserName()
    {
        return this.UserName;
    }
    public String getMMR()
    {
        return this.MMR;
    }
    public String getId()
    {
        return this.id;
    }
}
