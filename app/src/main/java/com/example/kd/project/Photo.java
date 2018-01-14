package com.example.kd.project;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.vk.sdk.VKSdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Photo extends AppCompatActivity {
    TitleFragment fragment1=new TitleFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        //VKSdk.login(Photo.this);
        setContentView(R.layout.activity_photo);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment1).commit();

        VKManager.setPhotoByUserId(this,305663627,(ImageView)findViewById(R.id.photo1));
        VKManager.setPhotoByUserId(this,285937394,(ImageView)findViewById(R.id.photo2));

        ImageView p1=findViewById(R.id.photo1);
        p1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "SanyaBog",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return false;
            }
        });
        ImageView p2=findViewById(R.id.photo2);
        p2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "VovaBog",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return false;
            }
        });
        //getFragmentManager().beginTransaction().replace(R.id.fragment);
    }
}

