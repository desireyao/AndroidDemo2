package com.yaoh.AndroidDemo2.source_code.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.utils.log.LogTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EventBusActivity";

    private Button btn_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        btn_post = findViewById(R.id.btn_post);
        btn_post.setOnClickListener(this);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        LogTool.LogE_DEBUG(TAG,"onMessageEvent ---> event: " + event);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_post) {
            EventBus.getDefault().post("haha");
        }
    }
}
