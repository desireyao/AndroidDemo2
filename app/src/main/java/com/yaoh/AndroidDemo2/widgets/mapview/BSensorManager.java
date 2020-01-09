package com.yaoh.AndroidDemo2.widgets.mapview;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.yaoh.AndroidDemo2.utils.log.LogTool;
import com.yaoh.AndroidDemo2.widgets.follow_view.OnAngleChangedListener;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by yaoh on 2018/12/27.
 */

public class BSensorManager {

    private static final String TAG = "BSensorManager";

    private SensorManager mSensorManager;
    private Sensor mSensorOri;

    private Context mContext;

    private OnAngleChangedListener mOnAngleChangedListener;

    private int mLastDegree;

    public BSensorManager(Context context) {
        mContext = context.getApplicationContext();

        mSensorManager = (SensorManager) mContext.getSystemService(SENSOR_SERVICE);
        mSensorOri = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    public void registerSensor(OnAngleChangedListener listener) {
        mOnAngleChangedListener = listener;
        mSensorManager.registerListener(mSensorEventListener, mSensorOri, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unRegisterSensor() {
        mOnAngleChangedListener = null;
        mSensorManager.unregisterListener(mSensorEventListener);
    }

    SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            int degree = (int) event.values[0];
            if (mLastDegree == degree) {
                return;
            }

            mLastDegree = degree;
            if (mOnAngleChangedListener != null) {
                mOnAngleChangedListener.onAngleChanged(degree);
            }
//            LogTool.LogD(TAG, " onSensorChanged ---> mDegree: " + degree);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
