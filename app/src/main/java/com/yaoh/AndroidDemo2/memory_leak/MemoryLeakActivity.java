package com.yaoh.AndroidDemo2.memory_leak;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.utils.log.LogTool;

public class MemoryLeakActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MemoryLeakActivity";

    private Button btn1;
    private Button btn2;

    private TextView tv_text;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_text.setText(String.valueOf(msg.what));
        }
    };

//    private MyHandler mHandler = new MyHandler(this);
//    private static class MyHandler extends Handler {
//
//        private WeakReference<Context> reference;
//
//        public MyHandler(Context context) {
//            reference = new WeakReference<>(context);//这里传入activity的上下文
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            LogTool.LogE_DEBUG(TAG, "handleMessage----> MSG:" + msg.toString());
//            MemoryLeakActivity activity = (MemoryLeakActivity) reference.get();
//            activity.tv_text.setText(String.valueOf(msg.what));
//        }
//    }

    private MyManager myManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        myManager = MyManager.get(this);
        myManager.doTest();
        myManager.setContext(this);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        tv_text = findViewById(R.id.tv_text);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1) {

        } else if (id == R.id.btn2) {
//            LogTool.LogE_DEBUG(TAG, "myManager.getTestListener---> " + myManager.getTestListener());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LogTool.LogE_DEBUG(TAG, "sendEmptyMessage ----->");
                    mHandler.sendEmptyMessage((int) (System.currentTimeMillis() / 1000));
                }
            }, 1000 * 15);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (myManager != null) {
//            myManager.destroy();
//        }
//        myManager.setTestListener(null);
        mHandler.removeCallbacksAndMessages(null);
    }


}
