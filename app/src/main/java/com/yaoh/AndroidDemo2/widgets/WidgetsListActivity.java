package com.yaoh.AndroidDemo2.widgets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.blur.BlurActivity;
import com.yaoh.AndroidDemo2.widgets.common_view.CommonWidgetActivity;
import com.yaoh.AndroidDemo2.widgets.dialog.DialogSimpleFragment;
import com.yaoh.AndroidDemo2.widgets.mapview.MapViewTestActivity;
import com.yaoh.AndroidDemo2.widgets.follow_view.FollowViewTestActivity;
import com.yaoh.AndroidDemo2.widgets.matrix.MatrixActivity;

public class WidgetsListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_follow_view;
    private Button btn_map_view;
    private Button btn_Matrix;
    private Button btn_dialog;
    private Button btn_common_widget;
    private Button btn_blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets_list);

        btn_follow_view = findViewById(R.id.btn_follow_view);
        btn_follow_view.setOnClickListener(this);

        btn_map_view = findViewById(R.id.btn_map_view);
        btn_map_view.setOnClickListener(this);

        btn_Matrix = findViewById(R.id.btn_Matrix);
        btn_Matrix.setOnClickListener(this);

        btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);

        btn_common_widget = findViewById(R.id.btn_common_widget);
        btn_common_widget.setOnClickListener(this);

        btn_blur = findViewById(R.id.btn_blur);
        btn_blur.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_follow_view: {
                startActivity(new Intent(this, FollowViewTestActivity.class));
                break;
            }
            case R.id.btn_map_view: {
                startActivity(new Intent(this, MapViewTestActivity.class));
                break;
            }
            case R.id.btn_Matrix: {
                startActivity(new Intent(this, MatrixActivity.class));
                break;
            }
            case R.id.btn_dialog: {
                DialogSimpleFragment dialogSimpleFragment = new DialogSimpleFragment();
                dialogSimpleFragment.showDialog(this);
                break;
            }
            case R.id.btn_common_widget: {
                startActivity(new Intent(this, CommonWidgetActivity.class));
                break;
            }
            case R.id.btn_blur: {
                startActivity(new Intent(this, BlurActivity.class));
                break;
            }

        }
    }
}
