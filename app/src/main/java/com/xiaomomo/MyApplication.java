package com.xiaomomo;

import android.app.Application;

/**
 * Created by lihuanxing on 2017/7/2.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
}
