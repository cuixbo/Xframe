package com.xbc.xframe.net;

/**
 * Created by xiaobo.cui on 2016/9/28.
 */
public abstract class Callback<T> {

    public void onPreCall() {

    }

    public abstract void onSuccess(T response);

    public void onFailure(Throwable e, T response) {

    }

    public void onEndCall() {

    }
}
