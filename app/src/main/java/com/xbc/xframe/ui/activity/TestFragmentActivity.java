package com.xbc.xframe.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;
import com.xbc.xframe.ui.fragment.TestDatePickerFragment;
import com.xbc.xframe.ui.fragment.TestLiteOrmFragment;
import com.xbc.xframe.ui.fragment.TestShapeSelectorFragment;
import com.xbc.xframe.ui.fragment.TestToastFragment;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestFragmentActivity extends BaseActivity {

    @BindView(R.id.fl_fragment_container)
    FrameLayout mFlFragmentContainer;

    int mTestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
    }

    @Override
    protected void initIntent() {
        mTestId = getIntent().getIntExtra("testId", 0);
    }

    @Override
    protected void initView() {
        if (savedInstanceState == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = null;
            switch (mTestId) {
                case 3:
                    fragment = new TestDatePickerFragment();
                    break;
                case 2:
                    fragment = new TestToastFragment();
                    break;
                case 1:
                    fragment = new TestShapeSelectorFragment();
                    break;
                case 0:
                    fragment = new TestLiteOrmFragment();
                    break;
                default:
                    break;
            }
            ft.add(R.id.fl_fragment_container, fragment, fragment.getClass().getSimpleName());
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
