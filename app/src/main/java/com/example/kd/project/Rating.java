package com.example.kd.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Rating extends AppCompatActivity {
    ArrayList<User> users= new ArrayList<>();
    TitleFragment fragment1=new TitleFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentrating,fragment1).commit();
        fillData();
        MyAdapter myAdapter= new MyAdapter(this, users);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(myAdapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(view.getContext(),Profile.class);
                startActivity(intent);
            }
        });
    }

    private void fillData() {
        for(int i=0;i<10;++i)
        {
            User user=new User();
            user.url("https://pp.userapi.com/c639919/v639919222/2ff90/y5ZH9G7Hag0.jpg");
            user.UserName("SanyaBog");
            user.MMR("100500");
            users.add(user);
        }
    }
}
