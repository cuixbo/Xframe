package com.xbc.xframe.app;

import java.util.Timer;

/**
 * Created by xiaobo.cui on 2016/9/28.
 */
public class GlobalResManager {
    public static GlobalResManager INSTANCE = new GlobalResManager();

    private Timer timer;


    private GlobalResManager() {

    }

    public static GlobalResManager getInstance() {
        return INSTANCE;
    }

    public Timer getGlobalTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        return timer;
    }


}
