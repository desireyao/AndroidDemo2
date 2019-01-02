package com.beacool.BPermissonProtect.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 获得屏幕相关的辅助类
 *
 * @author zhy
 */
public class ScreenUtil {
    private ScreenUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private static int mStatusBarHeight = 0;

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度，px
     */
    public static int getStatusBarHeight(Context context) {
        Context mContext = context.getApplicationContext();
        if (mStatusBarHeight != 0) {
            return mStatusBarHeight;
        }

        int resourceId = mContext.getResources().getIdentifier("status_bar_height",
                "dimen", "android");

        if (resourceId > 0) {
            mStatusBarHeight = mContext.getResources().getDimensionPixelSize(resourceId);
            return mStatusBarHeight;
        }
        return 0;
    }

}
