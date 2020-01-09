package com.beacool.bsmapviewlib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.beacool.bsmapviewlib.R;
import com.beacool.bsmapviewlib.ViewUtil;
import com.beacool.bsmapviewlib.log.LogTool;

import java.util.ArrayList;

public class BSMapView extends RelativeLayout {

    private static final String TAG = "BSMapView";

    // 显示 地图 底图和线 的 控件
    private BSMapAndLines mMapAndLines;

    // 地图 原点 左上角
    private View originalPointView;

    // 地图上 标记 的 点
    private ArrayList<BSMapPointView> mMapPoints = new ArrayList<>();

    private float firstScale;  // 首次 放大缩小的 倍数
    private float minScale;    // 最小倍数

    private int mMapBitmapWith;
    private int mMapBitmapHeight;

    private float mBimapScale = 1.f;

    private ImageView mIcon_edit_point;
    private Bitmap mEditPointBitmap;

    private int mTouchslop; // 最小的触摸距离

    // 在最小倍数 是否支持滑动
    private boolean isSupportMoveWithMinZoom;

    public BSMapView(Context context) {
        this(context, null);
    }

    public BSMapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BSMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // 初始化
    private void init() {
        mTouchslop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

//        setBackgroundColor(getResources().getColor(R.color.colorAccent));
//          setBackgroundResource(R.drawable.data_team_logo_loading_76px);

        mMapAndLines = new BSMapAndLines(getContext());
        LayoutParams mapLineViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mapLineViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mMapAndLines.setLayoutParams(mapLineViewParams);
//      mMapAndLines.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mMapAndLines.setScaleType(ImageView.ScaleType.MATRIX);
        addView(mMapAndLines);

        originalPointView = new BSMapPointView(getContext());
        Log.e(TAG, " originalPointView ------>"
                + " getX() =  " + originalPointView.getX()
                + " getY() = " + originalPointView.getY());

//        // 设置 触摸 监听
//        setOnTouchListener(new MyTouchListener());
    }


    /**
     * 设置最小倍数时是否可以滑动
     *
     * @param supportMoveWithMinZoom
     */
    public void setSupportMoveWithMinZoom(boolean supportMoveWithMinZoom) {
        isSupportMoveWithMinZoom = supportMoveWithMinZoom;
    }


    /**
     * 加多个 点
     *
     * @param list
     */
    public void setMapPoints(ArrayList<BSMapPointView> list) {
        clearMapPoints();
        mMapPoints.addAll(list);
    }

    /**
     * 加 多条 线
     *
     * @param mapLineCoords
     */
    public void setMapLines(ArrayList<BSMapAndLines.MapLineCoord> mapLineCoords) {
        mMapAndLines.clearLines();
        mMapAndLines.addLines(mapLineCoords);
    }

    /**
     * 清空 点
     */
    public void clearMapPoints() {
        for (int i = 0; i < mMapPoints.size(); i++) {
            removeView(mMapPoints.get(i));
        }
        mMapPoints.clear();
    }

    /**
     * 清空 线
     */
    public void clearMapLines() {
        mMapAndLines.clearLines();
    }

    /**
     * 更新点的位置
     *
     * @param mapPoint
     */

    public void updatePointPosition(BSMapPointView mapPoint) {
        updatePointPosition(null, mapPoint);
    }

    public void updatePointPosition(BSMapPointView mLastPoint, BSMapPointView mapPoint) {
        mapPoint.setFirstX(mapPoint.getFirstX() * mBimapScale);
        mapPoint.setFirstY(mMapBitmapHeight - mapPoint.getFirstY() * mBimapScale);

        float showX = (float) (mapPoint.getFirstX() * firstScale + originalPointView.getX());
        float showY = (float) (mapPoint.getFirstY() * firstScale + originalPointView.getY());

//        LogTool.LogE_DEBUG(TAG, "addMapPoint------>"
//                + "\n originalPointView.getX() = " + originalPointView.getX()
//                + " originalPointView.getY() = " + originalPointView.getY()
//                + "\n showX = " + showX
//                + " showY = " + showY
//                + "\n mBimapScale = " + mBimapScale);

        mapPoint.setFirstXShow(showX);
        mapPoint.setFirstYShow(showY);

//        if (mLastPoint != null) {
//            mapPoint.moveWithAnimator(mLastPoint.getX(), showX, mLastPoint.getY(), showY);
//        } else {
//            mapPoint.moveWithAnimator(showX, showX, showY, showY);
//        }
    }

