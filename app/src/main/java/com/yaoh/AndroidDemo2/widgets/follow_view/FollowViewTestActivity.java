package com.yaoh.AndroidDemo2.widgets.follow_view;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.widgets.follow_view.mapview.BSMapView;

public class FollowViewTestActivity extends AppCompatActivity {

//    private BSMapLayout mMapLayout;

    private BSensorManager mBSensorManager;
    private int mLastDegree;

    private ImageView imageView;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_view_test);

//        mMapLayout = findViewById(R.id.mapLayout);
        imageView = findViewById(R.id.imageview);
        imageView.setRotation(350);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator.ofFloat(imageView, "rotation", 350, 370)
                        .setDuration(2000)
                        .start();
            }
        },1000);

        // 旋转动画
        mBSensorManager = new BSensorManager(this);
        mBSensorManager.registerSensor(new OnAngleChangedListener() {
            @Override
            public void onAngleChanged(int degree) {
                if (mLastDegree == degree) {
                    return;
                }
//                ObjectAnimator.ofFloat(imageView, "rotation", 350, 5).start();
//                mLastDegree = degree;
            }
        });
    }


    public void addMapView() {
        BSMapView mMapView = new BSMapView(this);
//        mMapView.setBackgroundResource(R.drawable.data_team_logo_loading_76px);

//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        layoutParams.width = 4000;
//        layoutParams.height = 4000;
//
//        int leftMargin = (layoutParams.width - 1440) / 2;
//        int topMargin = (layoutParams.height - 2560) / 2;
//        layoutParams.leftMargin = -leftMargin;
//        layoutParams.topMargin = -topMargin;
//        mMapView.setLayoutParams(layoutParams);

//        mMapLayout.addView(mMapView);
    }

    public void addImageView() {
        ImageView imageView = new ImageView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.mipmap.ic_launcher);
//        mMapLayout.addView(imageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBSensorManager.unRegisterSensor();
    }
}
