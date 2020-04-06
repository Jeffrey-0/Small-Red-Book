package com.example.smallredbook.z_no;

import android.app.Application;
import android.content.Context;

//import com.zhy.http.okhttp.OkHttpUtils;


//import okhttp3.OkHttpClient;


public class MyApplication extends Application {

    public static Context getmContext() {
        return mContext;
    }

    private static Context mContext;
    @Override
    public void onCreate() {

        super.onCreate();
        mContext=this;
//        okhttp3.OkHttpClient okHttpClient;
//        OkHttpUtils.initClient(okHttpClient);
        initOkHttpClient();
    }

    private void initOkHttpClient() {
      //  OkHttpClient okHttpClient=new OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS)
        //        .readTimeout(10000L,TimeUnit.MILLISECONDS)
      //          .build();
       // OkHttpUtils.initClient(okHttpClient);
    }
}
