package com.xbc.xframe.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbc.lib.common.util.DeviceUtil;
import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseFragment;
import com.xbc.xframe.util.ButtonStateBuilder;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestShapeSelectorFragment extends BaseFragment {

    @BindView(R.id.tv_shape_selector)
    TextView mTvShapeSelector;
    @BindView(R.id.tv_shape_selector_xml)
    TextView mTvShapeSelectorXml;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test_shape_selector);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initArguments() {

    }

    @Override
    protected void initView() {
        int strokeWidth = 1; //dp 边框宽度
        int roundRadius = 8; //dp 圆角半径
        new ButtonStateBuilder().setView(mTvShapeSelector)
//                .setNormal(Color.YELLOW,10, Color.RED,4)
//                .setPressed(Color.CYAN,10, Color.CYAN,4)
                .setCornerRadius(DeviceUtil.dp2px(mContext, roundRadius))
                .setStrokeWidth(DeviceUtil.dp2px(mContext, strokeWidth))
                .setNormal(Color.YELLOW, Color.RED)
                .setPressed(Color.CYAN, 0)
                .setDisabled(Color.MAGENTA, 0)
                .setTextColor(Color.GREEN, Color.BLACK, Color.BLUE)
                .build();
    }

    @Override
    protected void initListener() {
        mTvShapeSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvShapeSelectorXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvShapeSelector.setEnabled(!mTvShapeSelector.isEnabled());
            }
        });

    }

    @Override
    protected void initData() {

    }

}
