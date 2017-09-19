package com.guyulei.mydisklrucachetest.utils;

import android.content.Context;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

/**
 * Created by 12539 on 2017/9/19.
 */

public class CacheUtil {

    private static DiskLruCache mDiskLruCache = null;

    public static DiskLruCache getDiskLruCache(Context context, String cacheName) {
        try {
            File cacheDir = utils.getDistLruCache(context, cacheName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, utils.getAppVersion(context), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mDiskLruCache;
    }
}
