package com.yaoh.AndroidDemo2.source_code.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.utils.log.LogTool;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "HandlerActivity";

    private  Handler mHandler;

    private Button btn_sendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        btn_sendMessage = findViewById(R.id.btn_sendMessage);
        btn_sendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_sendMessage){
            if(mHandler != null) {
                mHandler.sendEmptyMessage(1000);
            }
        }
    }


    private void startTest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        LogTool.LogE_DEBUG(TAG, "handleMessage ---> msg = " + msg
                                + "\n currentThread : " + Thread.currentThread().getName());
                    }
                };
//                mHandler.sendEmptyMessage(1);
                Looper.loop();
                mHandler.sendEmptyMessage(1);

                LogTool.LogD(TAG, "--------->");

            }
        }).start();
    }
}
