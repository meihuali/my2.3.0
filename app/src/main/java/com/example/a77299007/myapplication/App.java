package com.example.a77299007.myapplication;

import android.app.Application;

public class App extends Application {

    private static App sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getsInstance() {
        return sInstance;
    }
}
