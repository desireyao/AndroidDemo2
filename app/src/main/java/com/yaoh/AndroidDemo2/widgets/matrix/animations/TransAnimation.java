package com.yaoh.AndroidDemo2.widgets.matrix.animations;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.yaoh.AndroidDemo2.utils.log.LogTool;

import java.util.Arrays;

/**
 * Created by yaoh on 2019/3/1
 */
public class TransAnimation extends Animation {

    private static final String TAG = "TransAnimation";

    private float dx, dy;

    public TransAnimation(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
//        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        matrix.postTranslate(dx, dy);

        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        LogTool.LogE_DEBUG(TAG, "applyTransformation --->"
                + " matrixValues---> " + Arrays.toString(matrixValues));
    }
}
