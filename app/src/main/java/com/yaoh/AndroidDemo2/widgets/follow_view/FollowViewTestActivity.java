package com.yaoh.AndroidDemo2.widgets.follow_view;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.yaoh.AndroidDemo2.R;

public class FollowViewTestActivity extends AppCompatActivity {


    private ImageView img;

    private BSensorManager mBSensorManager;
    private float mDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_view_test);

        img = findViewById(R.id.img);

        mBSensorManager = new BSensorManager(this);
        mBSensorManager.registerSensor(new OnAngleChangedListener() {
            @Override
            public void onAngleChanged(float degree) {
//                img.setRotation(degree);

                if (mDegree == 0) {
                    mDegree = degree;
                }

                // 旋转动画
                ObjectAnimator.ofFloat(img, "rotation", mDegree, degree)
                        .start();
                mDegree = degree;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBSensorManager.unRegisterSensor();
    }
}
