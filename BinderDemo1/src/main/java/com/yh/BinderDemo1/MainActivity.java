package com.yh.BinderDemo1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yh.aidl.IPerson;


/**
 * BINDER1是客户端 调用 BINDER2服务端
 */

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_BIND_SERVICE = "com.yh.BinderDemo2.MyService";

    private Button bindBtn;
    private Button greetBtn;
    private Button unbindBtn;

    private IPerson person;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("ServiceConnection", "onServiceConnected() called");
            person = IPerson.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //This is called when the connection with the service has been unexpectedly disconnected,
            //that is, its process crashed. Because it is running in our same process, we should never see this happen.
            Log.e("ServiceConnection", "onServiceDisconnected() called");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindBtn = findViewById(R.id.bindBtn);
        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_BIND_SERVICE);
//                Intent intent = new Intent(MainActivity.this, AIDLService.class);
                intent.setPackage("com.yh.BinderDemo2");
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

                bindBtn.setEnabled(false);
                greetBtn.setEnabled(true);
                unbindBtn.setEnabled(true);
            }
        });

        greetBtn = findViewById(R.id.greetBtn);
        greetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String retVal = person.greet("scott");
                    Toast.makeText(MainActivity.this, retVal, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        unbindBtn = findViewById(R.id.unbindBtn);
        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mServiceConnection);

                bindBtn.setEnabled(true);
                greetBtn.setEnabled(false);
                unbindBtn.setEnabled(false);
            }
        });

    }
}
