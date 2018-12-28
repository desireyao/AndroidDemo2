package com.yaoh.AndroidDemo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.components.ComponentListActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_android_components;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_android_components = findViewById(R.id.btn_android_components);
        btn_android_components.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_android_components: {
                startActivity(new Intent(this, ComponentListActivity.class));
                break;
            }
        }
    }
}