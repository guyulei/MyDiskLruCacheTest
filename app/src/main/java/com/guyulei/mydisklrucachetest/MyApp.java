package com.guyulei.mydisklrucachetest;

import android.app.Application;
import android.content.Context;

/**
 * Created by 12539 on 2017/9/19.
 */

public class MyApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
