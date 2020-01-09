package com.yaoh.AndroidDemo2;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by yaoh on 2019/2/25
 */
public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
