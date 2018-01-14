package com.example.kd.project;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.util.VKUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

/**
 * Created by Alex on 14.01.2018.
 */

public class VKManager extends Application {
    private static Bitmap cutImage(Bitmap b, int x, int y, int x1, int y1)
    {
        Bitmap bmp2 = b;
        Bitmap bmOverlay = Bitmap.createBitmap(bmp2, x, y, x1-x, y1-y, null, false);
        return bmOverlay;
    }

    static class Request extends VKRequest.VKRequestListener
    {
        Context context;
        ImageView w;
        Request(Context context, ImageView w) {
            this.context = context;
            this.w = w;
        }
        @Override
        public void onComplete(VKResponse response) {
            try {
                JSONObject obj = response.json
                        .getJSONArray("response")
                        .getJSONObject(0)
                        .getJSONObject("crop_photo");

                final VKApiPhoto p = new VKApiPhoto(obj.getJSONObject("photo"));

                JSONObject object = obj.getJSONObject("crop");
                final int x = object.getInt("x");
                final int y = object.getInt("y");
                final int x1 = object.getInt("x2");
                final int y1 = object.getInt("y2");

                Bitmap bitmap = new Downloader().execute(p.photo_2560).get(); //Picasso.with(context).load(p.photo_2560).get();

                bitmap = cutImage(bitmap, bitmap.getWidth() / 100 * x,
                        bitmap.getHeight() / 100 * y,
                        bitmap.getWidth() / 100 * x1,
                        bitmap.getHeight() / 100 * y1);
                w.setImageBitmap(bitmap);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void setPhotoByUserId(Context context, int id, ImageView imgView)
    {
        VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, "" + id,VKApiConst.FIELDS, "crop_photo"));
        Bitmap bitmap = null;
        Request t = new Request(context, imgView);
        request.executeWithListener(t);
    }

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                Intent intent = new Intent(VKManager.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
    }
}
