package com.xbc.xframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbc.lib.common.util.ToastUtil;
import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestToastFragment extends BaseFragment {

    @BindView(R.id.tv_test_toast)
    TextView mTvTestToast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test_toast);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initArguments() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        mTvTestToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast();
            }
        });
    }

    @Override
    protected void initData() {

    }

    int i = 0;

    public void showCustomToast() {
        i++;
        if (i % 2 == 0) {
            ToastUtil.showToastSuccess(mContext, "已收藏");
        } else {
            ToastUtil.showToastWarn(mContext, "已取消收藏");
        }
    }

}
