package com.xbc.xframe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.btn_test_liteorm)
    Button mBtnTestLiteOrm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        mBtnTestLiteOrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLiteOrmTest();
            }
        });
    }

    @Override
    protected void initData() {

    }

    public void doLiteOrmTest() {
        startActivity(new Intent(mContext, TestFragmentActivity.class));
    }


}
