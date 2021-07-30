package cn.example.baselib.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * Created by lee on 2015/11/13.
 */
public class ScreenUtil {

    public static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int pxToDip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        } else {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(resourceId);
        }
    }

    public static void setActivityFullScreen(@NonNull Activity activity) {
        if (couldFullScreen()) {
            View decorView = activity.getWindow().getDecorView();
            int option = 9472;
            decorView.setSystemUiVisibility(option);
            if (Build.VERSION.SDK_INT >= 23) {
                activity.getWindow().setStatusBarColor(0);
            } else {
                activity.getWindow().setStatusBarColor(1140850688);
            }
        }

    }

    public static boolean couldFullScreen() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }

        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            if (systemPropertiesClass == null) {
                return false;
            }

            Method m = systemPropertiesClass.getMethod("get", String.class);
            if (m == null) {
                return false;
            }

            String navBarOverride = (String)m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception var7) {
        }

        return hasNavigationBar;
    }
}
