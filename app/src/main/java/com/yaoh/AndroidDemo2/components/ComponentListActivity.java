package com.yaoh.AndroidDemo2.components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.BaseActivity;
import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.components.services.normal.NormalServiceTestActivity;

public class ComponentListActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_normal_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_list);

        btn_normal_service = findViewById(R.id.btn_normal_service);
        btn_normal_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_normal_service: {
                startActivity(new Intent(this, NormalServiceTestActivity.class));
                break;
            }
        }
    }
}
