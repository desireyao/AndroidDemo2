package com.beacool.bsmapviewlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yaoh on 2018/3/5.
 */

public class ViewUtil {

    private static final String TAG = "ViewUtil";

    // ================================= 单位转换 ======================================
    public static int dp2px(float dip, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    public static int px2dp(int px, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    public static int sp2px(float spValue, Context context) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(float pxValue, Context context) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static float getScreenDensity(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return density;
    }

    public static Bitmap getBitmapFormAsset(Context context, String path) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        try {
            InputStream is = context.getAssets().open(path);
            Bitmap bmp = BitmapFactory.decodeStream(is, null, opt);
            is.close();

            return bmp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 缩放 bitmap
     *
     * @param bitmap
     * @param scale
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, float scale) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // 取得想要缩放的matrix參數
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBitMap;
    }


    /**
     * @param context
     * @param percent
     * @return
     */
    public static int getPixelWithWidthPercent(Context context, float percent) {
        int percentWidth = (int) (ScreenUtil.getScreenWidth(context) * percent);
        return percentWidth;
    }


    /**
     * @param context
     * @param percent
     * @return
     */
    public static int getPixelWithHeightPercent(Context context, float percent) {
        int percentHeight = (int) (ScreenUtil.getScreenHeight(context) * percent);
        return percentHeight;
    }

}
