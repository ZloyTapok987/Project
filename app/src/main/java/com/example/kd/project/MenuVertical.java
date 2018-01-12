package com.example.kd.project;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuVertical extends AppCompatActivity {

    String[] a={"Photo","Profile","Rating","Settings"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vertical);
        Button b=(Button)findViewById(R.id.rating);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //.setText("");
                Intent intent = new Intent(view.getContext(),Rating.class);
                startActivity(intent);
            }
        });
        Button a=(Button)findViewById(R.id.photo);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //.setText("");
                Intent intent = new Intent(view.getContext(),Photo.class);
                startActivity(intent);
            }
        });
        Button c=(Button)findViewById(R.id.profile);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //.setText("");
                Intent intent = new Intent(view.getContext(),Profile.class);
                startActivity(intent);
            }
        });
        Button d=(Button)findViewById(R.id.settings);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //.setText("");
                Intent intent = new Intent(view.getContext(),Settings.class);
                startActivity(intent);
            }
        });
        //MyListActivity myListActivity=new MyListActivity();
    }
}
