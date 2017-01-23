package com.xbc.xframe.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xbc.xframe.R;
import com.xbc.xframe.app.XApplication;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public class ToastUtil {

    private static  Toast CUSTOM_TOAST;

    public static void showToast(final String content) {
        XApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(XApplication.getInstance(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToast(int resId) {
        String content = XApplication.getInstance().getString(resId);
        showToast(content);
    }


    /**
     * 显示成功的自定义Toast
     * @param context
     * @param content
     */
    public static void showToastSuccess(Context context, String content){
       showToastCustom(context,R.drawable.icon_success,content);
    }

    /**
     * 显示警告/失败的自定义Toast
     * @param context
     * @param content
     */
    public static void showToastWarn(Context context, String content){
        showToastCustom(context,R.drawable.icon_warn,content);
    }

    /**
     * 自定义Toast
     * @param context
     * @param iconResId
     * @param content
     */
    public static void showToastCustom(Context context,int iconResId, String content){
        if(CUSTOM_TOAST ==null){
            CUSTOM_TOAST =new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_toast,null,false);
            new ButtonStateBuilder().setView(view)
                    .setCornerRadius(DeviceUtil.dp2px(context,12))
                    .setNormal(Color.BLACK,0)
                    .build();
            CUSTOM_TOAST.setView(view);
            CUSTOM_TOAST.setDuration(Toast.LENGTH_SHORT);
            CUSTOM_TOAST.setGravity(Gravity.CENTER,0,0);
        }
        View view= CUSTOM_TOAST.getView();
        TextView tvToast= (TextView) view.findViewById(R.id.tv_toast);
        tvToast.setCompoundDrawablesWithIntrinsicBounds(0,iconResId,0,0);
        tvToast.setText(content);
        CUSTOM_TOAST.show();
    }

}
