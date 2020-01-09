package com.beacool.bsmapviewlib.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beacool.bsmapviewlib.ViewUtil;
import com.beacool.bsmapviewlib.model.BSPositionPoint;

/**
 * 自定义 地图 坐标 点
 */
public class BSMapPointView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "BSMapPointView";

    private ImageView mPointIcon;
    private Bitmap mPointIconBitmap;

    // 初始 位置
    private double firstX;
    private double firstY;

    /**
     * 代表添加的图标 是居中对齐 还是 居底部对齐
     */
    private int MODE_ICON_ALIGN_BOTTOM = 0;
    private int MODE_ICON_ALIGN_CENTER = 1;
    private int mIconAlignMode = MODE_ICON_ALIGN_CENTER;

    /**
     * 坐标点的信息
     */
    private BSPositionPoint mPositionPoint;

    private OnMapPointClickListener onMapPointClickListener;

    public BSMapPointView(Context context) {
        super(context);
    }

    public BSMapPointView(Context context, double pointX, double pointY, float scale, String pathName) {
        super(context);
        firstX = pointX;
        firstY = pointY;

        mPointIconBitmap = createPointIconBitmap(scale, pathName);
        init();
    }


    public BSMapPointView(Context context, double pointX, double pointY, Bitmap bitmap) {
        super(context);
        firstX = pointX;
        firstY = pointY;

        mPointIconBitmap = bitmap;
        init();
    }


    public void setOnMapPointClickListener(OnMapPointClickListener onMapPointClickListener) {
        this.onMapPointClickListener = onMapPointClickListener;
    }

    public void setFirstXShow(float x) {
        x = x - mPointIconBitmap.getWidth() / 2.f;
        setX(x);
    }

    public void setFirstYShow(float y) {
        /**
         * 设置是以底部还是中心居齐
         */
        if (mIconAlignMode == MODE_ICON_ALIGN_CENTER) {
            y = y - mPointIconBitmap.getHeight() / 2.f;
        } else {
            y = y - mPointIconBitmap.getHeight();
        }

        setY(y);
    }

    public void moveWithAnimator(float lastX, float curX, float lastY, float curY) {
        curX = curX - mPointIconBitmap.getWidth() / 2.f;

        if (mIconAlignMode == MODE_ICON_ALIGN_CENTER) {
            curY = curY - mPointIconBitmap.getHeight() / 2.f;
        } else {
            curY = curY - mPointIconBitmap.getHeight();
        }

//        final float x = curX;
//        final float y = curY;
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "translationX", lastX, curX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "translationY", lastY, curY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);

//        animatorSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                setX(x);
//                setY(y);
//            }
//        });

        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();
    }

    /**
     * 设置添加的点 适配的位置 居中还是底部
     *
     * @param iconAlignMode
     */
    public void setIconAlignMode(int iconAlignMode) {
        this.mIconAlignMode = iconAlignMode;
    }


    public void setFirstX(double firstX) {
        this.firstX = firstX;
    }

    public void setFirstY(double firstY) {
        this.firstY = firstY;
    }

    public void init() {
        mPointIcon = new ImageView(getContext());
        mPointIcon.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        setOrientation(VERTICAL);
        addView(mPointIcon);

        mPointIcon.setImageBitmap(mPointIconBitmap);

        // 设置 监听
        setOnClickListener(this);
    }


    /**
     * @param
     */
    public void setPointIcon(float scale, String pathName) {
        mPointIconBitmap = createPointIconBitmap(scale, pathName);
        mPointIcon.setImageBitmap(mPointIconBitmap);
    }


    /**
     * 获取 assets 目录下的图片
     *
     * @param scale
     * @param pathName
     * @return
     */
    private Bitmap createPointIconBitmap(float scale, String pathName) {
        float mScale = ViewUtil.getScreenDensity(getContext()) * scale;
        return ViewUtil.scaleBitmap(ViewUtil.getBitmapFormAsset(getContext(), pathName), mScale);
    }

    public double getFirstX() {
        return firstX;
    }

    public double getFirstY() {
        return firstY;
    }


    public BSPositionPoint getPositionPoint() {
        return mPositionPoint;
    }

    public void setPositionPoint(BSPositionPoint mPositionPoint) {
        this.mPositionPoint = mPositionPoint;
    }

    @Override
    public void onClick(View v) {
        if (onMapPointClickListener != null) {
            onMapPointClickListener.onMapPointClick(mPositionPoint, this);
        }
    }
}
