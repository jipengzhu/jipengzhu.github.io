package dsa.structure.ds901_graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

        int[] p = new int[] { 0, 0 };
        int x = p[0];
        int y = p[1];

        queue.offer(p);
        // 标记为已访问
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                p = queue.poll();
                x = p[0];
                y = p[1];

                list.add(graph[x][y]);

                int[][] points = new int[][] {
                        { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
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

    public static int[] visitMapsGraphByBFS(Map<Integer, Map<Integer, Integer>> graph) {
        if (graph.size() == 0) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        Map<Integer, Map<Integer, Boolean>> visited = new HashMap<>();

        Queue<int[]> queue = new LinkedList<>();

        int[] p = new int[] { 0, 0 };
        int x = p[0];
        int y = p[1];

        queue.offer(p);
        // 标记为已访问
        visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                p = queue.poll();
                x = p[0];
                y = p[1];

                list.add(graph.get(x).get(y));

                int[][] points = new int[][] {
                        { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                };

                for (int[] point : points) {
                    x = point[0];
                    y = point[1];

                    if (!graph.containsKey(x) || !graph.get(x).containsKey(y)) {
                        // 无效点，跳过
                        continue;
                    }

                    if (visited.computeIfAbsent(x, k -> new HashMap<>()).containsKey(y)) {
                        // 已访问，跳过
                        continue;
                    }

                    if (graph.get(x).get(y) > 0) {
                        queue.offer(point);

                        // 标记为已访问
                        visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);
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

    public static int[] visitMapsGraphByDFS(Map<Integer, Map<Integer, Integer>> graph) {
        if (graph.size() == 0) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        Map<Integer, Map<Integer, Boolean>> visited = new HashMap<>();

        Deque<int[]> stack = new LinkedList<>();

        int[] p = new int[] { 0, 0 };
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                int x = p[0];
                int y = p[1];

                if (!visited.computeIfAbsent(x, k -> new HashMap<>()).containsKey(y)) {
                    list.add(graph.get(x).get(y));
                }

                // 标记为已访问
                visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);

                int[][] points = new int[][] {
                        { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                };

                int[] q = null;
                for (int[] point : points) {
                    x = point[0];
                    y = point[1];

                    if (!graph.containsKey(x) || !graph.get(x).containsKey(y)) {
                        // 无效点，跳过
                        continue;
                    }

                    if (visited.computeIfAbsent(x, k -> new HashMap<>()).containsKey(y)) {
                        // 已访问，跳过
                        continue;
                    }

                    if (graph.get(x).get(y) > 0) {
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

    public static int[] visitNodesGraphByBFS(Node graph) {
        if (graph == null) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        Queue<Node> queue = new LinkedList<>();

        Node p = graph;

        queue.offer(p);
        // 标记为已访问
        visited.add(p);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                p = queue.poll();

                list.add(p.val);

                for (Node neighbor : p.neighbors) {
                    if (visited.contains(neighbor)) {
                        // 已访问，跳过
                        continue;
                    }

                    queue.offer(neighbor);

                    // 标记为已访问
                    visited.add(neighbor);
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

    public static int[] visitNodesGraphByDFS(Node graph) {
        if (graph == null) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        Deque<Node> stack = new LinkedList<>();

        Node p = graph;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                if (!visited.contains(p)) {
                    list.add(p.val);
                }

                // 标记为已访问
                visited.add(p);

                Node q = null;
                for (Node neighbor : p.neighbors) {
                    if (visited.contains(neighbor)) {
                        // 已访问，跳过
                        continue;
                    }

                    q = neighbor;

                    break;
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

    public static int[] visitVertexesGraphByBFS(Vertex[] graph) {
        if (graph.length == 0) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        boolean[] visited = new boolean[graph.length];

        Queue<Vertex> queue = new LinkedList<>();

        Vertex p = graph[0];

        queue.offer(p);
        // 标记为已访问
        visited[p.index] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                p = queue.poll();

                list.add(p.val);

                Edge r = p.dummy;
                while (r.next != null) {
                    r = r.next;

                    if (visited[r.index]) {
                        // 已访问，跳过
                        continue;
                    }

                    p = graph[r.index];

                    queue.offer(p);

                    // 标记为已访问
                    visited[p.index] = true;
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

    public static int[] visitVertexesGraphByDFS(Vertex[] graph) {
        if (graph == null) {
            return new int[0];
        }

        List<Integer> list = new LinkedList<>();

        boolean[] visited = new boolean[graph.length];

        Deque<Vertex> stack = new LinkedList<>();

        Vertex p = graph[0];
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                if (!visited[p.index]) {
                    list.add(p.val);
                }

                // 标记为已访问
                visited[p.index] = true;

                Vertex q = null;
                Edge r = p.dummy;
                while (r.next != null) {
                    r = r.next;

                    if (visited[r.index]) {
                        // 已访问，跳过
                        continue;
                    }

                    q = graph[r.index];

                    break;
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

    public static Map<Integer, Map<Integer, Integer>> toMapsGraph(int[][] graph) {
        if (graph.length == 0) {
            return new HashMap<>();
        }

        Map<Integer, Map<Integer, Integer>> maps = new HashMap<>();

        int rows = graph.length;
        int cols = graph[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] > 0) {
                    maps.computeIfAbsent(i, k -> new HashMap<>()).put(j, graph[i][j]);
                }
            }
        }

        return maps;
    }

    public static Node toNodesGraph(int[][] graph) {
        if (graph.length == 0) {
            return null;
        }

        int rows = graph.length;
        int cols = graph[0].length;

        List<Node> list = new LinkedList<>();

        Map<Integer, Map<Integer, Node>> caches = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] > 0) {
                    Node node = new Node(graph[i][j]);

                    list.add(node);

                    caches.computeIfAbsent(i, k -> new HashMap<>()).put(j, node);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] > 0) {
                    Node node = caches.get(i).get(j);

                    int x = i;
                    int y = j;

                    int[][] points = new int[][] {
                            { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                    };

                    for (int[] point : points) {
                        x = point[0];
                        y = point[1];

                        if (x < 0 || x >= rows || y < 0 || y >= cols) {
                            // 无效点，跳过
                            continue;
                        }

                        if (graph[x][y] > 0) {
                            Node neighbor = caches.get(x).get(y);

                            node.neighbors.add(neighbor);
                        }
                    }
                }
            }
        }

        return list.get(0);
    }

    public static Vertex[] toVertexesGraph(int[][] graph) {
        if (graph.length == 0) {
            return new Vertex[0];
        }

        int rows = graph.length;
        int cols = graph[0].length;

        List<Vertex> list = new LinkedList<>();

        Map<Integer, Map<Integer, Integer>> caches = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] > 0) {
                    Vertex vertex = new Vertex(graph[i][j]);
                    vertex.index = list.size();

                    list.add(vertex);

                    caches.computeIfAbsent(i, k -> new HashMap<>()).put(j, list.size() - 1);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] > 0) {
                    Vertex vertex = list.get(caches.get(i).get(j));

                    Edge p = vertex.dummy;

                    int x = i;
                    int y = j;

                    int[][] points = new int[][] {
                            { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                    };

                    for (int[] point : points) {
                        x = point[0];
                        y = point[1];

                        if (x < 0 || x >= rows || y < 0 || y >= cols) {
                            // 无效点，跳过
                            continue;
                        }

                        if (graph[x][y] > 0) {
                            Edge edge = new Edge(caches.get(x).get(y));

                            p.next = edge;
                            p = p.next;
                        }
                    }
                }
            }
        }

        return list.toArray(new Vertex[0]);
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new java.util.ArrayList<Node>();
        }
    }

    static class Vertex {
        public int val;
        public int index; // 记录顶点在顶点数组中的下标
        public Edge dummy = new Edge(-1);

        public Vertex(int val) {
            this.val = val;
        }
    }

    static class Edge {
        public Edge next;
        public int index; // 记录关联的顶点在顶点数组中的下标

        public Edge(int index) {
            this.index = index;
        }
    }

    public static void main(String[] args) {
        GraphTest.testGraphBFS(t -> Graph.visitArraysGraphByBFS(t));
        GraphTest.testGraphBFS(t -> Graph.visitArraysGraphByDFS(t));
        GraphTest.testGraphBFS(t -> Graph.visitMapsGraphByBFS(Graph.toMapsGraph(t)));
        GraphTest.testGraphBFS(t -> Graph.visitMapsGraphByDFS(Graph.toMapsGraph(t)));
        GraphTest.testGraphBFS(t -> Graph.visitNodesGraphByBFS(Graph.toNodesGraph(t)));
        GraphTest.testGraphBFS(t -> Graph.visitNodesGraphByDFS(Graph.toNodesGraph(t)));
        GraphTest.testGraphBFS(t -> Graph.visitVertexesGraphByBFS(Graph.toVertexesGraph(t)));
        GraphTest.testGraphBFS(t -> Graph.visitVertexesGraphByDFS(Graph.toVertexesGraph(t)));
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