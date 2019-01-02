package com.yaoh.AndroidDemo2.protect_setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beacool.BPermissonProtect.BPermissonProtectActivity;
import com.yaoh.AndroidDemo2.R;

public class ProtectSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_setting_permisson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_setting);

        btn_setting_permisson = findViewById(R.id.btn_setting_permisson);
        btn_setting_permisson.setOnClickListener(this);

//        IntentWrapper.init(this);
//        IntentWrapper.whiteListMatters(this, "AndroidDemo2");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_setting_permisson) {
            startActivity(new Intent(this, BPermissonProtectActivity.class));
        }
    }
}
