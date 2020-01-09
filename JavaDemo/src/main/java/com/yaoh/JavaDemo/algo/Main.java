package com.yaoh.JavaDemo.algo;

/**
 * Created by yaoh on 2019/2/26
 */
public class Main {


    public static void main(String[] args) {

//        NPoint pt1 = new NPoint(1908, 2078);
//        NPoint pt2 = new NPoint(1908, 2516);
//        NPoint pt3 = new NPoint(1300.334529876709f, 1029.8188819885254f);

        NPoint pt3 = new NPoint(1310.2778178459266f, 1251.4926815517028f);

        NPoint pt1_1 = new NPoint(1456, 2646);
        NPoint pt1_2 = new NPoint(4028, 2646);
        NPoint nPoint1 = calCrossPoint(pt1_1, pt1_2, pt3);
        System.out.println("nPoint1-------> " + nPoint1);
        float distance1 = calPointTwoDistance(nPoint1, pt3);
        System.out.println("distance1-------> " + distance1);

        NPoint pt2_1 = new NPoint(3905, 2090);
        NPoint pt2_2 = new NPoint(3905, 2512);
        NPoint nPoint2 = calCrossPoint(pt2_1, pt2_2, pt3);
        System.out.println("nPoint2-------> " + nPoint2);
        float distance2 = calPointTwoDistance(nPoint2, pt3);
        System.out.println("distance2-------> " + distance2);


        NPoint pt3_1 = new NPoint(1453, 1982);
        NPoint pt3_2 = new NPoint(4042, 1982);
        NPoint nPoint3 = calCrossPoint(pt3_1, pt3_2, pt3);
        System.out.println("nPoint3-------> " + nPoint3);
        float distance3 = calPointTwoDistance(nPoint3, pt3);
        System.out.println("distance3-------> " + distance3);


        NPoint pt4_1 = new NPoint(1597, 2143);
        NPoint pt4_2 = new NPoint(1597, 2504);
        NPoint nPoint4 = calCrossPoint(pt4_1, pt4_2, pt3);
        System.out.println("nPoint4-------> " + nPoint4);
        float distance4 = calPointTwoDistance(nPoint4, pt3);
        System.out.println("distance4-------> " + distance4);

        NPoint pt5_1 = new NPoint(1980, 2078);
        NPoint pt5_2 = new NPoint(1980, 2516);
        NPoint nPoint5 = calCrossPoint(pt5_1, pt5_2, pt3);
        System.out.println("nPoint5-------> " + nPoint5);
        float distance5 = calPointTwoDistance(nPoint5, pt3);
        System.out.println("distance5-------> " + distance5);

        NPoint pt6_1 = new NPoint(2313, 2078);
        NPoint pt6_2 = new NPoint(2313, 2525);
        NPoint nPoint6 = calCrossPoint(pt6_1, pt6_2, pt3);
        System.out.println("nPoint6-------> " + nPoint6);
        float distance6 = calPointTwoDistance(nPoint6, pt3);
        System.out.println("distance6-------> " + distance6);

        NPoint pt7_1 = new NPoint(2872, 2090);
        NPoint pt7_2 = new NPoint(2872, 2516);
        NPoint nPoint7 = calCrossPoint(pt7_1, pt7_2, pt3);
        System.out.println("nPoint7-------> " + nPoint7);
        float distance7 = calPointTwoDistance(nPoint7, pt3);
        System.out.println("distance7-------> " + distance7);
    }


    /**
     * 计算垂直线的交点
     *
     * @param pt1，pt2，pt3
     * @return
     */
    public static NPoint calCrossPoint(NPoint pt1, NPoint pt2, NPoint pt3) {
//        System.out.println("calCrossPoint ---> pt1: " + pt1 + " pt2: " + pt2 + " pt3: " + pt3);
        NPoint ptCross = new NPoint();
        if (pt1.getX() - pt2.getX() != 0) {
            float A = (pt1.getY() - pt2.getY()) / (pt1.getX() - pt2.getX());
            float B = pt1.getY() - A * pt1.getY();

            float m = pt3.getX() + A * pt3.getY();

            ptCross.setX((m - A * B) / (A * A + 1));
            ptCross.setY(A * ptCross.getX() + B);
        } else {
            ptCross.setX(pt1.getX());
            ptCross.setY(pt3.getY());
        }

        return ptCross;
    }


    /**
     * 计算两点的距离
     *
     * @param pt1
     * @param pt2
     * @return
     */
    public static float calPointTwoDistance(NPoint pt1, NPoint pt2) {
        double distance = Math.sqrt((pt1.getX() - pt2.getX()) * (pt1.getX() - pt2.getX()) +
                (pt1.getY() - pt2.getY()) * (pt1.getY() - pt2.getY()));

        return (float) distance;
    }
}
