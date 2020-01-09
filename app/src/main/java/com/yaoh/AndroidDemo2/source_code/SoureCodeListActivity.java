package com.yaoh.AndroidDemo2.source_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.source_code.eventbus.EventBusActivity;
import com.yaoh.AndroidDemo2.source_code.handler.HandlerActivity;
import com.yaoh.AndroidDemo2.source_code.rxjava.RxJavaActivity;

import org.greenrobot.eventbus.EventBus;

public class SoureCodeListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_handler_test;
    private Button btn_rxjava_test;
    private Button btn_eventbus_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soure_code_list);

        btn_handler_test = findViewById(R.id.btn_handler_test);
        btn_handler_test.setOnClickListener(this);

        btn_rxjava_test = findViewById(R.id.btn_rxjava_test);
        btn_rxjava_test.setOnClickListener(this);

        btn_eventbus_test = findViewById(R.id.btn_eventbus_test);
        btn_eventbus_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_handler_test: {
                startActivity(new Intent(this, HandlerActivity.class));
                break;
            }
            case R.id.btn_rxjava_test: {
                startActivity(new Intent(this, RxJavaActivity.class));
                break;
            }
            case R.id.btn_eventbus_test: {
                startActivity(new Intent(this, EventBusActivity.class));
                break;
            }
        }
    }
}
