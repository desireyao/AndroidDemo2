package com.yaoh.AndroidDemo2.components.services.normal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;

public class NormalServiceTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_startService;
    private Button btn_stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_service_test);

        btn_startService = findViewById(R.id.btn_startService);
        btn_startService.setOnClickListener(this);

        btn_stopService = findViewById(R.id.btn_stopService);
        btn_stopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_startService: {
                startService(new Intent(this, MyService.class));
                break;
            }
            case R.id.btn_stopService: {
                stopService(new Intent(this, MyService.class));
                break;
            }
        }
    }
}
