package com.yaoh.AndroidDemo2.source_code.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.utils.log.LogTool;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RxJavaActivity";

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        initView();
    }

    private void initView() {
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn1: {
                test1();
                break;
            }
        }
    }

    private void test1() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1001);
                        emitter.onNext(1002);
                        LogTool.LogE_DEBUG(TAG, " subscribe---> currentThread.Name: " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogTool.LogE_DEBUG(TAG, "onNext---> value: " + value + " currentThread.Name: "
                                + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void test2() {
        Observable
                .just("a")        //Observable1
                .map(new Function<String, String>() {   // Observable2
                    @Override
                    public String apply(String s) throws Exception {
                        return null;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
