package com.yaoh.AndroidDemo2.widgets.mapview;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.beacool.bsmapviewlib.widget.BSMapLayout;
import com.beacool.bsmapviewlib.widget.BSMapPointView;
import com.beacool.bsmapviewlib.widget.BSMapView;
import com.beacool.bsmapviewlib.widget.OnMapViewReadyListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.utils.log.LogTool;
import com.yaoh.AndroidDemo2.widgets.follow_view.OnAngleChangedListener;

public class MapViewTestActivity extends AppCompatActivity {

    private static final String TAG = "MapViewTestActivity";

    private static final String MAP_PNG_URL = "http://m4.beacool.com/iot_file/image/1528779378447.png";

    private BSMapView mMapView;
    private BSMapLayout mMapLayout;

    private BSensorManager mBSensorManager;
    private BSMapPointView mUserPoint;

    private int mLastDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview_test);

        mMapLayout = findViewById(R.id.mapLayout);
        mMapLayout.setOnMapViewReadyListener(new OnMapViewReadyListener() {
            @Override
            public void onMapViewReady() {
                mMapView = mMapLayout.getMapView();
                showMap(MAP_PNG_URL);
            }
        });
    }


    private void showMap(String mapUrl) {
        Glide.with(this).load(mapUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                LogTool.LogE(TAG, " width = " + bitmap.getWidth() + " height = " + bitmap.getHeight());
                mMapView.setMapBitmap(bitmap);

                mUserPoint = new BSMapPointView(MapViewTestActivity.this,
                        2175, 2175, 0.18f, "icon_user_point.png");
                mMapView.addMapPoint(mUserPoint);

                LogTool.LogE_DEBUG(TAG, "111---> mUserPoint.getX(): " + mUserPoint.getX()
                        + " mUserPoint.getY(): " + mUserPoint.getY());
                registerSensor();
            }
        });
    }


    private void registerSensor() {
        mBSensorManager = new BSensorManager(this);
        mBSensorManager.registerSensor(new OnAngleChangedListener() {
            @Override
            public void onAngleChanged(int degree) {
                if (mLastDegree == 0) {
                    mLastDegree = degree;
                }

                int lastMapDegree = 360 - mLastDegree;
                int curMapDegree = 360 - degree;

                if (Math.abs(curMapDegree - lastMapDegree) > 180) {
                    mLastDegree = degree;
                }
                // 旋转动画
                ObjectAnimator animator = ObjectAnimator.ofFloat(mMapView, "rotation",
                        360 - mLastDegree, 360 - degree);

                LogTool.LogE_DEBUG(TAG, "---> mUserPoint.getX(): " + mUserPoint.getX()
                        + " mUserPoint.getY(): " + mUserPoint.getY());
                LogTool.LogE_DEBUG(TAG, "---> mMapView.getPivotX(): " + mMapView.getPivotX()
                        + " mMapView.getPivotY(): " + mMapView.getPivotY());
                animator.start();

                mUserPoint.setFirstX(2500);
                mUserPoint.setFirstY(2500);
                mMapView.updatePointPosition(mUserPoint);

                mUserPoint.setRotation(degree);
                mLastDegree = degree;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBSensorManager.unRegisterSensor();
    }
}
