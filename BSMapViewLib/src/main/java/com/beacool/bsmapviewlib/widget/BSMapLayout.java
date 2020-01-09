package com.beacool.bsmapviewlib.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.beacool.bsmapviewlib.R;
import com.beacool.bsmapviewlib.ScreenUtil;
import com.beacool.bsmapviewlib.log.LogTool;

/**
 * Created by yaoh on 2019/1/17.
 */

public class BSMapLayout extends FrameLayout {

    private static final String TAG = "BSMapLayout";
    private OnMapViewReadyListener mOnMapViewReadyListener;

    private BSMapView mMapView;

    private int mMapViewWidth;
    private int mMapViewHeight;

    private int mScreenWidth;
    private int mScreenHeight;

    public BSMapLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initData();
        initView();
    }

    public void setOnMapViewReadyListener(OnMapViewReadyListener mOnMapViewReadyListener) {
        this.mOnMapViewReadyListener = mOnMapViewReadyListener;
    }

    private void initData() {
        mScreenWidth = ScreenUtil.getScreenWidth(getContext());
        mScreenHeight = ScreenUtil.getScreenHeight(getContext());

        mMapViewWidth = mScreenWidth + mScreenHeight;
        mMapViewHeight = mMapViewWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
//        post(new Runnable() {
//            @Override
//            public void run() {
//                LogTool.LogE_DEBUG(TAG, " onLayout --->" + " getLeft(): " + getLeft() + " getTop(): " + getTop());
//                addMapView();
//            }
//        });

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                LogTool.LogE_DEBUG(TAG, " onLayout --->111" + " getLeft(): " + getLeft() + " getTop(): " + getTop());
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                addMapView();

                if (mOnMapViewReadyListener != null) {
                    mOnMapViewReadyListener.onMapViewReady();
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogTool.LogE_DEBUG(TAG, " onLayout --->22222" + " getLeft(): " + getLeft() + " getTop(): " + getTop());
    }

    public void addMapView() {
        mMapView = new BSMapView(getContext());
        FrameLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.width = mMapViewWidth;
        layoutParams.height = mMapViewHeight;

        int leftMargin = (layoutParams.width - mScreenWidth) / 2;
        int topMargin = (layoutParams.height - mScreenHeight) / 2;
        layoutParams.leftMargin = -leftMargin;
        layoutParams.topMargin = -topMargin;
        mMapView.setLayoutParams(layoutParams);

        addView(mMapView);
    }

    public void addImageView() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(layoutParams);
//        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.ic_launcher);
        mMapView.addView(imageView);
    }

    public BSMapView getMapView() {
        return mMapView;
    }
}
