package com.xbc.xframe.util;

import android.text.TextUtils;
import android.util.Log;

import com.xbc.xframe.app.Config;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class LogUtil {
    private static final String DEFAULT_TAG = "xbc";
    private static boolean mDebug = Config.IS_DEBUG;

    public static void setDebug(boolean debug) {
        mDebug = debug;
    }

    public static void d(String info) {
        if (!mDebug) return;
        if (TextUtils.isEmpty(info)) return;
        Log.d(DEFAULT_TAG, info);
    }

    public static void d(String tag, String info) {
        if (!mDebug) return;
        if (TextUtils.isEmpty(info)) return;
        Log.d(tag, info);
    }

    public static void i(String info) {
        if (!mDebug) return;
        if (TextUtils.isEmpty(info)) return;
        Log.i(DEFAULT_TAG, info);
    }

    public static void i(String tag, String info) {
        if (!mDebug) return;
        if (TextUtils.isEmpty(info)) return;
        Log.i(tag, info);
    }


    public static void e(String info) {
        if (!mDebug) return;
        if (TextUtils.isEmpty(info)) return;
        Log.e(DEFAULT_TAG, info);
    }

    public static void e(String tag, String info) {
        if (!mDebug) return;
        if (TextUtils.isEmpty(info)) return;
        Log.e(tag, info);
    }


    /**
     * 写入日志
     *
     * @param tag
     * @param info
     */
    public static void ptf(String tag, String info) {
        if (!mDebug) return;
        if (TextUtils.isEmpty(info)) return;

    }

}
