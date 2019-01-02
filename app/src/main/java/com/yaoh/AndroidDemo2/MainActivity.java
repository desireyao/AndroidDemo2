package com.yaoh.AndroidDemo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.protect_setting.ProtectSettingActivity;
import com.yaoh.AndroidDemo2.components.ComponentListActivity;
import com.yaoh.AndroidDemo2.widgets.WidgetsListActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_android_components;
    private Button btn_widgets;
    private Button btn_background_management;

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
        }
    }
}
