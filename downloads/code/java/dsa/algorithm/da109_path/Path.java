package dsa.algorithm.da109_path;

public class Path {
    /**
     * 最短路径问题之Dijkstra算法（贪心算法）
     */
    public static int Dijkstra(int[][] graph) {
        int n = graph.length;
        int s = 0;
        int t = n - 1;

        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        boolean[] used = new boolean[n];

        while (true) {
            int i = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && (i == -1 || dist[j] < dist[i])) {
                    i = j;
                }
            }

            if (i == -1) {
                break;
            } else {
                used[i] = true;
            }

            for (int j = 0; j < n; j++) {
                if (graph[i][j] == Integer.MAX_VALUE) {
                    // 无通路，跳过
                    continue;
                }

                // 更新从i到达j点时k到j的最短距离
                if (dist[i] + graph[i][j] < dist[j]) {
                    dist[j] = dist[i] + graph[i][j];
                }
            }
        }

        return dist[t];
    }

    /**
     * 最短路径问题之Floyd算法（动态规划）
     */
    public static int Floyd(int[][] graph) {
        int n = graph.length;
        int s = 0;
        int t = n - 1;

        int[][] dists = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dists[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dists[i][k] == Integer.MAX_VALUE || dists[k][j] == Integer.MAX_VALUE) {
                        // 不可能变小，跳过
                        continue;
                    }

                    if (dists[i][k] + dists[k][j] < dists[i][j]) {
                        dists[i][j] = dists[i][k] + dists[k][j];
                    }
                }
            }
        }

        return dists[s][t];
    }

    /**
     * 旅行商问题（回溯算法）
     */
    public static int[] Tps(int[][] graph) {

        int n = graph.length;

        int[] path = new int[n + 1];
        int[] minCost = { Integer.MAX_VALUE };

        boolean[] visited = new boolean[n];
        int[] trail = new int[n];

        int start = 0;

        visited[start] = true;
        trail[0] = start;
        path[n] = start;

        dfs(graph, start, 0, minCost, 1, n, visited, trail, path);

        // int val = 0;
        // for (int i = 0; i < ans.length - 1; i++) {
        // val += graph[ans[i]][ans[i + 1]];
        // }
        // System.out.println(val);
        // System.out.println(minCost[0]);
        // System.out.println(val == minCost[0]);

        return path;
    }

    /**
     * 
     * @param graph
     * @param v       当前顶点序号
     * @param curCost 到达当前顶点时的开销
     * @param minCost 回到起点时的最小开销
     * @param k       当前是第几个顶点（从1开始数）
     * @param n       总共有多少个顶点
     * @param visited 记录结点的访问状态
     * @param trail   记录结点的前进轨迹
     * @param path    记录最终的结果路径
     */
    private static void dfs(int[][] graph, int v, int curCost, int[] minCost, int k, int n, boolean[] visited,
            int[] trail, int[] path) {
        // 当前开销已经比最小值大了，需要进行剪枝
        if (curCost > minCost[0]) {
            return;
        }

        if (k == n) {
            int start = 0;
            if (curCost + graph[v][start] < minCost[0]) {
                minCost[0] = curCost + graph[v][start];

                int index = 0;
                for (int t : trail) {
                    path[index++] = t;
                }
            }

            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                trail[k] = i;

                visited[i] = true;
                dfs(graph, i, curCost + graph[v][i], minCost, k + 1, n, visited, trail, path);
                visited[i] = false; // 回溯时撤销选择
            }
        }
    }

    public static void main(String[] args) {

    }
}