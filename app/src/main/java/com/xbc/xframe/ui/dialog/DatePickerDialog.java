package com.xbc.xframe.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.xbc.xframe.R;
import com.xbc.xframe.ui.widget.DatePickerData;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobo.cui on 2016/11/8.
 */

public class DatePickerDialog extends Dialog {

    private Context mContext;
    @BindView(R.id.np_year)
    NumberPicker mNpYear;
    @BindView(R.id.np_month)
    NumberPicker mNpMonth;
    @BindView(R.id.np_day)
    NumberPicker mNpDay;

    DatePickerData mDatePickerData = new DatePickerData();

    public DatePickerDialog(Context context) {
        super(context, R.style.BottomDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setContentView(R.layout.dialog_bottom_date_picker);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        setWindowAttributes();
        initView();
        initListener();
    }

    /**
     * 设置Window的属性，宽高，重心等
     */
    private void setWindowAttributes() {
        Window window = getWindow();
//      window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;
    }

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

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
