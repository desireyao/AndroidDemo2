package com.yaoh.JavaDemo.locAlgo;

import java.math.BigDecimal;

/**
 * Created by yaoh on 2019/2/19
 */
public class Point {

    private BigDecimal x;
    private BigDecimal y;

    public Point(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
