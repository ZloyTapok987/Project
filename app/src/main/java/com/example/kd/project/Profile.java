package com.example.kd.project;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TitleFragment fragment1=new TitleFragment();
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,fragment1).commit();
        ImageView imgView =(ImageView)findViewById(R.id.photoprofile);
        new DownloadImageTask((ImageView)findViewById(R.id.photoprofile)).execute("https://pp.userapi.com/c639919/v639919222/2ff90/y5ZH9G7Hag0.jpg");
        ImageView p1=findViewById(R.id.photoprofile);
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
        c=this;
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
