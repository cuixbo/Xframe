package com.xbc.xframe.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        preLoading();
    }

    private void initView() {

    }

    private void preLoading() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .delaySubscription(500, TimeUnit.MILLISECONDS)
                .subscribe();
    }


}
