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
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter myAdapter;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        MainActivity.IsChoose=false;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment1).commit();
        myAdapter=new MyAdapter(this,users);
        Client.getInstance().setTable(myAdapter);
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(myAdapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(view.getContext(),Profile.class);
                intent.putExtra("id", myAdapter.users.get(i).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
