package com.yaoh.AndroidDemo2.memory_leak;

import android.content.Context;

import com.yaoh.AndroidDemo2.utils.log.LogTool;

import java.lang.ref.WeakReference;

/**
 * Created by yaoh on 2019/2/20
 */
public class MyManager {

    private static final String TAG = "MyManager";

    private volatile static MyManager mInstance;

    private OnTestListener mListener;

    private WeakReference<Context> mReference;

    private Context mContext;

    public MyManager(Context context) {
        mReference = new WeakReference<>(context);
    }

    public static MyManager get(Context context) {
        if (mInstance == null) {
            synchronized (MyManager.class) {
                if (mInstance == null) {
                    mInstance = new MyManager(context);
                }
            }
        }
        return mInstance;
    }

    public void doTest() {
        Context context = mReference.get();
        LogTool.LogE_DEBUG(TAG, " doTest ---> context: " + context);
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setTestListener(OnTestListener listener) {
        this.mListener = mListener;
    }

    public OnTestListener getTestListener() {
        return mListener;
    }

    public void destroy() {
        mInstance = null;
    }
}
