package com.yaoh.AndroidDemo2.widgets.matrix;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yaoh.AndroidDemo2.R;
import com.yaoh.AndroidDemo2.utils.log.LogTool;
import com.yaoh.AndroidDemo2.widgets.matrix.animations.TransAnimation;

import java.util.Arrays;

public class MatrixActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MatrixActivity";

    private ImageView imageview;
    private ImageView imageview2;

    private Button btn1;
    private Button btn2;

    private Matrix mMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        imageview = findViewById(R.id.imageview);
        imageview2 = findViewById(R.id.imageview2);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        mMatrix = imageview.getImageMatrix();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1) {
//            mMatrix.set(imageview.getImageMatrix());
//            mMatrix.postTranslate(200, 200);
//            imageview.setImageMatrix(mMatrix);

            TransAnimation simpleCustomAnim = new TransAnimation(200,200);//简单的自定义动画效果
            simpleCustomAnim.setDuration(1000);
            imageview.startAnimation(simpleCustomAnim);

//            Matrix matrix = new Matrix();
//            camera.getMatrix(matrix);
//            matrix.postTranslate(0, 100);    // matrix - 沿y轴正方向平移100像素
        } else if (id == R.id.btn2) {
            Matrix matrix = imageview.getImageMatrix();
            float[] matrixValues = new float[9];
            matrix.getValues(matrixValues);
            LogTool.LogE_DEBUG(TAG, "onClick btn2 --->" + " matrixValues---> " + Arrays.toString(matrixValues));

//            imageview.setX(100);
//            imageview.setY(100);
//
//            imageview2.setX(5);
//            imageview2.setY(5);
        }
    }


    private void translationAnim() {
        float curTranslationX = imageview.getTranslationX();
        float curTranslationY = imageview.getTranslationX();
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageview, "translationX",
                curTranslationX, 200);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageview, "translationY",
                curTranslationY, 200);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animatorX, animatorY);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mMatrix.set(imageview.getImageMatrix());
                mMatrix.postTranslate(200, 200);
                imageview.setImageMatrix(mMatrix);

                Matrix matrix = imageview.getImageMatrix();
                float[] matrixValues = new float[9];
                matrix.getValues(matrixValues);
                LogTool.LogE_DEBUG(TAG, "onClick btn2 --->" + " matrixValues---> " + Arrays.toString(matrixValues));
            }
        });
        animSet.setDuration(5000);
        animSet.start();
    }
}
