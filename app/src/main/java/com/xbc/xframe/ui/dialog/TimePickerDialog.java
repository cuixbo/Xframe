package com.xbc.xframe.ui.dialog;

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
import com.xbc.xframe.ui.widget.TimePickerData;
import com.xbc.xframe.util.ToastUtil;

import java.lang.reflect.Field;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobo.cui on 2016/11/8.
 */

public class TimePickerDialog extends Dialog {

    private Context mContext;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    @BindView(R.id.np_hour)
    NumberPicker mNpHour;
    @BindView(R.id.np_min)
    NumberPicker mNpMin;
    @BindView(R.id.np_sign)
    NumberPicker mNpSign;
    @BindView(R.id.np_left)
    NumberPicker mNpLeft;
    @BindView(R.id.np_right)
    NumberPicker mNpRight;

    public TimePickerDialog(Context context) {
        super(context, R.style.BottomDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setContentView(R.layout.dialog_bottom_time_picker);
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
        //禁止输入
        mNpHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpMin.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpSign.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpLeft.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNpRight.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        Calendar calendar = Calendar.getInstance();
        int defaultHour = calendar.get(Calendar.HOUR_OF_DAY);
        int defaultMin = calendar.get(Calendar.MINUTE);
        int defaultSec = calendar.get(Calendar.SECOND);
        TimePickerData.HourData hourData = new TimePickerData.HourData(23, 0, defaultHour);
        mNpHour.setMaxValue(hourData.maxValue);
        mNpHour.setMinValue(hourData.minValue);
        mNpHour.setValue(hourData.defaultValue);

        TimePickerData.MinData minData = new TimePickerData.MinData(59, 0, defaultMin);
        mNpMin.setMaxValue(minData.maxValue);
        mNpMin.setMinValue(minData.minValue);
        mNpMin.setValue(minData.defaultValue);

        mNpSign.setDisplayedValues(new String[]{":"});
        mNpLeft.setDisplayedValues(new String[]{""});
        mNpRight.setDisplayedValues(new String[]{""});

//        TimePickerData.SecData secData = new TimePickerData.SecData(59, 0, defaultMin);
//        mNpSec.setMaxValue(secData.maxValue);
//        mNpSec.setMinValue(secData.minValue);
//        mNpSec.setValue(secData.defaultValue);

        setNumberPickerDividerColor(mNpHour);
        setNumberPickerDividerColor(mNpMin);
        setNumberPickerDividerColor(mNpSign);
        setNumberPickerDividerColor(mNpLeft);
        setNumberPickerDividerColor(mNpRight);

        setNumberPickerDividerHeight(mNpHour);
        setNumberPickerDividerHeight(mNpMin);
        setNumberPickerDividerHeight(mNpSign);
        setNumberPickerDividerHeight(mNpLeft);
        setNumberPickerDividerHeight(mNpRight);

        hideNumberPickerInputText(mNpHour);
        hideNumberPickerInputText(mNpMin);
        hideNumberPickerInputText(mNpSign);
        hideNumberPickerInputText(mNpLeft);
        hideNumberPickerInputText(mNpRight);
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
                String result = mNpHour.getValue() + ":" + mNpMin.getValue() + "";
                ToastUtil.showToast(result);
            }
        });


        mNpHour.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String ret = value < 10 ? "0" + value : value + "";
                return ret;
            }
        });


        mNpMin.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String ret = value < 10 ? "0" + value : value + "";
                return ret;
            }
        });

        //需要设置OnClickListener
        mNpHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNpMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNpSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNpLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNpRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNpHour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        mNpMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

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

    public TimePickerDialog setTime(int hour, int min) {
        mNpHour.setValue(hour);
        mNpMin.setValue(min);
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
