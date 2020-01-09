package com.yaoh.JavaDemo.locAlgo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by yaoh on 2019/2/19
 */
public class LocAlgo {

    public static void main(String[] args) {
        Point point = (Point) evaluateCoordinates(
                BigDecimal.valueOf(1f), BigDecimal.valueOf(0),
                BigDecimal.valueOf(0), BigDecimal.valueOf(1),
                BigDecimal.valueOf(0), BigDecimal.valueOf(-1),
                BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1));

        System.out.print("point--->" + point);
    }



    private static void test(BigDecimal a){

    }

    /**
     * evaluateCoordinates(三点定位核心算法)
     * TODO 三点定位核心算法
     *
     * @throws
     */
    public static Point evaluateCoordinates(BigDecimal xa, BigDecimal ya,
                                            BigDecimal xb, BigDecimal yb,
                                            BigDecimal xc, BigDecimal yc,
                                            BigDecimal ra, BigDecimal rb,
                                            BigDecimal rc) {

        BigDecimal a2 = ra.multiply(ra);
        BigDecimal b2 = rb.multiply(rb);
        BigDecimal c2 = rc.multiply(rc);
        BigDecimal xa2 = xa.multiply(xa);
        BigDecimal xb2 = xb.multiply(xb);
        BigDecimal xc2 = xc.multiply(xc);
        BigDecimal ya2 = ya.multiply(ya);
        BigDecimal yb2 = yb.multiply(yb);
        BigDecimal yc2 = yc.multiply(yc);

        BigDecimal rbc = b2.subtract(c2);
        BigDecimal xbc = xb2.subtract(xc2);
        BigDecimal ybc = yb2.subtract(yc2);

        BigDecimal va = rbc.subtract(xbc).subtract(ybc).divide(new BigDecimal("2.0"));

        BigDecimal rba = b2.subtract(a2);
        BigDecimal xba = xb2.subtract(xa2);
        BigDecimal yba = yb2.subtract(ya2);

        BigDecimal vb = rba.subtract(xba).subtract(yba).divide(new BigDecimal("2.0"));

        BigDecimal xcb = xc.subtract(xb);
        BigDecimal xab = xa.subtract(xb);
        BigDecimal yab = ya.subtract(yb);
        BigDecimal ycb = yc.subtract(yb);

        BigDecimal va1 = va.multiply(xab);
        BigDecimal vb1 = vb.multiply(xcb);
        BigDecimal aa = yab.multiply(xcb);
        BigDecimal ab = ycb.multiply(xab);

        BigDecimal y1 = vb1.subtract(va1);
        BigDecimal y2 = aa.subtract(ab);
        BigDecimal y = y1.divide(y2, 10, RoundingMode.DOWN);

        BigDecimal x;
        if (xcb.signum() != 0) {
            x = va.subtract((y.multiply(ycb))).divide(xcb, 10, RoundingMode.DOWN);
        } else {
            x = vb.subtract(y.multiply(yab)).divide(xab, 10, RoundingMode.DOWN);
        }

        return new Point(x, y);
    }


}
