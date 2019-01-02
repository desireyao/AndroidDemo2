package com.yaoh.AndroidDemo2.widgets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.widgets.follow_view.FollowViewTestActivity;

public class WidgetsListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_follow_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets_list);

        btn_follow_view = findViewById(R.id.btn_follow_view);
        btn_follow_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_follow_view: {
                startActivity(new Intent(this, FollowViewTestActivity.class));
                break;
            }
        }
    }
}
