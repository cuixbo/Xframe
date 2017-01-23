package com.xbc.xframe.app.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public abstract class BaseActivity extends Activity {

    protected Context mContext;
    protected Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
        this.mContext = this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    public void init(){
        initIntent();
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showLoading() {

    }

    protected void closeLoading() {

    }

    protected abstract void initIntent();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

}
