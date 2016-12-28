package com.xbc.xframe.app.base;

import android.app.Application;
import android.os.Handler;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class BaseApplication extends Application {

    private static final Handler mHandler = new Handler();
    protected static Application instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Application getInstance() {
        return instance;
    }
}
