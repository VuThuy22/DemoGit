package com.example.elcomplus.demo_repreferences;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class MyApp extends Application{
    public static  Context context;
    @Override
    public void onCreate() {
        context=this;
        super.onCreate();
    }
}
