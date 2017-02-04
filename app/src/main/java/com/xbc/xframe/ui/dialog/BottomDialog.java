package com.xbc.xframe.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xbc.xframe.R;

/**
 * Created by xiaobo.cui on 2016/11/8.
 */

public class BottomDialog extends Dialog {
    private Context mContext;
    private LinearLayout llItemContainer;
    private TextView tvCancel;
    private float mDefaultDescTextSize = 14;//sp
    private float mDefaultItemTextSize = 17;//sp
    private int mDefaultDescHeight = 64;//dp
    private int mDefaultItemHeight = 52;//dp
    private int mDefaultDescTextColor;
    private int mDefaultItemTextColor;
    private int mHighlightItemTextColor;

    public BottomDialog(Context context) {
        super(context, R.style.BottomDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setContentView(R.layout.dialog_bottom_sample);
        setCanceledOnTouchOutside(true);
        setWindowAttributes();
        initView();
        initListener();
        mDefaultDescTextColor = mContext.getResources().getColor(R.color.colorTextMediumGray);
        mDefaultItemTextColor = mContext.getResources().getColor(R.color.colorTextDeepGray);
        mHighlightItemTextColor = mContext.getResources().getColor(R.color.colorTextHighlightRed);
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

    private void initView() {
        llItemContainer = (LinearLayout) findViewById(R.id.ll_item_container);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        llItemContainer.removeAllViews();
    }

    private void initListener() {
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 描述
     */
    public BottomDialog setDescription(String desc) {
        setDescription(desc, mDefaultDescHeight, mDefaultDescTextSize, mDefaultDescTextColor);
        return this;
    }

    public BottomDialog setDescription(String desc, int height, float textSize, int textColor) {
        TextView item = (TextView) getLayoutInflater().inflate(R.layout.item_dialog_tv, llItemContainer, false);
        item.setText(desc);
        item.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, mContext.getResources().getDisplayMetrics()));
        item.setTextSize(textSize);
        item.setTextColor(textColor);
        llItemContainer.addView(item);
        return this;
    }

    public BottomDialog addItem(String text) {
        addItem(text, mDefaultItemHeight, mDefaultItemTextSize, mDefaultItemTextColor, null);
        return this;
    }


    /**
     * 普通item
     */
    public BottomDialog addItem(String text, View.OnClickListener listener) {
        addItem(text, mDefaultItemHeight, mDefaultItemTextSize, mDefaultItemTextColor, listener);
        return this;
    }

    /**
     * 高亮item
     */
    public BottomDialog addItemHighlight(String text, View.OnClickListener listener) {
        addItem(text, mDefaultItemHeight, mDefaultItemTextSize, mHighlightItemTextColor, listener);
        return this;
    }

    public BottomDialog addItem(String desc, int height, float textSize, int textColor, final View.OnClickListener listener) {
        TextView item = (TextView) getLayoutInflater().inflate(R.layout.item_dialog_tv, llItemContainer, false);
        item.setText(desc);
        item.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, mContext.getResources().getDisplayMetrics()));
        item.setTextSize(textSize);
        item.setTextColor(textColor);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dismiss();
            }
        });
        llItemContainer.addView(item);
        return this;
    }

    public BottomDialog setHighlightColor(int textColorRes) {
        this.mHighlightItemTextColor = mContext.getResources().getColor(textColorRes);
        return this;
    }

    public BottomDialog setHighlightColor(String textColorString) {
        this.mHighlightItemTextColor = Color.parseColor(textColorString);
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
