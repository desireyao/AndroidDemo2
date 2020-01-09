package com.yaoh.JavaDemo.Dijkstra;

/**
 * Created by yaoh on 2019/3/13
 * <p>
 * 可用算法 求最短两点路径
 */
public class Dijkstra {

    public static final int M = -1;

    static int[][] map = {
            {0, 7, -1, 5, -1, -1, -1},
            {7, 0, 8, 9, 7, -1, -1},
            {-1, 8, 0, -1, 5, -1, -1},
            {5, 9, -1, 0, 15, 6, -1},
            {-1, 7, 5, 15, 0, 8, 9},
            {-1, -1, -1, 6, 8, 0, 11},
            {-1, -1, -1, -1, 9, 11, 0}
    };


    static int n = map.length;       //顶点的个数
    static int[] shortest = new int[n];  //存放从start到其他节点的最短路径
    static boolean[] visited = new boolean[n]; //标记当前该顶点的最短路径是否已经求出，true表示已经求出

    public static void main(String[] args) {
        int orig = 0; //起始点
        int[] shortPath = dijkstra_alg(orig); //寻找最短路径

        if (shortPath == null) {
            return;
        }

        for (int i = 0; i < shortPath.length; i++) {
            System.out.println("从" + (orig) + "出发到" + (i) + "的最短距离为：" + shortPath[i]);
        }
    }

    private static int[] dijkstra_alg(int orig) {
        // 初始化，第一个顶点求出
        shortest[orig] = 0;
        visited[orig] = true;

        //存放从start到其他各节点的最短路径
        String[] path = new String[n];
        for (int i = 0; i < n; i++) {
            path[i] = new String(orig + "--->" + i);
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
            //正确的图生成的矩阵不可能出现K== M的情况
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
                        path[i] = path[k] + "--->" + i;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
            System.out.println("从" + orig + "出发到" + i + "的最短路径为：" + path[i]);
        System.out.println("=====================================");

        return shortest;
    }


}
