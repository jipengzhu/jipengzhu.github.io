package dsa.structure.ds901_graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    public static int[] visitArraysGraphByBFS(int[][] graph) {
        if (graph.length == 0) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        int rows = graph.length;
        int cols = graph[0].length;

        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] { 0, 0 });
        // 标记为已访问
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                int x = p[0];
                int y = p[1];

                list.add(graph[x][y]);

                int[][] points = new int[][] {
                        { x - 1, y }, { x, y - 1 }, { x + 1, y }, { x, y + 1 }
                };
                for (int[] point : points) {
                    x = point[0];
                    y = point[1];

                    if (x < 0 || x >= rows || y < 0 || y >= cols) {
                        // 无效点，跳过
                        continue;
                    }

                    if (visited[x][y]) {
                        // 已访问，跳过
                        continue;
                    }

                    if (graph[x][y] > 0) {
                        queue.offer(point);

                        // 标记为已访问
                        visited[x][y] = true;
                    }
                }
            }
        }

        int[] array = new int[list.size()];
        int i = 0;
        for (int v : list) {
            array[i++] = v;
        }

        return array;
    }

    public static int[] visitArraysGraphByDFS(int[][] graph) {
        if (graph.length == 0) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        int rows = graph.length;
        int cols = graph[0].length;

        boolean[][] visited = new boolean[rows][cols];

        Deque<int[]> stack = new LinkedList<>();

        int[] p = new int[] { 0, 0 };
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                int x = p[0];
                int y = p[1];

                if (!visited[x][y]) {
                    list.add(graph[x][y]);
                }

                // 标记为已访问
                visited[x][y] = true;

                int[][] points = new int[][] {
                        { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                };

                int[] q = null;
                for (int[] point : points) {
                    x = point[0];
                    y = point[1];

                    if (x < 0 || x >= rows || y < 0 || y >= cols) {
                        // 无效点，跳过
                        continue;
                    }

                    if (visited[x][y]) {
                        // 已访问，跳过
                        continue;
                    }

                    if (graph[x][y] > 0) {
                        q = point;

                        break;
                    }
                }

                if (q != null) {
                    stack.push(p); // 保存回退点

                    p = q; // 前进
                } else {
                    // p = stack.isEmpty() ? null : stack.pop(); // 回溯
                    p = null; // 在下一次循环时跳转到回溯分支
                }
            } else {
                p = stack.pop(); // 回溯
            }
        }

        int[] array = new int[list.size()];
        int i = 0;
        for (int v : list) {
            array[i++] = v;
        }

        return array;
    }

    public static int[] visitListsGraphByBFS(List<List<Integer>> graph) {
        if (graph.size() == 0) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        List<List<Boolean>> visited = new LinkedList<>();
        for (int i = 0; i < graph.size(); i++) {
            visited.add(new LinkedList<>());

            for (int j = 0; j < graph.get(i).size(); j++) {
                visited.get(i).add(false);
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] { 0, 0 });
        // 标记为已访问
        visited.get(0).set(0, true);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                int x = p[0];
                int y = p[1];

                list.add(graph.get(x).get(y));

                int[][] points = new int[][] {
                        { x - 1, y }, { x, y - 1 }, { x + 1, y }, { x, y + 1 }
                };
                for (int[] point : points) {
                    x = point[0];
                    y = point[1];

                    if (x < 0 || x >= graph.size() || y < 0 || y >= graph.get(x).size()) {
                        // 无效点，跳过
                        continue;
                    }

                    if (visited.get(x).get(y)) {
                        // 已访问，跳过
                        continue;
                    }

                    if (graph.get(x).get(y) > 0) {
                        queue.offer(point);

                        // 标记为已访问
                        visited.get(x).set(y, true);
                    }
                }
            }
        }

        int[] array = new int[list.size()];
        int i = 0;
        for (int v : list) {
            array[i++] = v;
        }

        return array;
    }

    public static List<List<Integer>> toListsGraph(int[][] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        int rows = graph.length;
        int cols = graph[0].length;

        for (int i = 0; i < rows; i++) {

            List<Integer> list = new LinkedList<>();
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] > 0) {
                    list.add(graph[i][j]);
                }
            }
            lists.add(list);
        }

        return lists;
    }

    public static void main(String[] args) {
        GraphTest.testGraphBFS(t -> Graph.visitArraysGraphByBFS(t));
        GraphTest.testGraphBFS(t -> Graph.visitArraysGraphByDFS(t));
        GraphTest.testGraphBFS(t -> Graph.visitListsGraphByBFS(Graph.toListsGraph(t)));
    }

    public static class GraphTest {
        public static interface GraphFunc {
            int[] apply(int[][] graph);
        }

        private static void testGraphBFS(GraphFunc gf) {
            int[][] graph = new int[][] {
                    { 1, 3, 5 },
                    { 0, 7, 0 },
                    { 9, 11, 13 }
            };

            int[] result = gf.apply(graph);
            int[] expect = { 1, 3, 5, 7, 9, 11, 13 };

            Arrays.sort(result);
            Arrays.sort(expect);

            TestUtils.check("图", result, expect);
        }
    }

    public static class TestUtils {
        public static int[] random(int n) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 1000) + 1;
            }

            return array;
        }

        public static void check(String name, int[] result, int[] expect) {
            System.out.println();
            System.out.println("--->");

            System.out.println("result: " + Arrays.toString(result));
            System.out.println("expect: " + Arrays.toString(expect));

            boolean ok = Arrays.equals(result, expect);

            System.out.println();
            if (ok) {
                System.out.println("比较结果为: 正确(right)");
            } else {
                System.out.println("比较结果为: 错误(error)");
            }

            System.out.println("<---");
            System.out.println();

            if (!ok) {
                throw new RuntimeException(String.format("ERROR: [%s]的测试失败", name));
            }
        }
    }
}