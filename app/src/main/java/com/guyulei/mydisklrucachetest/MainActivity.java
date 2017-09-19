package com.guyulei.mydisklrucachetest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.guyulei.mydisklrucachetest.utils.CacheUtil;
import com.guyulei.mydisklrucachetest.utils.NetUtil;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.guyulei.mydisklrucachetest.utils.MD5Util.hashKeyForDisk;

public class MainActivity extends AppCompatActivity {

    private String url = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
    DiskLruCache diskCache;
    String       urlmd5key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView) findViewById(R.id.image);
        diskCache = CacheUtil.getDiskLruCache(MainActivity.this, "bitmap");
        urlmd5key = hashKeyForDisk(url);
        try {
            DiskLruCache.Snapshot snapShot = diskCache.get(urlmd5key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                image.setImageBitmap(bitmap);
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DiskLruCache.Editor edit = diskCache.edit(urlmd5key);
                            OutputStream outputStream = edit.newOutputStream(0);
                            if (NetUtil.downloadUrlToStream(url, outputStream)) {
                                edit.commit();
                            } else {
                                edit.abort();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
