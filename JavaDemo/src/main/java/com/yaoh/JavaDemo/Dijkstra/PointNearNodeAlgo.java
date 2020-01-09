package com.yaoh.JavaDemo.Dijkstra;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yaoh on 2019/3/18
 * <p>
 * 起始点的算法
 */
public class PointNearNodeAlgo {

    /**
     *     DijkstraAlgo.Point[] mPoints = new DijkstraAlgo.Point[12];
     *     mPoints[0] = new DijkstraAlgo.Point(1570, 1977, 1, 6);
     *     mPoints[1] = new DijkstraAlgo.Point(1972, 1977, 0, 2, 7);
     *     mPoints[2] = new DijkstraAlgo.Point(2291, 1977, 1, 3, 8);
     *     mPoints[3] = new DijkstraAlgo.Point(2842, 1977, 2, 4, 9);
     *     mPoints[4] = new DijkstraAlgo.Point(3235, 1977, 2, 5, 10);
     *     mPoints[5] = new DijkstraAlgo.Point(3908, 1977, 4, 11);
     *     mPoints[6] = new DijkstraAlgo.Point(1570, 2550, 0, 7);
     *     mPoints[7] = new DijkstraAlgo.Point(1972, 2550, 1, 6, 8);
     *     mPoints[8] = new DijkstraAlgo.Point(2291, 2550, 2, 7, 9);
     *     mPoints[9] = new DijkstraAlgo.Point(2842, 2550, 3, 8, 10);
     *     mPoints[10] = new DijkstraAlgo.Point(3235, 2550, 4, 9, 11)
     *     mPoints[11] = new DijkstraAlgo.Point(3908, 2550, 5, 10);
     */


    /**
     * 找当前点 到路网上 所有垂线最短的 垂足
     *
     * @param curPoint
     * @param points
     */
    public static CrossPoint findNearCrossPoint(DijkstraAlgo.Point curPoint, DijkstraAlgo.Point[] points) {

        if (curPoint == null || points == null) {
            throw new NullPointerException("point is null");
        }

        float tempDistance = 0;
        DijkstraAlgo.Point tempPoint = null;
        int node0 = 0;
        int node1 = 0;

        for (int i = 0; i < points.length; i++) {
            List<Integer> pointList = Arrays.asList(points[i].getRelatePoints());
            for (int j = i + 1; j < points.length; j++) {
                if (pointList.contains(j)) {
                    DijkstraAlgo.Point cPoint = calCrossPoint(points[i], points[j], curPoint);

                    // 垂足在延长线的点不要
                    if (cPoint.getX() > Math.max(points[i].getX(), points[j].getX())
                            || cPoint.getX() < Math.min(points[i].getX(), points[j].getX())
                            || cPoint.getY() > Math.max(points[i].getY(), points[j].getY())
                            || cPoint.getY() < Math.min(points[i].getY(), points[j].getY())) {

                        System.out.println("isOutLine---> cPoint: " + cPoint);
                        continue;
                    }

                    float distance = distance(cPoint, curPoint);
                    System.out.println("i: " + i + " j:" + j
                            + " cPoint: " + cPoint.toString() + " distance: " + distance);

                    if (distance < tempDistance || tempDistance == 0) {
                        tempPoint = cPoint;
                        tempDistance = distance;
                        node0 = i;
                        node1 = j;
                    } else if (distance == tempDistance) {
                        float node0Distance = distance(curPoint, points[node0]);
                        float node1Distance = distance(curPoint, points[node1]);
                        float node2Distance = distance(curPoint, points[i]);
                        float node3Distance = distance(curPoint, points[j]);

//                        System.out.println("node0Distance: " + node0Distance
//                                + " node1Distance: " + node1Distance
//                                + " node2Distance: " + node2Distance
//                                + " node3Distance: " + node3Distance
//                                + " node0: " +node0  + " node1: " + node1
//                                + " i: " + i + " j: " +j);


                        if (Math.min(node0Distance, node1Distance)
                                > Math.min(node2Distance, node3Distance)) {
                            tempPoint = cPoint;
                            tempDistance = distance;
                            node0 = i;
                            node1 = j;
                        }
                    }
                }
            }
        }

        float node0Distance = distance(curPoint, points[node0]);
        float node1Distance = distance(curPoint, points[node1]);
        int nodeIndex = (node0Distance <= node1Distance ? node0 : node1);

        System.out.println("findNearCrossPoint---> "
                + " node0: " + node0 + " node1: " + node1
                + " nodeIndex: " + nodeIndex
                + " x: " + tempPoint.getX()
                + " y: " + tempPoint.getY());

        CrossPoint point = new CrossPoint(tempPoint, nodeIndex);
        return point;
    }

