package com.yaoh.JavaDemo.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yaoh on 2019/3/13
 */
public class DijkstraAlgo {
    public static final int M = -1;

    private int[][] map;
    private int n;                 // 顶点的个数
    private int[] shortest;        // 存放从 start 到其他节点的最短路径
    private boolean[] visited;     // 标记当前该顶点的最短路径是否已经求出，true表示已经求出

    public List<Integer> dijkstraAlgo(int start,int end, Point[] points){
        HashMap<Integer, List<Integer>> shortPath = dijkstra_algo(start,points);
        List<Integer> pathList = shortPath.get(end);
        return  pathList;
    }


    private HashMap<Integer, List<Integer>> dijkstra_algo(int orig, Point[] points) {
        map = createMap(points);

        n = map.length;
        shortest = new int[n];
        visited = new boolean[n];

        // 初始化，第一个顶点求出
        shortest[orig] = 0;
        visited[orig] = true;

        //存放从start到其他各节点的最短路径
        String[] path = new String[n];

        for (int i = 0; i < n; i++) {
            path[i] = new String(orig + "," + i);
        }

        for (int count = 0; count != n - 1; count++) {
            //选出一个距离初始顶点最近的为标记顶点
            int k = M;
            int min = M;
            for (int i = 0; i < n; i++)//遍历每一个顶点
            {
                if (!visited[i] && map[orig][i] != M) //如果该顶点未被遍历过且与orig相连
                {
                    if (min == -1 || min > map[orig][i]) //找到与orig最近的点
                    {
                        min = map[orig][i];
                        k = i;
                    }
                }
            }
            //正确的图生成的矩阵不可能出现 K==M 的情况
            if (k == M) {
                System.out.println("the input map matrix is wrong!");
                return null;
            }

            shortest[k] = min;
            visited[k] = true;

            //以k为中心点，更新oirg到未访问点的距离
            for (int i = 0; i < n; i++) {
                if (!visited[i] && map[k][i] != M) {
                    int callen = min + map[k][i];
                    if (map[orig][i] == M || map[orig][i] > callen) {
                        map[orig][i] = callen;
                        path[i] = path[k] + "," + i;
                    }
                }
            }
        }

        System.out.println("======================");
        HashMap<Integer, List<Integer>> pathMaps = new HashMap<>();
        for (int i = 0; i < n; i++) {
            System.out.println("从" + orig + "出发到" + i + "的最短路径为：" + path[i]);
            String[] paths = path[i].split(",");
            List<Integer> pathList = new ArrayList<>();
            for (int j = 0; j < paths.length; j++) {
                pathList.add(Integer.parseInt(paths[j]));
            }
            pathMaps.put(i, pathList);
        }

        System.out.println("======================");
        for (int i = 0; i < shortest.length; i++) {
            System.out.println("从" + (orig) + "出发到" + (i) + "的最短距离为：" + shortest[i]);
        }

//        System.out.println("paths: " + pathMaps.toString());
//        String s = Arrays.deepToString(map);
//        System.out.println("111-> " + s);

        return pathMaps;
    }


    private Point[] mPoints;

    public int[][] createMap(Point[] _points) {
        mPoints = _points;

        int[][] map = new int[mPoints.length][mPoints.length];
        for (int i = 0; i < mPoints.length; i++) {
            int[] mapRow = new int[mPoints.length];
            List<Integer> points = Arrays.asList(mPoints[i].getRelatePoints());
            for (int j = 0; j < mPoints.length; j++) {
                // 默认输入数据的时候 是从1开始 所以此处需加1
                if (points.contains(j)) {
                    mapRow[j] = distance(i, j);
                } else {
                    mapRow[j] = -1;
                }
            }
            map[i] = mapRow;
        }

        // ------------打印矩阵-----------
//        String s = Arrays.deepToString(map);
//        System.out.println(s);

        return map;
    }

    /**
     * 计算两点的距离
     * @param index1
     * @param index2
     * @return
     */
    private int distance(int index1, int index2) {
        double r = Math.sqrt(
                (mPoints[index1].getX() - mPoints[index2].getX())
                        * (mPoints[index1].getX() - mPoints[index2].getX())
                        + (mPoints[index1].getY() - mPoints[index2].getY())
                        * (mPoints[index1].getY() - mPoints[index2].getY())
        );

        return (int) r;
    }


    public static class Point {
        private int x;
        private int y;
        Integer[] relatePoints; // 可以通过的点

        public Point() {
        }

        public Point(int x, int y, Integer... relatePoints) {
            this.x = x;
            this.y = y;
            this.relatePoints = relatePoints;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Integer[] getRelatePoints() {
            return relatePoints;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