    /**
     * 添加一个点 并显示
     */
    public void addMapPoint(BSMapPointView mapPoint) {
        // 需要 转化坐标点
//        mapPoint.setFirstY(mMapBitmapHeight - mapPoint.getFirstY());
        mapPoint.setFirstX(mapPoint.getFirstX() * mBimapScale);
        mapPoint.setFirstY(mMapBitmapHeight - mapPoint.getFirstY() * mBimapScale);

        float showX = (float) (mapPoint.getFirstX() * firstScale + originalPointView.getX());
        float showY = (float) (mapPoint.getFirstY() * firstScale + originalPointView.getY());

//        LogTool.LogE_DEBUG(TAG, "addMapPoint------>"
//                + "\n originalPointView.getX() = " + originalPointView.getX()
//                + " originalPointView.getY() = " + originalPointView.getY()
//                + "\n showX = " + showX
//                + " showY = " + showY
//                + "\n mBimapScale = " + mBimapScale);

        mapPoint.setFirstXShow(showX);
        mapPoint.setFirstYShow(showY);
        addView(mapPoint);

        mMapPoints.add(mapPoint);
    }

    /**
     * 移出点
     *
     * @param mapPoint
     */
    public void removeMapPoint(BSMapPointView mapPoint) {
        removeView(mapPoint);
        mMapPoints.remove(mapPoint);
    }

    /**
     * 添加 线 的 坐标
     * 至少 两个 ，且 这两个 坐标不重复 ，线才会显示
     *
     * @param x
     * @param y
     */
    public void addMapLineCoord(float x, float y) {

        float firstX = x * mBimapScale;
        float firstY = mMapBitmapHeight - y * mBimapScale;

        BSMapAndLines.MapLineCoord mapLineCoord = new BSMapAndLines.MapLineCoord(firstX, firstY,
                firstX * firstScale + originalPointView.getX(),
                firstY * firstScale + originalPointView.getY());

        mapLineCoord.setType(0);

        mMapAndLines.addLineCoord(mapLineCoord);
    }

    /**
     * 添加线的坐标 type 代表绘制不同的颜色
     *
     * @param x
     * @param y
     * @param type
     */
    public void addMapLineCoord(float x, float y, int type) {

        float firstX = x * mBimapScale;
        float firstY = mMapBitmapHeight - y * mBimapScale;

        BSMapAndLines.MapLineCoord mapLineCoord = new BSMapAndLines.MapLineCoord(firstX, firstY,
                firstX * firstScale + originalPointView.getX(),
                firstY * firstScale + originalPointView.getY());

        mapLineCoord.setType(type);

        mMapAndLines.addLineCoord(mapLineCoord);
    }


    /**
     * 设置 地图 底图 并显示
     *
     * @param bitmap
     */
    public void setMapBitmap(Bitmap bitmap) {
        if (bitmap.getWidth() > 2000 || bitmap.getHeight() > 2000) {
            mBimapScale = 0.5f;
        }

        // 大图不能显示 在此处进行压缩
        final Bitmap scaleBitmap = ViewUtil.scaleBitmap(bitmap, mBimapScale);
        mMapBitmapWith = scaleBitmap.getWidth();
        mMapBitmapHeight = scaleBitmap.getHeight();

        mMapAndLines.post(new Runnable() {
            @Override
            public void run() {
                float widthScale = getWidth() * 1.0f / mMapBitmapWith;
                float heightScale = getHeight() * 1.0f / mMapBitmapHeight;
                firstScale = Math.min(widthScale, heightScale); // 这里放大两倍
                minScale = firstScale;

                float initPostTransX = getWidth() / 2.f - (mMapBitmapWith * firstScale) / 2.f;
                float initPostTransY = getHeight() / 2.f - (mMapBitmapHeight * firstScale) / 2.f;

                LogTool.LogE_DEBUG(TAG, " setMapBitmap------> "
                        + " \n firstScale = " + firstScale
                        + " \n widthScale = " + widthScale
                        + " \n heightScale = " + heightScale
                        + " \n mMapBitmapWith = " + mMapBitmapWith
                        + " \n mMapBitmapHeight = " + mMapBitmapHeight
                        + " \n getLeft() = " + getLeft()
                        + " \n getRight() = " + getRight()
                        + " \n getWidth() = " + getWidth()
                        + " \n getHeight() = " + getHeight()
                        + " \n baseMapAndLines.getWidth() =" + mMapAndLines.getWidth()
                        + " \n baseMapAndLines.getHeight() = " + mMapAndLines.getHeight()
                        + " \n initPostTransX = " + initPostTransX
                        + " \n initPostTransY = " + initPostTransY);

                mMapAndLines.setImageBitmap(scaleBitmap);
                Matrix matrix = new Matrix();
                matrix.preScale(firstScale, firstScale);
                matrix.postTranslate(initPostTransX, initPostTransY);
                mMapAndLines.setImageMatrix(matrix);


//                originalPointView.setX(originalPointView.getX() * firstScale);
//                originalPointView.setY(originalPointView.getY() * firstScale);
                originalPointView.setX(initPostTransX);
                originalPointView.setY(initPostTransY);
            }
        });
    }


