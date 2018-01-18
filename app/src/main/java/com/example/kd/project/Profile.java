package com.example.kd.project;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    TitleFragment fragment1=new TitleFragment();
    ImageView imgView;
    TextView textView;
/*
    @Override
    protected void onStop() {
        super.onStop();
        textView.setText("");
        Picasso.with(this).load(R.drawable.doshik).into(imgView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String id = getIntent().getStringExtra("id");
        if(id == null) id = "259211402";
        Client.getInstance().setProfile(textView, imgView, id);
        imgView.setOnLongClickListener(new View.OnLongClickListener() {
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
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment1).commit();
        imgView = (ImageView)findViewById(R.id.photoprofile);
        textView = findViewById(R.id.usernameMMR);
        Log.d("asd", "asd");
        String id = getIntent().getStringExtra("id");
        if(id == null) id = "259211402";
        Client.getInstance().setProfile(textView, imgView, id);
        imgView.setOnLongClickListener(new View.OnLongClickListener() {
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
    }
}
