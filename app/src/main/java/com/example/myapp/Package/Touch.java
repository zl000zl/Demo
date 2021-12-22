package com.example.myapp.Package;

import android.app.Application;

public class Touch extends Application {
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time-lastClickTime;

        if (0<timeD && timeD<800){
            return true;
        }
        lastClickTime = time;
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
