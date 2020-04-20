package com.yaoh.AndroidDemo2.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AndroidSqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private DBManager mDBManager;

    private Button btn_add;
    private Button btn_query;

    private ScheduledExecutorService mExecutor;

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
        mDBManager = new DBManager(this);
        mExecutor = Executors.newScheduledThreadPool(10);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_add) {
            for (int i = 0; i < 10000; i++) {
                mExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        mDBManager.insert();
                    }
                });

                mExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        mDBManager.delete();
                    }
                });


//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mDBManager.add();
//                    }
//                }).start();
            }
//
//            new Thread(() -> dbManager.query()).start();
//            new Thread(() -> dbManager.add()).start();
//            new Thread(() -> dbManager.add()).start();
//            new Thread(() -> dbManager.query()).start();

        } else if (id == R.id.btn_query) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    dbManager.add();
                    mDBManager.query();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mDBManager.query();
                }
            }).start();
        }
    }
}
