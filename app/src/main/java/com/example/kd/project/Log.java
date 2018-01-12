package com.example.kd.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Log extends AppCompatActivity {
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Button b=(Button)findViewById(R.id.reg);
        final Intent intent = new Intent(this,MenuVertical.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //.setText("");
                startActivity(intent);
            }
        });
    }
}
