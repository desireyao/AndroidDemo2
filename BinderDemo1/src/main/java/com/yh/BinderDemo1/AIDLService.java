package com.yh.BinderDemo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.yh.aidl.IPerson;

/**
 * Created by yaoh on 2019/3/15
 */
public class AIDLService extends Service {

    private static final String TAG = "AIDLService";

    IPerson.Stub stub = new IPerson.Stub() {
        @Override
        public String greet(String someone) throws RemoteException {
            Log.e(TAG, "greet()  demo1 called===============>");
            return "hello, " + someone;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind() called");
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind() called");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
    }


}
