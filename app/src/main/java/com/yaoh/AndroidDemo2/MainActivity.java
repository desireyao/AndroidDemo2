package com.yaoh.AndroidDemo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.anim.AnimActivity;
import com.yaoh.AndroidDemo2.components.ComponentListActivity;
import com.yaoh.AndroidDemo2.liveData.MVVMActivity;
import com.yaoh.AndroidDemo2.memory_leak.MemoryLeakActivity;
import com.yaoh.AndroidDemo2.protect_setting.ProtectSettingActivity;
import com.yaoh.AndroidDemo2.source_code.SoureCodeListActivity;
import com.yaoh.AndroidDemo2.webview.WebviewDemoActivity;
import com.yaoh.AndroidDemo2.widgets.WidgetsListActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_android_components;
    private Button btn_widgets;
    private Button btn_background_management;
    private Button btn_sourceCode;
    private Button btn_memoryLeak;
    private Button btn_sqlite;
    private Button btn_liveData;
    private Button btn_anim;
    private Button btn_androidUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_android_components = findViewById(R.id.btn_android_components);
        btn_android_components.setOnClickListener(this);

        btn_widgets = findViewById(R.id.btn_widgets);
        btn_widgets.setOnClickListener(this);

        btn_background_management = findViewById(R.id.btn_background_management);
        btn_background_management.setOnClickListener(this);

        btn_sourceCode = findViewById(R.id.btn_sourceCode);
        btn_sourceCode.setOnClickListener(this);

        btn_memoryLeak = findViewById(R.id.btn_memoryLeak);
        btn_memoryLeak.setOnClickListener(this);

        btn_sqlite = findViewById(R.id.btn_sqlite);
        btn_sqlite.setOnClickListener(this);

        btn_liveData = findViewById(R.id.btn_liveData);
        btn_liveData.setOnClickListener(this);

        btn_anim = findViewById(R.id.btn_anim);
        btn_anim.setOnClickListener(this);

        btn_androidUtils = findViewById(R.id.btn_androidUtils);
        btn_androidUtils.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_android_components: {
                startActivity(new Intent(this, ComponentListActivity.class));
                break;
            }
            case R.id.btn_widgets: {
                startActivity(new Intent(this, WidgetsListActivity.class));
                break;
            }
            case R.id.btn_background_management: {
                startActivity(new Intent(this, ProtectSettingActivity.class));
                break;
            }
            case R.id.btn_sourceCode: {
                startActivity(new Intent(this, SoureCodeListActivity.class));
                break;
            }
            case R.id.btn_memoryLeak: {
                startActivity(new Intent(this, MemoryLeakActivity.class));
                break;
            }
            case R.id.btn_sqlite: {
                startActivity(new Intent(this, WebviewDemoActivity.class));
                break;
            }
            case R.id.btn_liveData: {
                startActivity(new Intent(this, MVVMActivity.class));
                break;
            }
            case R.id.btn_anim: {
                startActivity(new Intent(this, AnimActivity.class));
                break;
            }
        }
    }
}