    /**
     * 旋转地图图片
     *
     * @param angle
     */
    public void roationBitmap(float angle) {
        mMapAndLines.setRotation(angle);
    }

    /**
     * 根据 当前 缩放 比例
     * 移动 点 的 位置
     * 用于 放大缩小 按钮
     */
    public void moveMapPoints() {
        for (int i = 0; i < mMapPoints.size(); i++) {
            BSMapPointView point = mMapPoints.get(i);

            // 设置 点 的 初始位置
            point.setFirstXShow((float) (point.getFirstX() * firstScale));
            point.setFirstYShow((float) (point.getFirstY() * firstScale));
        }
    }

    /**
     * 根据 当前 缩放 比例
     * 移动 线 的 位置
     * 用于 放大缩小 按钮
     */
    public void moveMapLines() {
        for (int i = 0; i < mMapAndLines.getLineSize(); i++) {
            BSMapAndLines.MapLineCoord line = mMapAndLines.getLine(i);
            line.setViewX(line.getFirstX() * firstScale);
            line.setViewY(line.getFirstY() * firstScale);
        }
        mMapAndLines.invalidate();
    }

    /**
     * 得到 地图 点 的集合
     *
     * @return
     */
    public ArrayList<BSMapPointView> getMapPoints() {
        return mMapPoints;
    }

    /**
     * 得到 地图 线 的 集合
     *
     * @return
     */
    public ArrayList<BSMapAndLines.MapLineCoord> getMapLines() {
        return mMapAndLines.getMapLineCoords();
    }

    /**
     * 将中心点添加到地图上
     */
    public void addCenterPoint(OnAddCenterPointListerner listerner, float scale, String pathName) {
        if (mEditPointBitmap == null) {
            return;
        }

        float editPointX = (getLeft() + getRight()) / 2 - getX();
        // 把添加的点 放到编辑点的 横向居中 bottom 位置
        float editPointY = (getTop() + getBottom()) / 2 - getY() + mEditPointBitmap.getHeight() / 2;

        float originalPointViewX = originalPointView.getX();
        float originalPointViewY = originalPointView.getY();

        Log.e(TAG, " addCenterPoint --------->"
                + "\n originalPointViewX = " + originalPointViewX
                + "\t originalPointViewY = " + originalPointViewY
                + "\n getX() = " + getX()
                + "\t getY() = " + getY()
                + "\n baseMapAndLines.getWidth() = " + mMapAndLines.getWidth()
                + "\t baseMapAndLines.getHeight() = " + mMapAndLines.getHeight());

        float x = (editPointX - originalPointViewX) / firstScale;
        float y = (editPointY - originalPointViewY) / firstScale;

        // 回调出去的映射到地图上的坐标点
        float mPointX = x / mBimapScale;
        float mPointY = (mMapBitmapHeight - y) / mBimapScale;

        // 回调出去的点 坐标处理
        LogTool.LogE_DEBUG(TAG, " addCenterPoint ---------->"
                + " x = " + x
                + " y = " + y
                + " mPointX = " + mPointX
                + " mPointY = " + mPointY);

        BSMapPointView mapPoint = new BSMapPointView(getContext(), x, y, scale, pathName);
        float showX = (float) (mapPoint.getFirstX() * firstScale + originalPointView.getX());
        float showY = (float) (mapPoint.getFirstY() * firstScale + originalPointView.getY());

        LogTool.LogE_DEBUG(TAG, "addMapPoint------>"
                + "\n originalPointView.getX() = " + originalPointView.getX()
                + " originalPointView.getY() = " + originalPointView.getY()
                + "\n showX = " + showX
                + " showY = " + showY);

        mapPoint.setFirstXShow(showX);
        mapPoint.setFirstYShow(showY);

        /**
         * 这里因为之后会重新请求服务器刷新 所以不需要
         */
//        addView(mapPoint);
//        mapPoints.add(mapPoint);

        if (listerner != null) {
            listerner.onAddCenterPoint(mPointX, mPointY, mapPoint);
        }
    }

