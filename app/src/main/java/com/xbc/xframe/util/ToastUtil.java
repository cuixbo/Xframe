package com.xbc.xframe.util;

import android.widget.Toast;

import com.xbc.xframe.app.XApplication;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class ToastUtil {

    public static void showToast(final String content) {
        XApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(XApplication.getInstance(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToast(int resId) {
        String content = XApplication.getInstance().getString(resId);
        showToast(content);
    }

}