    public static class CrossPoint {
        /**
         * 垂足的点
         */
        private DijkstraAlgo.Point mCrossPoint;

        /**
         * 最近的是第几个节点
         */
        private int nodeIndex;

        public CrossPoint(DijkstraAlgo.Point mCrossPoint, int nodeIndex) {
            this.mCrossPoint = mCrossPoint;
            this.nodeIndex = nodeIndex;
        }

        public DijkstraAlgo.Point getCrossPoint() {
            return mCrossPoint;
        }

        public int getNodeIndex() {
            return nodeIndex;
        }

        @Override
        public String toString() {
            return "CrossPoint{" +
                    "mCrossPoint=" + mCrossPoint +
                    ", nodeIndex=" + nodeIndex +
                    '}';
        }
    }

    /**
     * 计算垂直线的交点
     *
     * @param pt1，pt2，pt3
     * @return
     */
    public static DijkstraAlgo.Point calCrossPoint(DijkstraAlgo.Point pt1, DijkstraAlgo.Point pt2, DijkstraAlgo.Point pt3) {
//        System.out.print(" calCrossPoint ---> pt1: " + pt1 + " pt2: " + pt2 + " pt3: " + pt3);
        DijkstraAlgo.Point ptCross = new DijkstraAlgo.Point();
        if (pt1.getX() - pt2.getX() != 0) {
            float A = (pt1.getY() - pt2.getY()) / (pt1.getX() - pt2.getX());
            float B = pt1.getY() - A * pt1.getY();
            float m = pt3.getX() + A * pt3.getY();

            ptCross.setX((int) ((m - A * B) / (A * A + 1)));
            ptCross.setY((int) (A * ptCross.getX() + B));
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
    public static float distance(DijkstraAlgo.Point pt1, DijkstraAlgo.Point pt2) {
        double distance = Math.sqrt((pt1.getX() - pt2.getX()) * (pt1.getX() - pt2.getX()) +
                (pt1.getY() - pt2.getY()) * (pt1.getY() - pt2.getY()));
        return (float) distance;
    }


    private static DijkstraAlgo.Point getFoot(DijkstraAlgo.Point p1, DijkstraAlgo.Point p2, DijkstraAlgo.Point p3){
        DijkstraAlgo.Point foot = new DijkstraAlgo.Point();

        float dx=p1.getX() - p2.getX();
        float dy=p1.getY() - p2.getY();

        float u=(p3.getX() - p1.getX() )*dx+(p3.getY()-p1.getY() ) * dy;
        u/=dx*dx+dy*dy;

        foot.setX((int)(p1.getX() + u*dx));
        foot.setY((int)(p1.getY() + u*dy));

        return foot;
    }


    public static void main(String[] args){

//        mPoints[0] = new DijkstraAlgo.Point(1570, 1200);
//        mPoints[1] = new DijkstraAlgo.Point(1972, 1977);
//        mPoints[2] = new DijkstraAlgo.Point(1620, 1200);

        DijkstraAlgo.Point[] mPoints = new DijkstraAlgo.Point[3];
        mPoints[0] = new DijkstraAlgo.Point(0, 100);
        mPoints[1] = new DijkstraAlgo.Point(100,0);
        mPoints[2] = new DijkstraAlgo.Point(0, 0);

        DijkstraAlgo.Point point1 =  calCrossPoint(mPoints[0], mPoints[1],mPoints[2]);
        DijkstraAlgo.Point point2 =  getFoot( mPoints[0], mPoints[1], mPoints[2]);
        System.out.print("point1: " + point1 + " point2: " + point2);
    }

}
