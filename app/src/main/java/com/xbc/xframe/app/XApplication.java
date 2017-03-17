package com.xbc.xframe.app;

import android.text.TextUtils;

import com.xbc.xframe.app.base.BaseApplication;
import com.xbc.xframe.db.DBManager;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class XApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //当前进程名与包名相同才认为是app启动；
        if (TextUtils.equals(getPackageName(), AppUtil.getCurProcessName())) {
            onAppStart();
        }
    }


    /**
     * 用户登录成功后行为:用户主动登录、自动登录
     */
    public void onUserLogin() {

    }

    /**
     * 用户登出成功后行为
     */
    public void onUserLogout() {

    }

    /**
     * 用户启动APP后的行为
     */
    public void onAppStart() {
        CrashHandler.getInstance().init(getInstance());
        DBManager.getInstance().init(getInstance(), "1");
    }

    /**
     * 用户退出APP后行为
     */
    public void onAppExit() {

    }

    /**
     * 初始化全局资源
     */
    public void initGlobalResource() {

    }

    /**
     * 初始化用户资源
     */
    public void initUserResource() {

    }

}
