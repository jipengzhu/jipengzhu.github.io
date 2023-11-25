package dsa.structure.ds901_graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Graph {
    public static List<List<Integer>> visitArraysGraphByBFS(int[][] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        int rows = graph.length;
        int cols = graph[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }

                if (visited[i][j]) {
                    continue;
                }

                List<Integer> list = new LinkedList<>();

                Queue<int[]> queue = new LinkedList<>();

                int[] p = new int[] { i, j };
                int x = p[0];
                int y = p[1];

                queue.offer(p);
                // 标记为已访问
                visited[x][y] = true;

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int t = 0; t < size; t++) {
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

                lists.add(list);
            }
        }

        return lists;
    }

    public static List<List<Integer>> visitArraysGraphByDFS(int[][] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        int rows = graph.length;
        int cols = graph[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }

                if (visited[i][j]) {
                    continue;
                }

                List<Integer> list = new LinkedList<>();

                Deque<int[]> stack = new LinkedList<>();

                int[] p = new int[] { i, j };
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

                lists.add(list);
            }
        }

        return lists;
    }

    public static List<List<Integer>> visitMapsGraphByBFS(Map<Integer, Map<Integer, Integer>> graph) {
        if (graph.size() == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        Map<Integer, Map<Integer, Boolean>> visited = new HashMap<>();

        for (int i : graph.keySet()) {
            for (int j : graph.get(i).keySet()) {
                if (graph.get(i).get(j) == 0) {
                    continue;
                }

                if (visited.computeIfAbsent(i, k -> new HashMap<>()).containsKey(j)) {
                    continue;
                }

                List<Integer> list = new LinkedList<>();

                Queue<int[]> queue = new LinkedList<>();

                int[] p = new int[] { i, j };
                int x = p[0];
                int y = p[1];

                queue.offer(p);
                // 标记为已访问
                visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int t = 0; t < size; t++) {
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

                lists.add(list);
            }
        }

        return lists;
    }

    public static List<List<Integer>> visitMapsGraphByDFS(Map<Integer, Map<Integer, Integer>> graph) {
        if (graph.size() == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        Map<Integer, Map<Integer, Boolean>> visited = new HashMap<>();

        for (int i : graph.keySet()) {
            for (int j : graph.get(i).keySet()) {
                if (graph.get(i).get(j) == 0) {
                    continue;
                }

                if (visited.computeIfAbsent(i, k -> new HashMap<>()).containsKey(j)) {
                    continue;
                }

                List<Integer> list = new LinkedList<>();

                Deque<int[]> stack = new LinkedList<>();

                int[] p = new int[] { i, j };
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

                lists.add(list);
            }
        }

        return lists;
    }

    public static List<List<Integer>> visitNodesGraphByBFS(Node[] graph) {
        if (graph == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        for (Node node : graph) {
            if (visited.contains(node)) {
                continue;
            }

            List<Integer> list = new LinkedList<>();

            Queue<Node> queue = new LinkedList<>();

            Node p = node;

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

            lists.add(list);
        }

        return lists;
    }

    public static List<List<Integer>> visitNodesGraphByDFS(Node[] graph) {
        if (graph == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        for (Node node : graph) {
            if (visited.contains(node)) {
                continue;
            }

            List<Integer> list = new LinkedList<>();

            Deque<Node> stack = new LinkedList<>();

            Node p = node;
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

            lists.add(list);
        }

        return lists;
    }

    public static List<List<Integer>> visitVertexesGraphByBFS(Vertex[] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }

            List<Integer> list = new LinkedList<>();

            Queue<Integer> queue = new LinkedList<>();

            Integer p = i; // 用下标做指针

            queue.offer(p);
            // 标记为已访问
            visited[p] = true;

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int t = 0; t < size; t++) {
                    p = queue.poll();

                    list.add(graph[p].val);

                    Edge e = graph[p].dummy;
                    while (e.next != null) {
                        e = e.next;

                        if (visited[e.index]) {
                            // 已访问，跳过
                            continue;
                        }

                        p = e.index;

                        queue.offer(p);

                        // 标记为已访问
                        visited[p] = true;
                    }
                }
            }

            lists.add(list);
        }

        return lists;
    }

    public static List<List<Integer>> visitVertexesGraphByDFS(Vertex[] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }

            List<Integer> list = new LinkedList<>();

            Deque<Integer> stack = new LinkedList<>();

            Integer p = i;
            while (p != null || !stack.isEmpty()) {
                if (p != null) {
                    if (!visited[p]) {
                        list.add(graph[p].val);
                    }

                    // 标记为已访问
                    visited[p] = true;

                    Integer q = null;
                    Edge e = graph[p].dummy;
                    while (e.next != null) {
                        e = e.next;

                        if (visited[e.index]) {
                            // 已访问，跳过
                            continue;
                        }

                        q = e.index;

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

            lists.add(list);
        }

        return lists;
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

    public static Node[] toNodesGraph(int[][] graph) {
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

        return list.toArray(new Node[0]);
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
            List<List<Integer>> apply(int[][] graph);
        }

        private static void testGraphBFS(GraphFunc gf) {
            int[][] graph = new int[][] {
                    { 1, 3, 5 },
                    { 0, 7, 0 },
                    { 9, 11, 13 }
            };

            List<List<Integer>> result = gf.apply(graph);
            Integer[][] expect = { { 1, 3, 5, 7, 9, 11, 13 } };

            for (List<Integer> t : result) {
                t.sort(null);
            }
            for (Integer[] t : expect) {
                Arrays.sort(t);
            }

            TestUtils.check("图", result, expect, Integer.class);
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
            String resultString = Arrays.toString(result);
            String expectString = Arrays.toString(expect);
            boolean ok = Arrays.equals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static void check(String name, Object[] result, Object[] expect) {
            String resultString = Arrays.deepToString(result);
            String expectString = Arrays.deepToString(expect);
            boolean ok = Arrays.deepEquals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static void check(String name, Object result, Object expect) {
            String resultString = result.toString();
            String expectString = expect.toString();
            boolean ok = Objects.equals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static <T> void check(String name, java.util.List<T> result, T[] expect, Class<T> clazz) {
            check(name, toArray(result, clazz), expect);
        }

        public static <T> void check(String name, java.util.List<java.util.List<T>> result, T[][] expect,
                Class<T> clazz) {
            check(name, toArrays(result, clazz), expect);
        }

        @SuppressWarnings("unchecked")
        public static <T> T[] genArray(Class<T> clazz, int length) {
            return (T[]) java.lang.reflect.Array.newInstance(clazz, length);
        }

        @SuppressWarnings("unchecked")
        public static <T> T[][] genArrays(Class<T> clazz, int rows, int cols) {
            T[] array = genArray(clazz, 0);

            T[][] arrays = (T[][]) genArray(array.getClass(), rows);
            for (int i = 0; i < arrays.length; i++) {
                arrays[i] = genArray(clazz, cols);
            }

            return arrays;
        }

        public static <T> T[] toArray(java.util.List<T> list, Class<T> clazz) {
            if (list == null || list.isEmpty()) {
                return genArray(clazz, 0);
            }

            T[] array = genArray(clazz, list.size());
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

            return array;
        }

        public static <T> T[][] toArrays(java.util.List<java.util.List<T>> lists, Class<T> clazz) {
            if (lists == null || lists.isEmpty()) {
                return genArrays(clazz, 0, 0);
            }

            T[][] arrays = genArrays(clazz, lists.size(), 0);
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = toArray(lists.get(i), clazz);
            }

            return arrays;
        }

        public static void printCheck(String name, String result, String expect, boolean ok) {
            System.out.println();
            System.out.println("--->");

            System.out.println("result: " + result);
            System.out.println("expect: " + expect);

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