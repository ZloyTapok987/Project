package com.example.kd.project;

import android.widget.ImageView;

import com.vk.sdk.VKAccessToken;

public class User
{
    String UserName=new String();
    String MMR=new String();
    String url=new String();
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
    public void url(String url)
    {
        this.url=url;
    }
    public String getUserName()
    {
        return this.UserName;
    }
    public String getMMR()
    {
        return this.MMR;
    }
    public String getUrl()
    {
        return this.url;
    }
}
