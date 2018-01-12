package com.example.kd.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ImageView p1,p2;
        p1=findViewById(R.id.photo1);
        p2=findViewById(R.id.photo2);
        p1.
    }
    protected void click1(View view)
    {
        Intent intent=new Intent(view.getContext(),MainActivity.class);
        startActivity(intent);
    }
    protected void click2(View view)
    {
        Intent intent=new Intent(view.getContext(),MainActivity.class);
        startActivity(intent);
    }
}
