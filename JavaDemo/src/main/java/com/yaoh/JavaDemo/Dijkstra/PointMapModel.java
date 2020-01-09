//package com.yaoh.JavaDemo.Dijkstra;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by yaoh on 2019/3/14
// */
//public class PointMapModel {
//
//    private Point[] mPoints;
//
//    private void addData() {
//        mPoints = new Point[12];
//        mPoints[0] = new Point(1570, 1977, 2, 7);
//        mPoints[1] = new Point(1972, 1977, 1, 3, 8);
//        mPoints[2] = new Point(2291, 1977, 2, 4, 9);
//        mPoints[3] = new Point(2842, 1977, 3, 5, 10);
//        mPoints[4] = new Point(3235, 1977, 3, 6, 11);
//        mPoints[5] = new Point(3908, 1977, 5, 12);
//        mPoints[6] = new Point(1570, 2550, 1, 8);
//        mPoints[7]  = new Point(1972, 2550, 2, 7,9);
//        mPoints[8]  = new Point(2291, 2550, 3, 8,10);
//        mPoints[9]  = new Point(2842, 2550, 4, 9,11);
//        mPoints[10]  = new Point(3235, 2550, 5, 10,12);
//        mPoints[11] = new Point(3908, 2550, 6,11);
//    }
//
//    public int[][] createMap() {
//        addData();
//        int[][] map = new int[mPoints.length][mPoints.length];
//        for (int i = 0; i < mPoints.length; i++) {
//            int[] mapRow = new int[mPoints.length];
//            List<Integer> points = Arrays.asList(mPoints[i].getRelatePoints());
//            for (int j = 0; j < mPoints.length; j++) {
//                // 默认输入数据的时候 是从1开始 所以此处需加1
//                if (points.contains(j + 1)) {
//                    mapRow[j] = distance(i,j);
//                } else {
//                    mapRow[j] = -1;
//                }
//            }
//            map[i] = mapRow;
//        }
//        return  map;
//    }
//
//
//    private int distance(int index1, int index2) {
//        double r = Math.sqrt(
//                  (mPoints[index1].getX() - mPoints[index2].getX())
//                * (mPoints[index1].getX() - mPoints[index2].getX())
//                + (mPoints[index1].getY() - mPoints[index2].getY())
//                * (mPoints[index1].getY() - mPoints[index2].getY())
//        );
//        return (int) r;
//    }
//
//
//
//
//}