    /**
     * 显示编辑的点
     */
    public void showEditPoint(String pathName, float scale) {
        if (mIcon_edit_point == null) {
            mIcon_edit_point = new ImageView(getContext());
            LayoutParams iconEditParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            iconEditParams.addRule(RelativeLayout.CENTER_IN_PARENT);

            float mScale = ViewUtil.getScreenDensity(getContext()) / scale;
            mEditPointBitmap = ViewUtil.scaleBitmap(ViewUtil.getBitmapFormAsset(getContext(), pathName), mScale);
            mIcon_edit_point.setImageBitmap(mEditPointBitmap);
            mIcon_edit_point.setLayoutParams(iconEditParams);
            addView(mIcon_edit_point);
        }

        /**
         * 放在最上层
         */
        mIcon_edit_point.bringToFront();
        mIcon_edit_point.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏编辑的点
     */
    public void hideEditPoint() {
        if (mIcon_edit_point != null) {
            mIcon_edit_point.setVisibility(View.GONE);
        }
    }

    /**
     * 记录是拖拉照片模式还是放大缩小照片模式
     */
    private int mode = 0;// 初始状态
    /**
     * 拖拉照片模式
     */
    private static final int MODE_DRAG = 1;
    /**
     * 放大缩小照片模式
     */
    private static final int MODE_ZOOM = 2;

    /**
     * 用于记录开始时候的坐标位置
     */
    private PointF startPoint = new PointF();
    /**
     * 用于记录拖拉图片移动的坐标位置
     */
    private Matrix matrix = new Matrix();
    /**
     * 用于记录图片要进行拖拉时候的坐标位置
     */
    private Matrix currentMatrix = new Matrix();

    /**
     * 两个手指的开始距离
     */
    private float startDis;
    /**
     * 两个手指的中间点
     */
    private PointF midPoint;

    private float downX;
    private float downY;

    private boolean isMoveAction;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        LogTool.LogE_DEBUG(TAG, " onInterceptTouchEvent ------> event = \n " + event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                isMoveAction = false;
                mode = MODE_DRAG;
                // 记录ImageView当前的移动位置
                currentMatrix.set(mMapAndLines.getImageMatrix());
                startPoint.set(event.getX(), event.getY());

//                LogTool.LogE_DEBUG(TAG, " MotionEvent.ACTION_DOWN ------>"
//                        + " event.getX() = " + event.getX()
//                        + " event.getY() = " + event.getY());
                downX = event.getX();
                downY = event.getY();

                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
//                LogTool.LogE_DEBUG(TAG, " onInterceptTouchEvent MotionEvent.ACTION_POINTER_DOWN ------>"
//                        + " event.getX() = " + event.getX()
//                        + " event.getY() = " + event.getY());
                mode = MODE_ZOOM;
                /** 计算两个手指间的距离 */
                startDis = distance(event);
                /** 计算两个手指间的中间点 */
                if (startDis > mTouchslop) { // 两个手指并拢在一起的时候像素大于10
                    midPoint = mid(event);
                    //记录当前ImageView的缩放倍数
                    currentMatrix.set(mMapAndLines.getImageMatrix());
                }
                return true;
//                break;
            }
            case MotionEvent.ACTION_MOVE: {
                LogTool.LogE_DEBUG(TAG, " onInterceptTouchEvent MotionEvent.ACTION_MOVE ------>"
                        + " downX = " + downX
                        + " downY = " + downY
                        + " event.getX() = " + event.getX()
                        + " event.getY() = " + event.getY()
                        + " touchslop = " + mTouchslop);

                if (Math.abs(event.getX() - downX) > mTouchslop
                        || Math.abs(event.getY() - downY) > mTouchslop) {
                    LogTool.LogE_DEBUG(TAG, " MotionEvent.ACTION_MOVE ---> isMoveAction");
                    isMoveAction = true;
                    return true;
                } else {
                    return false;
                }
//                return true;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        LogTool.LogE_DEBUG(TAG, "onTouchEvent---> event = \n " + event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            // 手指压下屏幕
            case MotionEvent.ACTION_DOWN: {
//                mode = MODE_DRAG;
//                // 记录ImageView当前的移动位置
//                currentMatrix.set(baseMapAndLines.getImageMatrix());
//                startPoint.set(event.getX(), event.getY());
////                LogTool.LogE_DEBUG(TAG, " MotionEvent.ACTION_DOWN ------>"
////                        + " event.getX() = " + event.getX()
////                        + " event.getY() = " + event.getY());
                break;
            }
            // 手指在屏幕上移动，改事件会被不断触发
            case MotionEvent.ACTION_MOVE: {
                isMoveAction = true;
                // 拖拉图片
                if (mode == MODE_DRAG) {
//                    LogTool.LogE_DEBUG(TAG, " MotionEvent.ACTION_MOVE ------>"
//                            + " event.getX() = " + event.getX()
//                            + " event.getY() = " + event.getY()
//                            + " event.getRawX() = " + event.getRawX()
//                            + " event.getRawY() = " + event.getRawY());

                    float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                    float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
                    // 在没有移动之前的位置上进行移动
                    matrix.set(currentMatrix);
                    matrix.postTranslate(dx, dy);


                    if (!isSupportMoveWithMinZoom) {
                        /**
                         * 原始倍数 不消耗事件
                         */
                        float[] matrixValues = new float[9];
                        mMapAndLines.getImageMatrix().getValues(matrixValues);
                        if (matrixValues[0] == minScale) {
                            LogTool.LogD(TAG, " baseMapAndLines :" + mMapAndLines.getImageMatrix()
                                    + " minScale = " + minScale);
                            super.onTouchEvent(event);
                            return false;
                        }
                    }
                } else if (mode == MODE_ZOOM) {
                    // 放大缩小图片
                    float endDis = distance(event);         // 结束距离
                    if (endDis > mTouchslop) {              // 两个手指并拢在一起的时候像素大于10
                        float scale = endDis / startDis;    // 得到缩放倍数
                        matrix.set(currentMatrix);
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                }
                break;
            }
            // 手指离开屏幕
            case MotionEvent.ACTION_UP: {
                // 两个手指离开屏幕
//                LogTool.LogE_DEBUG(TAG, " MotionEvent.ACTION_UP ---> firstScale = " + firstScale
//                        + " minScale = " + minScale);
                if (firstScale < minScale) {
                    float scale = minScale / firstScale;
                    firstScale = minScale;

//                    LogTool.LogE_DEBUG(TAG, " setMapBitmap------> "
//                            + " \n firstScale = " + firstScale
//                            + " \n originalPointView.getX() = " + originalPointView.getX()
//                            + " \n originalPointView.getY() = " + originalPointView.getY()
//                            + " \n baseMapAndLines.getWidth() =" + baseMapAndLines.getWidth()
//                            + " \n baseMapAndLines.getHeight() = " + baseMapAndLines.getHeight()
//                            + " \n baseMapAndLines.getLeft() = " + baseMapAndLines.getLeft()
//                            + " \n baseMapAndLines.getRight() = " + baseMapAndLines.getRight()
//                            + " \n baseMapAndLines.getTop() = " + baseMapAndLines.getTop()
//                            + " \n baseMapAndLines.getBottom() = " + baseMapAndLines.getBottom()
//                            + " \n getLeft() = " + getLeft()
//                            + " \n getRight() = " + getRight()
//                            + " \n getWidth() = " + getWidth()
//                            + " \n getHeight() = " + getHeight());

                    float initPostTransX = getWidth() / 2.f - (mMapBitmapWith * firstScale) / 2.f;
                    float initPostTransY = getHeight() / 2.f - (mMapBitmapHeight * firstScale) / 2.f;

                    float offsetX = -originalPointView.getX() + initPostTransX;
                    float offsetY = -originalPointView.getY() + initPostTransY;

                    matrix.preScale(scale, scale);
                    matrix.postTranslate(offsetX, offsetY);
                    updateBaseMapAndLines();
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                // 当触点离开屏幕，但是屏幕上还有触点(手指)
//                LogTool.LogE_DEBUG(TAG, " MotionEvent.ACTION_POINTER_UP --->");
                mode = 0;
                break;
            }
            // 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
            case MotionEvent.ACTION_POINTER_DOWN: {
//                LogTool.LogE_DEBUG(TAG, " MotionEvent.ACTION_POINTER_DOWN --->");
                mode = MODE_ZOOM;
                /** 计算两个手指间的距离 */
                startDis = distance(event);
                /** 计算两个手指间的中间点 */
                if (startDis > mTouchslop) { // 两个手指并拢在一起的时候像素大于10
                    midPoint = mid(event);
                    //记录当前ImageView的缩放倍数
                    currentMatrix.set(mMapAndLines.getImageMatrix());
                }
                break;
            }
        }

        /**
         * 如果 此次 触摸事件  是  移动，放大事件
         * 则 改变 地图 和 坐标点的位置
         */
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE:
                // 移动 地图
                updateBaseMapAndLines();
                /**
                 *如果 外层为ScrollView 此句代码是解决
                 * 地图的移动 和 ScrollView 的滚动冲突的
                 * 当触摸事件在地图范围内时，ScrollView 滚动事件无法响应
                 * 当触摸事件在 地图范围外时，ScrollView可以滚动
                 */
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }

//        LogTool.LogE_DEBUG(TAG, " onTouchEvent MotionEvent.ACTION_UP ------>"
//                + " downX = " + downX
//                + " downY = " + downY
//                + " event.getX() = " + event.getX()
//                + " event.getY() = " + event.getY());

        /**
         * 点击在一定范围时 才触发点击事件
         */
        if (Math.abs(event.getX() - downX) < mTouchslop && Math.abs(event.getY() - downY) < mTouchslop) {
            super.onTouchEvent(event);
        }

        return true;
    }


    /**
     * 更新地图上点的位置
     */
    private void updateBaseMapAndLines() {
        mMapAndLines.setImageMatrix(matrix);

        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);

        // 地图原点移动
        originalPointView.setX((float) (0 * matrixValues[0] + matrixValues[2]));
        originalPointView.setY((float) (0 * matrixValues[4] + matrixValues[5]));

        firstScale = matrixValues[0];
//            LogTool.LogE_DEBUG(TAG, "baseMapAndLines --->"
//                    + " firstScale = " + firstScale
//                    + " originalPointView.getX() = " + originalPointView.getX()
//                    + " originalPointView.getY() = " + originalPointView.getY());

        // 移动 点
        for (int i = 0; i < mMapPoints.size(); i++) {
            double scaleX = mMapPoints.get(i).getFirstX() * matrixValues[0];
            double scaleY = mMapPoints.get(i).getFirstY() * matrixValues[4];
            mMapPoints.get(i).setFirstXShow((float) (scaleX + matrixValues[2]));
            mMapPoints.get(i).setFirstYShow((float) (scaleY + matrixValues[5]));
        }

        //暂时不处理 移动 线
        for (int i = 0; i < mMapAndLines.getLineSize(); i++) {
            float v1 = mMapAndLines.getLine(i).getFirstX() * matrixValues[0] + matrixValues[2];
            float v2 = mMapAndLines.getLine(i).getFirstY() * matrixValues[4] + matrixValues[5];
            mMapAndLines.getLine(i).setViewX(v1);
            mMapAndLines.getLine(i).setViewY(v2);
        }

        mMapAndLines.invalidate();
    }

    /**
     * 计算两个手指间的距离
     */
    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        /** 使用勾股定理返回两点之间的距离 */
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 计算两个手指间的中间点
     */
    private PointF mid(MotionEvent event) {
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }

    /**
     * 添加中间点的回调
     */
    public interface OnAddCenterPointListerner {
        public void onAddCenterPoint(double pointX, double pointY, BSMapPointView mapPoint);
    }

}
