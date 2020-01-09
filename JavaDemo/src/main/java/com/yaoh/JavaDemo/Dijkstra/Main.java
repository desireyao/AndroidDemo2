package com.yaoh.JavaDemo.Dijkstra;

import java.util.List;

/**
 * Created by yaoh on 2019/3/13
 */
public class Main {

    public static void main(String[] args) {
        DijkstraAlgo.Point[] mPoints = new DijkstraAlgo.Point[12];
        mPoints[0] = new DijkstraAlgo.Point(1570, 1977, 1, 6);
        mPoints[1] = new DijkstraAlgo.Point(1972, 1977, 0, 2, 7);
        mPoints[2] = new DijkstraAlgo.Point(2291, 1977, 1, 3, 8);
        mPoints[3] = new DijkstraAlgo.Point(2842, 1977, 2, 4, 9);
        mPoints[4] = new DijkstraAlgo.Point(3235, 1977, 3, 5, 10);
        mPoints[5] = new DijkstraAlgo.Point(3908, 1977, 4, 11);
        mPoints[6] = new DijkstraAlgo.Point(1570, 2639, 0, 7);
        mPoints[7] = new DijkstraAlgo.Point(1972, 2639, 1, 6, 8);
        mPoints[8] = new DijkstraAlgo.Point(2291, 2639, 2, 7, 9);
        mPoints[9] = new DijkstraAlgo.Point(2842, 2639, 3, 8, 10);
        mPoints[10] = new DijkstraAlgo.Point(3235, 2639, 4, 9, 11);
        mPoints[11] = new DijkstraAlgo.Point(3908, 2639, 5, 10);

//        DijkstraAlgo.Point startPoint = new DijkstraAlgo.Point(1570, 2300);
//        DijkstraAlgo.Point endPoint = new DijkstraAlgo.Point(3500, 2800);
//        PointNearNodeAlgo.CrossPoint cStart = PointNearNodeAlgo.findNearCrossPoint(startPoint, mPoints);
//        PointNearNodeAlgo.CrossPoint cEnd = PointNearNodeAlgo.findNearCrossPoint(endPoint, mPoints);
//        System.out.println(" crossPoint--->\n start: " + cStart.toString() + "\n end: " + cEnd.toString());

        DijkstraAlgo dijkstraAlgo = new DijkstraAlgo();
        List<Integer> shortPath = dijkstraAlgo.dijkstraAlgo(2,1, mPoints);
        System.out.println("shortPath---> " + shortPath.toString());
    }

}
