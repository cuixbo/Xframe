package com.xbc.xframe.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;
import com.xbc.xframe.ui.fragment.TestLiteOrmFragment;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestFragmentActivity extends BaseActivity {

    @BindView(R.id.fl_fragment_container)
    FrameLayout mFlFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_fragment_container, new TestLiteOrmFragment(), "TestLiteOrmFragment");
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
