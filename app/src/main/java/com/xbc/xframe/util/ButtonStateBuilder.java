package com.xbc.xframe.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xiaobo.cui on 2017/1/17.
 */


public class ButtonStateBuilder {
    View mView;
    StateListDrawable mStateListDrawable = new StateListDrawable();
    GradientDrawable mNormalDrawable = new GradientDrawable();
    GradientDrawable mPressedDrawable = new GradientDrawable();
    GradientDrawable mDisabledDrawable = new GradientDrawable();
    ColorStateList mColorStateList;

    int mNormalStrokeColor;
    int mPressedStrokeColor;
    int mDisabledStrokeColor;

    int mCornerRadius;
    int mStrokeWidth;

    int mNormalTextColor;
    int mPressedTextColor;
    int mDisabledTextColor;

    public ButtonStateBuilder setView(View view) {
        this.mView = view;
        return this;
    }

    public ButtonStateBuilder setCornerRadius(int cornerRadius) {
        this.mCornerRadius = cornerRadius;
        mNormalDrawable.setCornerRadius(cornerRadius);
        mPressedDrawable.setCornerRadius(cornerRadius);
        mDisabledDrawable.setCornerRadius(cornerRadius);
        return this;
    }

    public ButtonStateBuilder setStrokeWidth(int strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        mNormalDrawable.setStroke(mStrokeWidth, mNormalStrokeColor);
        mPressedDrawable.setStroke(mStrokeWidth, mPressedStrokeColor);
        mDisabledDrawable.setStroke(mStrokeWidth, mDisabledStrokeColor);
        return this;
    }

    public ButtonStateBuilder setNormal(int solidColor, int strokeColor) {
        mNormalDrawable.setColor(solidColor);
        mNormalStrokeColor = strokeColor;
        mNormalDrawable.setStroke(mStrokeWidth, mNormalStrokeColor);
        return this;
    }

    public ButtonStateBuilder setPressed(int solidColor, int strokeColor) {
        mPressedDrawable.setColor(solidColor);
        mPressedStrokeColor = strokeColor;
        mPressedDrawable.setStroke(mStrokeWidth, mPressedStrokeColor);
        return this;
    }

    public ButtonStateBuilder setDisabled(int solidColor, int strokeColor) {
        mDisabledDrawable.setColor(solidColor);
        mDisabledStrokeColor = strokeColor;
        mDisabledDrawable.setStroke(mStrokeWidth, mDisabledStrokeColor);
        return this;
    }

    public ButtonStateBuilder setNormal(int solidColor, int cornerRadius, int strokeColor, int strokeWidth) {
        mNormalDrawable.setColor(solidColor);
        mNormalDrawable.setCornerRadius(cornerRadius);
        mNormalDrawable.setStroke(strokeWidth, strokeColor);
        return this;
    }

    public ButtonStateBuilder setPressed(int solidColor, int cornerRadius, int strokeColor, int strokeWidth) {
        mPressedDrawable.setColor(solidColor);
        mPressedDrawable.setCornerRadius(cornerRadius);
        mPressedDrawable.setStroke(strokeWidth, strokeColor);
        return this;
    }

    public ButtonStateBuilder setDisabled(int solidColor, int cornerRadius, int strokeColor, int strokeWidth) {
        mDisabledDrawable.setColor(solidColor);
        mDisabledDrawable.setCornerRadius(cornerRadius);
        mDisabledDrawable.setStroke(strokeWidth, strokeColor);
        return this;
    }


    public ButtonStateBuilder setTextColor(int normalColor, int pressedColor, int disabledColor) {
        this.mNormalTextColor = normalColor;
        this.mPressedTextColor = pressedColor;
        this.mDisabledTextColor = disabledColor;
        int[] colors = new int[]{mDisabledTextColor, mPressedTextColor, mNormalTextColor};
        int[][] states = new int[3][];
        states[0] = new int[]{-android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_pressed};
        states[2] = new int[]{};
        mColorStateList = new ColorStateList(states, colors);
        return this;
    }

    public ButtonStateBuilder build() {
        mStateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, mDisabledDrawable);
        mStateListDrawable.addState(new int[]{android.R.attr.state_pressed}, mPressedDrawable);
        mStateListDrawable.addState(new int[]{}, mNormalDrawable);
        mView.setBackgroundDrawable(mStateListDrawable);
        if (mColorStateList != null && mView instanceof TextView) {
            TextView castTextView = ((TextView) mView);
            castTextView.setTextColor(mColorStateList);
        }
        return this;
    }
}
