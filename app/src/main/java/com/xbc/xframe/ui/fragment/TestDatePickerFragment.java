package com.xbc.xframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xbc.lib.common.util.RandomUtil;
import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseFragment;
import com.xbc.xframe.ui.widget.picker.DatePickerData;
import com.xbc.xframe.ui.widget.picker.DatePickerDialog;
import com.xbc.xframe.ui.widget.picker.TimePickerDialog;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestDatePickerFragment extends BaseFragment {

    @BindView(R.id.btn_in_dialog)
    Button mBtnInDialog;

    DatePickerData mDatePickerData = new DatePickerData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test_date_picker);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initArguments() {

    }

    @Override
    protected void initView() {

    }

    int i = 0;

    @Override
    protected void initListener() {
        i = RandomUtil.getRandom(5);
        mBtnInDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 2 == 0) {
                    TimePickerDialog dialog = new TimePickerDialog(mContext);
                    dialog.show();
                } else {
                    DatePickerDialog dialog = new DatePickerDialog(mContext);
                    dialog.setDate(2017, 2, 3);
                    dialog.setMaxYear(2017);
                    dialog.show();
                }
                i++;
            }
        });

        mBtnInDialog.performClick();
    }

    @Override
    protected void initData() {

    }


}
