package com.xbc.xframe.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.xbc.xframe.app.XApplication;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class AppUtil {


    /**
     * 获取APP名称
     *
     * @return
     */
    public static String getAppName() {
        String applicationName = null;
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = XApplication.getInstance().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            applicationName = packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return applicationName;
    }


    /**
     * 获取应用的包名
     *
     * @return
     */
    public static String getPackageName() {
        return XApplication.getInstance().getPackageName();
    }

    /**
     * 获取APP的版本号
     *
     * @return
     */
    public static String getAppVersionName() {
        String versionName = null;
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            versionName = packageInfo.versionName;
        }
        return versionName;
    }


    /**
     * 获取APP的版本Code
     *
     * @return
     */
    public static int getAppVersionCode() {
        int versionCode = 0;
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            versionCode = packageInfo.versionCode;
        }
        return versionCode;
    }

    private static PackageInfo getPackageInfo() {
        PackageManager packageManager = null;
        PackageInfo packageInfo = null;
        try {
            packageManager = XApplication.getInstance().getPackageManager();
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;

    }

    /**
     * 获取当前进程名
     *
     * @return
     */
    public static String getCurProcessName() {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) XApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


}
