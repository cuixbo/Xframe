package com.xbc.xframe.app;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final CrashHandler instance = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;//用来系统默认处理异常；

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return instance;
    }


    public void init(Context context) {
        this.mContext = context;
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtil.i("uncaughtException");
        handleUncaughtException(thread, ex);
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, ex);
        }
    }

    private void handleUncaughtException(Thread thread, final Throwable ex) {
        if (ex != null) {
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    dumpExceptionToLogFile(ex);
                }
            }).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static final String CRASH_LOG_FILE_PATH = Environment.getExternalStorageDirectory() + "/xframe_crash_log.txt";

    /**
     * 记录Crash异常至SD日志文件；
     *
     * @param ex
     */
    private void dumpExceptionToLogFile(Throwable ex) {
        if (ex == null) return;
//        LogUtil.i(Log.getStackTraceString(ex));
        try {
            File crashFile = new File(CRASH_LOG_FILE_PATH);
            if (!crashFile.exists()) {
                if (!crashFile.getParentFile().exists()) {
                    crashFile.getParentFile().mkdirs();
                }
                crashFile.createNewFile();
            }

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(crashFile, true)));
            String time = new Date().toLocaleString();
            printWriter.println(time);
            dumpDeviceInfo(printWriter);
            ex.printStackTrace(printWriter);
            printWriter.println();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 记录设备信息
     *
     * @param printWriter
     */
    private void dumpDeviceInfo(PrintWriter printWriter) {
        if (printWriter != null) {
            //品牌
            printWriter.print("Brand:");
            printWriter.print(Build.BRAND);
            printWriter.print("  ");

            //型号
            printWriter.print("Model:");
            printWriter.print(Build.MODEL);
            printWriter.print("  ");

            //系统版本号
            printWriter.print("OS Version:");
            printWriter.print(Build.VERSION.RELEASE);
            printWriter.print("  ");

            //API Level
            printWriter.print("API Level");
            printWriter.print(Build.VERSION.SDK_INT);
            printWriter.println("  ");


            //APP名称
            printWriter.print("APP Name:");
            printWriter.print(AppUtil.getAppName());
            printWriter.print("  ");

            //APP版本号
            printWriter.print("APP Version:");
            printWriter.print(AppUtil.getAppVersionName());
            printWriter.print("  ");

            //APP 包名
            printWriter.print("Package Name:");
            printWriter.print(AppUtil.getPackageName());
            printWriter.println("  ");

        }
    }
}
