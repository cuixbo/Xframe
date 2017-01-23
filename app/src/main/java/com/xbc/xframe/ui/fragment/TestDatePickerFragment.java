package com.xbc.xframe.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseFragment;
import com.xbc.xframe.ui.dialog.DatePickerDialog;
import com.xbc.xframe.ui.widget.DatePickerData;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestDatePickerFragment extends BaseFragment {

    @BindView(R.id.np_year)
    NumberPicker mNpYear;
    @BindView(R.id.np_month)
    NumberPicker mNpMonth;
    @BindView(R.id.np_day)
    NumberPicker mNpDay;
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
        mNpYear.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpMonth.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpDay.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        DatePickerData.YearData yearData = new DatePickerData.YearData(2050, 1970, 2017);
        mNpYear.setMaxValue(yearData.maxValue);
        mNpYear.setMinValue(yearData.minValue);
        mNpYear.setValue(yearData.defaultValue);

        DatePickerData.MonthData monthData = new DatePickerData.MonthData(12, 1, 1);
        mNpMonth.setMaxValue(monthData.maxValue);
        mNpMonth.setMinValue(monthData.minValue);
        mNpMonth.setValue(monthData.defaultValue);

        DatePickerData.DayData dayData = new DatePickerData.DayData(31, 1, 1);
        mNpDay.setMaxValue(dayData.maxValue);
        mNpDay.setMinValue(dayData.minValue);
        mNpDay.setValue(dayData.defaultValue);


//        mNpDate.setWrapSelectorWheel(true);
//        mNpDate.setDividerDrawable(new ColorDrawable(Color.RED));
        setNumberPickerDividerColor(mNpYear);
        setNumberPickerDividerColor(mNpMonth);
        setNumberPickerDividerColor(mNpDay);
        hideNumberPickerInputText(mNpYear);
        hideNumberPickerInputText(mNpMonth);
        hideNumberPickerInputText(mNpDay);
    }

    @Override
    protected void initListener() {
        mNpYear.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {

                return value + "年";
            }
        });


        mNpMonth.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {

                return value + "月";
            }
        });
        mNpDay.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {

                return value + "日";
            }
        });


        mNpYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNpMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mNpDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNpYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.e("xbc", "old:" + oldVal + ",new:" + newVal);
                DatePickerData.DateData data = mDatePickerData.getData(newVal, mNpMonth.getValue(), mNpDay.getValue());
                mNpYear.setValue(data.year);
                mNpMonth.setValue(data.month);
                mNpDay.setMaxValue(data.maxDay);
                mNpDay.setValue(data.day);
            }
        });

        mNpMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.e("xbc", "old:" + oldVal + ",new:" + newVal);
                DatePickerData.DateData data = mDatePickerData.getData(mNpYear.getValue(), newVal, mNpDay.getValue());
                mNpYear.setValue(data.year);
                mNpMonth.setValue(data.month);
                mNpDay.setMaxValue(data.maxDay);
                mNpDay.setValue(data.day);
            }
        });

        mBtnInDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog=new DatePickerDialog(mContext);
                dialog.show();
            }
        });

    }

    @Override
    protected void initData() {

    }


    public void showCustomToast() {

    }


    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        try {
            NumberPicker picker = numberPicker;
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(Color.TRANSPARENT));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修复NumberPicker.setValue()在setFormatter()后第一次无效或者点击后无效的bug
     * 需要将InputText设置成不可见,这样在修复NumberPicker的OnDraw中才会绘制中间部分
     * 由于performClick()方法中调用了showSoftInput()方法,showSoftInput()中又将InputText设置成可见
     * 所以还需要配合设置NumberPicker.setOnClickListener(new View.OnClickListener(){...});
     *
     * @param numberPicker
     */
    private void hideNumberPickerInputText(NumberPicker numberPicker) {
        try {
            NumberPicker picker = numberPicker;
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mInputText")) {
                    pf.setAccessible(true);
                    //设置分割线的颜色值
                    EditText inputText = (EditText) pf.get(numberPicker);
                    inputText.setVisibility(View.INVISIBLE);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
