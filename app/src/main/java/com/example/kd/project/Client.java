package com.example.kd.project;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.RestrictTo;
import android.util.Log;
import android.widget.ImageView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.logging.SocketHandler;

/**
 * Created by Alex on 14.01.2018.
 */

public class Client {
    private final String IP = "10.23.44.152";
    private static Client client = null;
    private Client() {

    }

    static Client getInstance()
    {
        if(client == null) client = new Client();
        return client;
    }

    public void login(VKAccessToken token) {
        LoginClass loginClass = new LoginClass(token);
        loginClass.start();
    }

    class LoginClass extends Thread
    {
        VKAccessToken token;

        public LoginClass(VKAccessToken token) {
            this.token = token;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket(IP, 8989);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.print(token.userId + " login "); pw.flush();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean selected(String id1,String id2, int winner) {
        if(id1 == null || id2 == null) return false;
        SelectedClass selectedClass = new SelectedClass(id1,id2,winner);
        selectedClass.start();
        return true;
    }

    class SelectedClass extends Thread
    {
        String id1,id2;
        int winner;

        public SelectedClass(String id1, String id2, int winner) {
            this.id1 = id1;
            this.id2 = id2;
            this.winner = winner;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket(IP, 8989);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.print(VKManager.token.userId + " results " + id1 + " " + id2 + " " + winner); pw.flush();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPhotosToCompare(Activity activity, ImageView v1, ImageView v2)
    {
        new PhotoGetter(v1,v2,activity).start();
    }

    class PhotoGetter extends Thread
    {
        ImageView v1, v2;
        Activity activity;
        String[] url;
        PhotoGetter(ImageView v1, ImageView v2, Activity activity)
        {
            this.v1 = v1;
            this.v2 = v2;
            this.activity = activity;
        }

        // TODO: comebackIt
        @Override
        public void run() {
            try {
                /*Socket socket = new Socket(IP, 8989);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                Scanner sc = new Scanner(socket.getInputStream());
                pw.print(VKManager.token.userId + " getIDs "); pw.flush();
                */String[] ans = new String[2];
                Photo.id1 = ans[0] = "259211402";//sc.next();
                Photo.id2 = ans[1] = "259211402";//sc.next();
                VKManager.setPhotoByUserId(activity, ans[0], v1, 5,null,null);
                VKManager.setPhotoByUserId(activity, ans[1], v2, 5,null,null);
                //socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
