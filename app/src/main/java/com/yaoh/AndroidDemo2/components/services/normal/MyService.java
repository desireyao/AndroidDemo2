package com.yaoh.AndroidDemo2.components.services.normal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.yaoh.AndroidDemo2.R;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(TAG, "onBind--->");
        return new MyBinder();

//        return printBinder;
    }

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate--->");
//        setForegroundService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand--->");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy--->");

//        startService(new Intent(this, MyService.class));
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind--->");
        return super.onUnbind(intent);
    }


    public void setForegroundService() {
        startForeground(1, NotificationUtil.get(this)
                .createNotification("标题","内容",R.mipmap.ic_launcher));
    }

//    public void printText(String text) {
//        Log.e(TAG, "printText---> text = " + text);
//    }
}
