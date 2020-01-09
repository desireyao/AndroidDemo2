package com.yh.BinderDemo2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yh.aidl.IPerson;

/**
 * Created by yaoh on 2019/3/15
 */
public class MyService extends Service {

    private static final String TAG = "MyService";

    IPerson.Stub mBinder = new IPerson.Stub() {
        @Override
        public String greet(String someone) throws RemoteException {
            return "Binder Demo2 greet!";
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind ---> 222");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate---> 222");
    }
}
