package com.example.tanmay.shell;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Tanmay on 04-12-2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(getApplicationContext());
    }
}
