package com.yaoh.AndroidDemo2.widgets.follow_view.mapview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.utils.log.LogTool;

/**
 * Created by yaoh on 2019/1/16.
 */

public class BSMapView extends RelativeLayout {

    private static final String TAG = "BSMapView";

    private int mViewWidth;
    private int mViewHeight;

    private boolean isNeedLayout = true;

    public BSMapView(Context context) {
        super(context);
    }

    public BSMapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initData();
        initView();
    }

    private void initData() {
//        LogTool.LogE_DEBUG(TAG, "initData -------->"
//                + " Width: " + ScreenUtil.getScreenWidth(getContext())
//                + " Height: " + ScreenUtil.getScreenHeight(getContext())
//                + "\n getWidth(): " + getWidth()
//                + "  getHeight(): " + getHeight()
//                + "\n getMeasuredWidth(): " + getMeasuredWidth()
//                + "  getMeasuredHeight(): " + getMeasuredHeight()
//                + "\n getLeft() : " + getLeft()
//                + "  getRight(): " + getRight()
//                + "  getTop(): " + getTop()
//                + "  getBottom(): " + getBottom());

        mViewWidth = ScreenUtil.getScreenWidth(getContext()) + ScreenUtil.getScreenHeight(getContext());
        mViewHeight = mViewWidth;
    }

    private void initView() {
//         setBackgroundResource(R.drawable.data_team_logo_loading_76px);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogTool.LogE_DEBUG(TAG, "onMeasure -------->");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        LogTool.LogE_DEBUG(TAG, "onMeasure --------> 111"
                + "\n Width: " + ScreenUtil.getScreenWidth(getContext())
                + "   Height: " + ScreenUtil.getScreenHeight(getContext())
                + "\n getWidth(): " + getWidth()
                + "   getHeight(): " + getHeight()
                + "\n getMeasuredWidth(): " + getMeasuredWidth()
                + "   getMeasuredHeight(): " + getMeasuredHeight()
                + "\n getLeft() : " + getLeft()
                + "   getRight(): " + getRight()
                + "   getTop(): " + getTop()
                + "   getBottom(): " + getBottom());

//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
//        int leftMargin = (mViewWidth - ScreenUtil.getScreenWidth(getContext())) / 2;
//        int topMargin = (mViewHeight - ScreenUtil.getScreenHeight(getContext())) / 2;
//        params.leftMargin = -leftMargin;
//        params.topMargin = -topMargin;
//        setMeasuredDimension(mViewWidth, mViewHeight);

//        LogTool.LogE_DEBUG(TAG, "onMeasure -------->"
//                + " params.leftMargin: " + params.leftMargin
//                + " params.topMargin: " + params.topMargin );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);



        setBackgroundResource(R.drawable.data_team_logo_loading_76px);
    }

    //    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        LogTool.LogE_DEBUG(TAG, "onLayout -------->");
////        super.onLayout(changed, l, t, r, b);
//        if (!isNeedLayout) {
//            return;
//        }
//
//        LogTool.LogE_DEBUG(TAG, "onLayout --------> 111"
//                + "\n Width: " + ScreenUtil.getScreenWidth(getContext())
//                + " Height: " + ScreenUtil.getScreenHeight(getContext())
//                + "\n getWidth(): " + getWidth()
//                + "  getHeight(): " + getHeight()
//                + "\n getMeasuredWidth(): " + getMeasuredWidth()
//                + "  getMeasuredHeight(): " + getMeasuredHeight()
//                + "\n getLeft() : " + getLeft()
//                + "  getRight(): " + getRight()
//                + "  getTop(): " + getTop()
//                + "  getBottom(): " + getBottom()
//                + " l:" + l + ",t: " + t + ",r: " + r + ",b: " + b);
//        isNeedLayout = false;
//
//        int leftMargin = (mViewWidth - ScreenUtil.getScreenWidth(getContext())) / 2;
//        int topMargin = (mViewHeight - ScreenUtil.getScreenHeight(getContext())) / 2;
//        layout(-leftMargin, -topMargin, mViewWidth - leftMargin, mViewHeight - topMargin);
//        LogTool.LogE_DEBUG(TAG, " leftMargin: " + leftMargin + " topMargin: " + topMargin);
//
//        addImageView();
//
//    }

    public void addImageView() {
        ImageView imageView = new ImageView(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(layoutParams);
//        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.mipmap.ic_launcher);
        addView(imageView);
    }

}
