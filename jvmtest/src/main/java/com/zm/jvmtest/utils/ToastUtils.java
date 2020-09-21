package com.zm.jvmtest.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.StringRes;


import java.lang.reflect.Field;

public class ToastUtils {

    private static Toast toast;

    private static Field sField_TN;
    private static Field sField_TN_Handler;
    private static Context sContext;

    static {
        try {
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
        } catch (Exception e) {
        }
    }

    public static void init(Context context) {
        sContext = context;
    }

//    public static void showLongToast(String text) {
//        showToast(text, Toast.LENGTH_LONG);
//    }
//
//    public static void showShortToast(String text) {
//        showToast(text, Toast.LENGTH_SHORT);
//    }
//
//    public static void showLongToast(@StringRes int resId) {
//        showToast(sContext.getString(resId), Toast.LENGTH_LONG);
//    }
//
//    public static void showShortToast(@StringRes int resId) {
//        showToast(sContext.getString(resId), Toast.LENGTH_SHORT);
//    }

    private static long lastShowTime;

    public static void showToast(Context context, String text, int duration) {
//        if (lastShowTime > 0 && System.currentTimeMillis() - lastShowTime < 3000) {
//            cancelToast();
//        }
//        lastShowTime = System.currentTimeMillis();
//        TextView tv = new TextView(sContext);
//        tv.setBackground(getToastBackground(Color.parseColor("#66000000")));
//        if (toast == null) {
//            toast = new Toast(sContext);
//            hook(toast);
//        }
//        toast.setView(tv);
//        tv.setText(text);
//        toast.setDuration(duration);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
        if(toast == null){
            toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

//    /**
//     * 在高版本的系统中, 调用{@link #showToast(String, int)}显示倒计时消息时，第一条消息可以显示，后面的消息都不显示了
//     * 频繁更新显示时用这个方法
//     *
//     * @param text 显示的字符串
//     * @param duration 显示时长
//     */
//    public static void showScheduleToast(String text, int duration) {
//        LayoutInflater inflate = (LayoutInflater) Utils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflate.inflate(R.layout.toast_view, null);
//        TextView tv = view.findViewById(R.id.tv);
//        tv.setBackground(getToastBackground(Color.parseColor("#66000000")));
//        cancelToast();
//        toast = new Toast(Utils.getContext());
//        hook(toast);
//        toast.setView(view);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        tv.setText(text);
//        toast.setDuration(duration);
//        toast.show();
//    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    /**
     * 通过hook添加保护，避免频繁显示Toast时可能报出BadTokenException问题
     *
     * @param toast
     */
    private static void hook(Toast toast) {
        try {
            Object tn = sField_TN.get(toast);
            Handler preHandler = (Handler) sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn, new SafelyHandlerWrapper(preHandler));
        } catch (Exception e) {
        }
    }

    private static Drawable getToastBackground(@ColorInt int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(15);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    private static class SafelyHandlerWrapper extends Handler {

        private Handler impl;

        public SafelyHandlerWrapper(Handler impl) {
            this.impl = impl;
        }

        @Override
        public void dispatchMessage(Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
            }
        }

        @Override
        public void handleMessage(Message msg) {
            impl.handleMessage(msg);//需要委托给原Handler执行
        }
    }
}
