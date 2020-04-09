package com.yaoh.AndroidDemo2.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;

public class AndroidSqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private DBManager dbManager;

    private Button btn_add;
    private Button btn_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_sqlite);

        initData();
        initView();
    }

    private void initView() {
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        btn_query = findViewById(R.id.btn_query);
        btn_query.setOnClickListener(this);
    }

    private void initData() {
        dbManager = new DBManager(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_add) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dbManager.add();
                }
            }).start();

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    dbManager.query();
//                }
//            }).start();
//
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dbManager.add();
                }
            }).start();
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    dbManager.query();
//                }
//            }).start();


        } else if (id == R.id.btn_query) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    dbManager.add();
                    dbManager.query();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    dbManager.query();
                }
            }).start();
        }
    }
}
