package com.example.kd.project;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Rating extends AppCompatActivity {
    ArrayList<User> users= new ArrayList<>();
    TitleFragment fragment1=new TitleFragment();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentrating,fragment1).commit();
        fillData();
        c=this;
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        MyAdapter adapter = new MyAdapter(users);
        rv.setAdapter(adapter);

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

    private void fillData() {
        for(int i=0;i<10;++i)
        {
            User user=new User();
            user.Id("305663627");
            user.UserName("SanyaBog");
            user.MMR("100500");
            users.add(user);
        }
    }
}
