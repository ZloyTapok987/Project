package com.example.kd.project;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
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
        ImageView imgView = (ImageView)findViewById(R.id.photoprofile);
        TextView textView1 = findViewById(R.id.usernameMMR);
        TextView textView2 = findViewById(R.id.Position);
        Log.d("asd", "asd");
        String id1 = getIntent().getStringExtra("id");
        if(id1 == null) id1 = "259211402";
        final String id = id1;
        //Client.getInstance().setProfile(textView1, textView2, imgView, id);
        imgView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/id" + id)));
                return true;
            }
        });
    }
}
