package com.yaoh.JavaDemo.algo;

/**
 * Created by yaoh on 2018/9/27.
 */

public class NPoint {

    private float x;
    private float y;

    public NPoint() {
    }

    public NPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "NPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
