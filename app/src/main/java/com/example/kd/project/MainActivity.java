package com.example.kd.project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import static com.vk.sdk.VKSdk.LoginState.LoggedIn;
import static com.vk.sdk.VKSdk.LoginState.LoggedOut;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                goNext();
            }
            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization/
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    void goNext()
    {
        Intent intent=new Intent(getBaseContext(),Photo.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!VKSdk.isLoggedIn()) VKSdk.login(MainActivity.this);
        else goNext();
    }
}
