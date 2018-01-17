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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment1).commit();
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

    }
}
