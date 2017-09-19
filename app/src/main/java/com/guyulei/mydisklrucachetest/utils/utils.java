package com.guyulei.mydisklrucachetest.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.guyulei.mydisklrucachetest.MyApp;

import java.io.File;

/**
 * Created by 12539 on 2017/9/19.
 */

public class utils {


    public static Context getContext() {
        return MyApp.getContext();
    }


    public static File getDistLruCache(Context context, String fileName) {

        String cachePath;
        //当SD卡存在或者SD卡不可被移除的时候
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();// /sdcard/Android/data/<application package>/cache
        } else {
            cachePath = context.getCacheDir().getPath();///data/data/<application package>/cache
        }
        return new File(cachePath + File.separator + fileName);
    }

    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
