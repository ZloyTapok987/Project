package com.example.kd.project;

import android.app.Application;
import android.content.Intent;
import android.util.*;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;
import com.vk.sdk.util.VKUtil;

/**
 * Created by Alex on 12.01.2018.
 */

public class VkLogin extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        android.util.Log.i("finger", VKUtil.getCertificateFingerprint(this, this.getPackageName())[0]);
    }
}
