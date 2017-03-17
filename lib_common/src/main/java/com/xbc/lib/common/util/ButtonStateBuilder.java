package com.xbc.lib.common.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.TextView;

/**
 * 给TextView,Button修改背景及文字颜色;
 * 也可以给ViewGroup修改背景;
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

    /**
     * 设置需要改变的TextView,Button
     */
    public ButtonStateBuilder setView(View view) {
        this.mView = view;
        return this;
    }

    /**
     * 设置矩形圆角半径,多状态共用
     *
     * @param cornerRadius 单位px
     * @return
     */
    public ButtonStateBuilder setCornerRadius(int cornerRadius) {
        this.mCornerRadius = cornerRadius;
        mNormalDrawable.setCornerRadius(cornerRadius);
        mPressedDrawable.setCornerRadius(cornerRadius);
        mDisabledDrawable.setCornerRadius(cornerRadius);
        return this;
    }

    /**
     * 设置描边线框宽度,多状态共用
     *
     * @param strokeWidth 单位px
     * @return
     */
    public ButtonStateBuilder setStrokeWidth(int strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        mNormalDrawable.setStroke(mStrokeWidth, mNormalStrokeColor);
        mPressedDrawable.setStroke(mStrokeWidth, mPressedStrokeColor);
        mDisabledDrawable.setStroke(mStrokeWidth, mDisabledStrokeColor);
        return this;
    }

    /**
     * 设置正常状态下的填充颜色,描边颜色,没有可以设0
     *
     * @param solidColor
     * @param strokeColor
     * @return
     */
    public ButtonStateBuilder setNormal(int solidColor, int strokeColor) {
        mNormalDrawable.setColor(solidColor);
        mNormalStrokeColor = strokeColor;
        mNormalDrawable.setStroke(mStrokeWidth, mNormalStrokeColor);
        return this;
    }

    /**
     * 设置按下状态下的填充颜色,描边颜色,没有可以设0
     *
     * @param solidColor
     * @param strokeColor
     * @return
     */
    public ButtonStateBuilder setPressed(int solidColor, int strokeColor) {
        mPressedDrawable.setColor(solidColor);
        mPressedStrokeColor = strokeColor;
        mPressedDrawable.setStroke(mStrokeWidth, mPressedStrokeColor);
        return this;
    }

    /**
     * 设置禁用状态下的填充颜色,描边颜色,没有可以设0
     *
     * @param solidColor
     * @param strokeColor
     * @return
     */
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

    /**
     * 设置文字在不同状态下的颜色
     *
     * @param normalColor
     * @param pressedColor
     * @param disabledColor
     * @return
     */
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

    /**
     * 为TextView构建背景及文字颜色
     */
    public ButtonStateBuilder build() {
        if (mView != null) {
            mStateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, mDisabledDrawable);
            mStateListDrawable.addState(new int[]{android.R.attr.state_pressed}, mPressedDrawable);
            mStateListDrawable.addState(new int[]{}, mNormalDrawable);
            mView.setBackgroundDrawable(mStateListDrawable);
            if (mColorStateList != null && mView instanceof TextView) {
                TextView castTextView = ((TextView) mView);
                castTextView.setTextColor(mColorStateList);
            }
        }
        return this;
    }
}
