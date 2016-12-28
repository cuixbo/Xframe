package com.xbc.xframe.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.xbc.xframe.app.XApplication;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class PreferenceUtil {

    private static final String PREFERENCE_FILE_NAME = "xframe";
    private static SharedPreferences mSharedPreferences;
    private static Editor mEditor;

    public static SharedPreferences getPreference() {
        if (mSharedPreferences == null) {
            mSharedPreferences = XApplication.getInstance().getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    public static Editor getEditor() {
        if (mEditor == null) {
            mEditor = getPreference().edit();
        }
        return mEditor;
    }


}
