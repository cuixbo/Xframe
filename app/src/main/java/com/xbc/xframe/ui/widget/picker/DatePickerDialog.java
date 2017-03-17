package com.xbc.xframe.ui.widget.picker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.xbc.xframe.R;

import java.lang.reflect.Field;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobo.cui on 2016/11/8.
 */

public class DatePickerDialog extends Dialog {

    private Context mContext;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
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
        //截止输入
        mNpYear.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpMonth.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpDay.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        Calendar calendar = Calendar.getInstance();
        int defaultYear = calendar.get(Calendar.YEAR);
        int defaultMonth = calendar.get(Calendar.MONTH) + 1;
        int defaultDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerData.YearData yearData = new DatePickerData.YearData(2050, 1970, defaultYear);
        mNpYear.setMaxValue(yearData.maxValue);
        mNpYear.setMinValue(yearData.minValue);
        mNpYear.setValue(yearData.defaultValue);

        DatePickerData.MonthData monthData = new DatePickerData.MonthData(12, 1, defaultMonth);
        mNpMonth.setMaxValue(monthData.maxValue);
        mNpMonth.setMinValue(monthData.minValue);
        mNpMonth.setValue(monthData.defaultValue);

        DatePickerData.DayData dayData = new DatePickerData.DayData(31, 1, defaultDay);
        mNpDay.setMaxValue(dayData.maxValue);
        mNpDay.setMinValue(dayData.minValue);
        mNpDay.setValue(dayData.defaultValue);

        setNumberPickerDividerColor(mNpYear);
        setNumberPickerDividerColor(mNpMonth);
        setNumberPickerDividerColor(mNpDay);

        setNumberPickerDividerHeight(mNpYear);
        setNumberPickerDividerHeight(mNpMonth);
        setNumberPickerDividerHeight(mNpDay);

        hideNumberPickerInputText(mNpYear);
        hideNumberPickerInputText(mNpMonth);
        hideNumberPickerInputText(mNpDay);
    }


    protected void initListener() {

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                String result = mNpYear.getValue() + "年" + mNpMonth.getValue() + "月" + mNpDay.getValue() + "日";
                ToastUtil.showToast(result);
            }
        });


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

        //需要设置OnClickListener
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
//                Log.e("xbc", "old:" + oldVal + ",new:" + newVal);
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
//                Log.e("xbc", "old:" + oldVal + ",new:" + newVal);
                DatePickerData.DateData data = mDatePickerData.getData(mNpYear.getValue(), newVal, mNpDay.getValue());
                mNpYear.setValue(data.year);
                mNpMonth.setValue(data.month);
                mNpDay.setMaxValue(data.maxDay);
                mNpDay.setValue(data.day);
            }
        });
    }


    /**
     * 设置分割线颜色
     *
     * @param numberPicker
     */
    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        try {
            NumberPicker picker = numberPicker;
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(Color.LTGRAY));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置分割线颜色
     *
     * @param numberPicker
     */
    private void setNumberPickerDividerHeight(NumberPicker numberPicker) {
        try {
            NumberPicker picker = numberPicker;
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDividerHeight")) {
                    pf.setAccessible(true);
                    //设置分割线的颜色值
                    pf.set(picker, 1);
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

    public DatePickerDialog setDate(int year, int month, int day) {
        mNpYear.setValue(year);
        mNpMonth.setValue(month);
        mNpDay.setValue(day);
        return this;
    }

    public DatePickerDialog setMaxYear(int value) {
        mNpYear.setMaxValue(value);
        return this;
    }

    public DatePickerDialog setMinYear(int value) {
        mNpYear.setMinValue(value);
        return this;
    }

    public DatePickerDialog setYearWrap(boolean wrap) {
        mNpYear.setWrapSelectorWheel(wrap);
        return this;
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
