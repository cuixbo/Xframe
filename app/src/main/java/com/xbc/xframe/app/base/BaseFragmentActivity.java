package com.xbc.xframe.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class BaseFragmentActivity extends FragmentActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mContext = this;
        super.onCreate(savedInstanceState);
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


}
