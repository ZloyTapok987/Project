package com.example.kd.project;

import android.app.Activity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ResourceBundle;

import static com.vk.sdk.VKSdk.LoginState.LoggedIn;
import static com.vk.sdk.VKSdk.LoginState.LoggedOut;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Client.getInstance().login(res);
                goNext();
            }
            @Override
            public void onError(VKError error) {
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    void goNext()
    {
        VKManager.token = VKAccessToken.currentToken();
        Intent intent=new Intent(getBaseContext(),Photo.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(VKSdk.isLoggedIn()) goNext();
        setContentView(R.layout.activity_main);
        Button img = (Button) findViewById(R.id.vk);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKSdk.login(MainActivity.this);
            }
        });
    }
}
