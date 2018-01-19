package com.example.kd.project;

import android.app.Activity;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex on 14.01.2018.
 */

public class Client {
    private final String IP = "10.23.45.126";
    private static Client client = null;
    private Client() {

    }

    static Client getInstance()
    {
        if(client == null) client = new Client();
        return client;
    }

    public void setProfile(TextView username, TextView MMR, TextView pos, ImageView img,String id)
    {
        new SetProfileClass(username, MMR, pos, img,id).start();
    }

    class SetProfileClass extends Thread
    {
        ImageView img;
        TextView view1, view2, view3;
        String id;

        public SetProfileClass(TextView view1,TextView view2, TextView view3, ImageView img, String id) {
            this.view1 = view1;
            this.view2 = view2;
            this.view3 = view3;
            this.id = id;
            this.img = img;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket(IP, 8989);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                Scanner sc = new Scanner(socket.getInputStream());
                pw.print(VKManager.token.userId + " getMMR " + id + " "); pw.flush();
                ArrayList<User> u = new ArrayList<>(1);
                User user = new User(); user.MMR(sc.next()); user.Id(id);
                u.add(user);
                VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, id));

                socket.close();
                socket = new Socket(IP, 8989);
                pw = new PrintWriter(socket.getOutputStream());
                sc = new Scanner(socket.getInputStream());
                pw.print(VKManager.token.userId + " getPlace " + id + " "); pw.flush();

                request.executeWithListener(new UsernameDownloader(u, view1, view2, view3, sc.next()));
                VKManager.setPhotoByUserId(view1.getContext(), id, img, 3, null, null);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTable(MyAdapter adapter)
    {
        new SetTableClass().execute(adapter);
    }

    class SetTableClass extends AsyncTask<MyAdapter,String, ArrayList<User>>
    {
        @Override
        protected ArrayList<User> doInBackground(MyAdapter... voids) {
            try {
                Socket socket = new Socket(IP, 8989);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                Scanner sc = new Scanner(socket.getInputStream());
                pw.print(VKManager.token.userId + " getTop "); pw.flush();
                int n = sc.nextInt();
                ArrayList<User> users = new ArrayList<>(n);
                StringBuilder ids = new StringBuilder();
                for(int i = 0;i<n;i++)
                {
                    User e = new User();
                    e.Id(sc.next()); e.MMR(sc.next());
                    users.add(e);
                    if(i!=0) ids.append(",").append(e.getId());
                    else ids.append(e.getId());
                }
                VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, ids.toString()));
                request.executeWithListener(new UsernameDownloader(users, voids[0]));
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
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
                pw.print(VKManager.token.userId + " results " + id1 + " " + id2 + " " + winner + " "); pw.flush();
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

        @Override
        public void run() {
            try {
                Socket socket = new Socket(IP, 8989);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                Scanner sc = new Scanner(socket.getInputStream());
                pw.print(VKManager.token.userId + " getIDs "); pw.flush();
                String[] ans = new String[2];
                Photo.id1 = ans[0] = sc.next();
                Photo.id2 = ans[1] = sc.next();
                VKManager.setPhotoByUserId(activity, ans[0], v1, 3,null,null);
                VKManager.setPhotoByUserId(activity, ans[1], v2, 3,null,null);
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
