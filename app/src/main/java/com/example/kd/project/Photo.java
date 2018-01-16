package com.example.kd.project;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vk.sdk.VKSdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Photo extends AppCompatActivity {
    TitleFragment fragment1=new TitleFragment();
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        //VKSdk.login(Photo.this);
        setContentView(R.layout.activity_photo);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment1).commit();
        c=this;
        TextView t=(TextView)findViewById(R.id.title);
        Typeface type = Typeface.createFromAsset(getAssets(), "vs.ttf");
        t.setTypeface(type);


        VKManager.setPhotoByUserId(this,"305663627",(ImageView)findViewById(R.id.photo1),5,null, null);
        VKManager.setPhotoByUserId(this,"285937394",(ImageView)findViewById(R.id.photo2),5,null, null);


        ImageView versus=(ImageView)findViewById(R.id.versus);
        versus.setImageResource(R.drawable.vs);


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


        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.choose: {
                        Intent intent=new Intent(c,Photo.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        c.startActivity(intent);
                        break;
                    }
                    case R.id.rating:{
                        Intent intent=new Intent(c,Rating.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        c.startActivity(intent);
                        break;
                    }
                    case R.id.profile:{
                        Intent intent=new Intent(c,Profile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        c.startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });
    }
}

