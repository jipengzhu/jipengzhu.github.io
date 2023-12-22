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
    public static List<List<String>> visitAdjTableGraphByBFS(String[] vertexes, int[][] edges) {
        return visitAdjTableGraphByBFS(vertexes, toListsEdges(vertexes, edges));
    }

    public static List<List<String>> visitAdjTableGraphByDFS(String[] vertexes, int[][] edges) {
        return visitAdjTableGraphByDFS(vertexes, toListsEdges(vertexes, edges));
    }

    public static List<List<String>> visitAdjTableGraphByBFS(String[] vertexes, List<List<Integer>> edges) {
        if (vertexes.length == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        boolean[] visited = new boolean[vertexes.length];

        for (int i = 0; i < vertexes.length; i++) {
            if (visited[i]) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Queue<Integer> queue = new LinkedList<>();

            Integer p = i;

            queue.offer(p);

            // 标记为已访问
            visited[p] = true;
            list.add(vertexes[p]);

            while (!queue.isEmpty()) {
                p = queue.poll();

                for (int q : edges.get(p)) {
                    if (!visited[q]) {
                        queue.offer(q);

                        // 标记为已访问
                        visited[q] = true;
                        list.add(vertexes[q]);
                    }
                }
            }

            lists.add(list);
        }

        return lists;
    }

    public static List<List<String>> visitAdjTableGraphByDFS(String[] vertexes, List<List<Integer>> edges) {
        if (vertexes.length == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        boolean[] visited = new boolean[vertexes.length];

        for (int i = 0; i < vertexes.length; i++) {
            if (visited[i]) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Deque<Integer> stack = new LinkedList<>();

            Integer p = i;

            // 标记为已访问
            visited[p] = true;
            list.add(vertexes[p]);

            while (p != null || !stack.isEmpty()) {
                if (p != null) {
                    Integer r = null;
                    for (int q : edges.get(p)) {
                        if (!visited[q]) {
                            r = q;

                            // 标记为已访问
                            visited[q] = true;
                            list.add(vertexes[q]);

                            break;
                        }
                    }

                    if (r != null) {
                        stack.push(p); // 保存回退点

                        p = r; // 前进
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

    public static List<List<String>> visitAdjTableGraphByBFS(Map<String, Map<String, Integer>> graph) {
        if (graph.size() == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        // 不直接使用graph.keySet()是为了避免java.util.ConcurrentModificationException
        Set<String> vertexes = new HashSet<>(graph.keySet());
        for (String v : vertexes) {
            if (visited.contains(v)) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Queue<String> queue = new LinkedList<>();

            String p = v;

            queue.offer(p);

            // 标记为已访问
            visited.add(p);
            list.add(p);

            while (!queue.isEmpty()) {
                p = queue.poll();

                for (String q : graph.computeIfAbsent(p, k -> new HashMap<>()).keySet()) {
                    if (!visited.contains(q)) {
                        queue.offer(q);

                        // 标记为已访问
                        visited.add(q);
                        list.add(q);
                    }
                }
            }

            lists.add(list);
        }

        return lists;
    }

    public static List<List<String>> visitAdjTableGraphByDFS(Map<String, Map<String, Integer>> graph) {
        if (graph.size() == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        // 不直接使用graph.keySet()是为了避免java.util.ConcurrentModificationException
        Set<String> vertexes = new HashSet<>(graph.keySet());
        for (String v : vertexes) {
            if (visited.contains(v)) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Deque<String> stack = new LinkedList<>();

            String p = v;

            // 标记为已访问
            visited.add(p);
            list.add(p);

            while (p != null || !stack.isEmpty()) {
                if (p != null) {
                    String r = null;
                    for (String q : graph.computeIfAbsent(p, k -> new HashMap<>()).keySet()) {
                        if (!visited.contains(q)) {
                            r = q;

                            // 标记为已访问
                            visited.add(q);
                            list.add(q);

                            break;
                        }
                    }

                    if (r != null) {
                        stack.push(p); // 保存回退点

                        p = r; // 前进
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

    public static List<List<String>> visitAdjTableGraphByBFS(Node[] graph) {
        if (graph == null) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        for (Node node : graph) {
            if (visited.contains(node)) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Queue<Node> queue = new LinkedList<>();

            Node p = node;

            queue.offer(p);

            // 标记为已访问
            visited.add(p);
            list.add(p.val);

            while (!queue.isEmpty()) {
                p = queue.poll();

                for (Node q : p.neighbors) {
                    if (!visited.contains(q)) {
                        queue.offer(q);

                        // 标记为已访问
                        visited.add(q);
                        list.add(q.val);
                    }
                }
            }

            lists.add(list);
        }

        return lists;
    }

    public static List<List<String>> visitAdjTableGraphByDFS(Node[] graph) {
        if (graph == null) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        for (Node node : graph) {
            if (visited.contains(node)) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Deque<Node> stack = new LinkedList<>();

            Node p = node;
            // 标记为已访问
            visited.add(p);
            list.add(p.val);

            while (p != null || !stack.isEmpty()) {
                if (p != null) {
                    Node r = null;
                    for (Node q : p.neighbors) {
                        if (!visited.contains(q)) {
                            r = q;

                            // 标记为已访问
                            visited.add(q);
                            list.add(q.val);

                            break;
                        }
                    }

                    if (r != null) {
                        stack.push(p); // 保存回退点

                        p = r; // 前进
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

    public static List<List<String>> visitAdjTableGraphByBFS(Vertex[] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Queue<Integer> queue = new LinkedList<>();

            Integer p = i; // 用下标做指针

            queue.offer(p);

            // 标记为已访问
            visited[p] = true;
            list.add(graph[p].val);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int t = 0; t < size; t++) {
                    p = queue.poll();

                    Edge e = graph[p].edge;
                    while (e != null) {
                        int q = e.index;

                        if (!visited[q]) {
                            queue.offer(q);

                            // 标记为已访问
                            visited[q] = true;
                            list.add(graph[q].val);
                        }

                        e = e.next;
                    }
                }
            }

            lists.add(list);
        }

        return lists;
    }

    public static List<List<String>> visitAdjTableGraphByDFS(Vertex[] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }

            List<String> list = new LinkedList<>();

            Deque<Integer> stack = new LinkedList<>();

            Integer p = i;

            // 标记为已访问
            visited[p] = true;
            list.add(graph[p].val);

            while (p != null || !stack.isEmpty()) {
                if (p != null) {
                    Integer r = null;

                    Edge e = graph[p].edge;
                    while (e != null) {
                        int q = e.index;

                        if (!visited[q]) {
                            r = q;

                            // 标记为已访问
                            visited[q] = true;
                            list.add(graph[q].val);

                            break;
                        }

                        e = e.next;
                    }

                    if (r != null) {
                        stack.push(p); // 保存回退点

                        p = r; // 前进
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

    public static List<List<String>> visitAdjMatrixGraphByBFS(String[] vertexes, int[][] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        int rows = graph.length;
        int cols = graph[0].length;

        boolean[][] visited = new boolean[rows][cols];
        boolean[] added = new boolean[vertexes.length];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }

                if (visited[i][j]) {
                    continue;
                }

                List<String> list = new LinkedList<>();

                Queue<int[]> queue = new LinkedList<>();

                int[] p = new int[] { i, j };
                int x = p[0];
                int y = p[1];

                queue.offer(p);

                // 标记为已访问
                visited[x][y] = true;
                if (!added[x]) {
                    list.add(vertexes[x]);
                    added[x] = true;
                }
                if (!added[y]) {
                    list.add(vertexes[y]);
                    added[y] = true;
                }

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int t = 0; t < size; t++) {
                        p = queue.poll();
                        x = p[0];
                        y = p[1];

                        int[][] points = new int[][] {
                                { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                        };

                        for (int[] q : points) {
                            x = q[0];
                            y = q[1];

                            if (x < 0 || x >= rows || y < 0 || y >= cols) {
                                // 无效点，跳过
                                continue;
                            }

                            if (graph[x][y] == 0) {
                                // 无通路，跳过
                                continue;
                            }

                            if (!visited[x][y]) {
                                queue.offer(q);

                                // 标记为已访问
                                visited[x][y] = true;
                                if (!added[x]) {
                                    list.add(vertexes[x]);
                                    added[x] = true;
                                }
                                if (!added[y]) {
                                    list.add(vertexes[y]);
                                    added[y] = true;
                                }
                            }
                        }
                    }
                }

                lists.add(list);
            }
        }

        return lists;
    }

    public static List<List<String>> visitAdjMatrixGraphByDFS(String[] vertexes, int[][] graph) {
        if (graph.length == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        int rows = graph.length;
        int cols = graph[0].length;

        boolean[][] visited = new boolean[rows][cols];
        boolean[] added = new boolean[vertexes.length];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }

                if (visited[i][j]) {
                    continue;
                }

                List<String> list = new LinkedList<>();

                Deque<int[]> stack = new LinkedList<>();

                int[] p = new int[] { i, j };
                int x = p[0];
                int y = p[1];

                // 标记为已访问
                visited[x][y] = true;
                if (!added[x]) {
                    list.add(vertexes[x]);
                    added[x] = true;
                }
                if (!added[y]) {
                    list.add(vertexes[y]);
                    added[y] = true;
                }

                while (p != null || !stack.isEmpty()) {
                    if (p != null) {
                        x = p[0];
                        y = p[1];

                        int[][] points = new int[][] {
                                { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                        };

                        int[] r = null;
                        for (int[] q : points) {
                            x = q[0];
                            y = q[1];

                            if (x < 0 || x >= rows || y < 0 || y >= cols) {
                                // 无效点，跳过
                                continue;
                            }

                            if (graph[x][y] == 0) {
                                // 无通路，跳过
                                continue;
                            }

                            if (!visited[x][y]) {
                                r = q;

                                // 标记为已访问
                                visited[x][y] = true;
                                if (!added[x]) {
                                    list.add(vertexes[x]);
                                    added[x] = true;
                                }
                                if (!added[y]) {
                                    list.add(vertexes[y]);
                                    added[y] = true;
                                }

                                break;
                            }
                        }

                        if (r != null) {
                            stack.push(p); // 保存回退点

                            p = r; // 前进
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

    public static List<List<String>> visitAdjMatrixGraphByBFS(String[] vertexes,
            Map<Integer, Map<Integer, Integer>> graph) {
        if (graph.size() == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        Map<Integer, Map<Integer, Integer>> visited = new HashMap<>();
        boolean[] added = new boolean[vertexes.length];

        for (int i : graph.keySet()) {
            for (int j : graph.get(i).keySet()) {
                if (visited.computeIfAbsent(i, k -> new HashMap<>()).containsKey(j)) {
                    continue;
                }

                List<String> list = new LinkedList<>();

                Queue<int[]> queue = new LinkedList<>();

                int[] p = new int[] { i, j };
                int x = p[0];
                int y = p[1];

                queue.offer(p);

                // 标记为已访问
                visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);
                if (!added[x]) {
                    list.add(vertexes[x]);
                    added[x] = true;
                }
                if (!added[y]) {
                    list.add(vertexes[y]);
                    added[y] = true;
                }

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int t = 0; t < size; t++) {
                        p = queue.poll();
                        x = p[0];
                        y = p[1];

                        int[][] points = new int[][] {
                                { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                        };

                        for (int[] q : points) {
                            x = q[0];
                            y = q[1];

                            if (!graph.containsKey(x) || !graph.get(x).containsKey(y)) {
                                // 无通路，跳过
                                continue;
                            }

                            if (!visited.computeIfAbsent(x, k -> new HashMap<>()).containsKey(y)) {
                                queue.offer(q);

                                // 标记为已访问
                                visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);
                                if (!added[x]) {
                                    list.add(vertexes[x]);
                                    added[x] = true;
                                }
                                if (!added[y]) {
                                    list.add(vertexes[y]);
                                    added[y] = true;
                                }
                            }
                        }
                    }
                }

                lists.add(list);
            }
        }

        return lists;
    }

    public static List<List<String>> visitAdjMatrixGraphByDFS(String[] vertexes,
            Map<Integer, Map<Integer, Integer>> graph) {
        if (graph.size() == 0) {
            return new LinkedList<>();
        }

        List<List<String>> lists = new LinkedList<>();

        Map<Integer, Map<Integer, Integer>> visited = new HashMap<>();
        boolean[] added = new boolean[vertexes.length];

        for (int i : graph.keySet()) {
            for (int j : graph.get(i).keySet()) {
                if (visited.computeIfAbsent(i, k -> new HashMap<>()).containsKey(j)) {
                    continue;
                }

                List<String> list = new LinkedList<>();

                Deque<int[]> stack = new LinkedList<>();

                int[] p = new int[] { i, j };
                int x = p[0];
                int y = p[1];

                // 标记为已访问
                visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);
                if (!added[x]) {
                    list.add(vertexes[x]);
                    added[x] = true;
                }
                if (!added[y]) {
                    list.add(vertexes[y]);
                    added[y] = true;
                }

                while (p != null || !stack.isEmpty()) {
                    if (p != null) {
                        x = p[0];
                        y = p[1];

                        int[][] points = new int[][] {
                                { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                        };

                        int[] r = null;
                        for (int[] q : points) {
                            x = q[0];
                            y = q[1];

                            if (!graph.containsKey(x) || !graph.get(x).containsKey(y)) {
                                // 无通路，跳过
                                continue;
                            }

                            if (!visited.computeIfAbsent(x, k -> new HashMap<>()).containsKey(y)) {
                                r = q;

                                // 标记为已访问
                                visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);
                                if (!added[x]) {
                                    list.add(vertexes[x]);
                                    added[x] = true;
                                }
                                if (!added[y]) {
                                    list.add(vertexes[y]);
                                    added[y] = true;
                                }

                                break;
                            }
                        }

                        if (r != null) {
                            stack.push(p); // 保存回退点

                            p = r; // 前进
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

    public static List<List<Integer>> toListsEdges(String[] vertexes, int[][] edges) {
        if (vertexes.length == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();
        for (int i = 0; i < vertexes.length; i++) {
            lists.add(new LinkedList<>());
        }

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            // int w = edge[2]; // 权重

            lists.get(i).add(j);
        }

        return lists;
    }

    public static Map<String, Map<String, Integer>> toMapsGraph(String[] vertexes, int[][] edges) {
        if (vertexes.length == 0) {
            return new HashMap<>();
        }

        Map<String, Map<String, Integer>> maps = new HashMap<>();

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            int w = edge[2]; // 权重

            String u = vertexes[i];
            String v = vertexes[j];

            maps.computeIfAbsent(u, k -> new HashMap<>()).put(v, w);
        }

        return maps;
    }

    public static Node[] toNodesGraph(String[] vertexes, int[][] edges) {
        if (vertexes.length == 0) {
            return new Node[0];
        }

        Node[] array = new Node[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            array[i] = new Node(vertexes[i]);
        }

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            // int w = edge[2]; // 权重

            array[i].neighbors.add(array[j]);
        }

        return array;
    }

    public static Vertex[] toVertexesGraph(String[] vertexes, int[][] edges) {
        if (vertexes.length == 0) {
            return new Vertex[0];
        }

        Vertex[] array = new Vertex[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            array[i] = new Vertex(vertexes[i]);
        }

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            int w = edge[2]; // 权重

            Vertex vertex = array[i];
            if (vertex.edge == null) {
                Edge e = new Edge(j);
                e.weight = w;

                vertex.edge = e;
            } else {
                Edge e = new Edge(j);
                e.weight = w;

                Edge p = vertex.edge;
                while (p.next != null) {
                    p = p.next;
                }

                p.next = e;
            }
        }

        return array;
    }

    public static int[][] toArraysEdges(String[] vertexes, int[][] edges) {
        if (vertexes.length == 0) {
            return new int[0][0];
        }

        int[][] arrays = new int[vertexes.length][vertexes.length];

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            int w = edge[2]; // 权重

            arrays[i][j] = w;
        }

        return arrays;
    }

    public static Map<Integer, Map<Integer, Integer>> toMapsEdges(String[] vertexes, int[][] edges) {
        if (vertexes.length == 0) {
            return new HashMap<>();
        }

        Map<Integer, Map<Integer, Integer>> maps = new HashMap<>();

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            int w = edge[2]; // 权重

            maps.computeIfAbsent(i, k -> new HashMap<>()).put(j, w);
        }

        return maps;
    }

    static class Node {
        public String val;
        public List<Node> neighbors;

        public Node(String val) {
            this.val = val;
            this.neighbors = new java.util.ArrayList<Node>();
        }
    }

    static class Vertex {
        public String val;
        public Edge edge;

        public Vertex(String val) {
            this.val = val;
        }
    }

    static class Edge {
        public Edge next;
        public int index; // 记录边中目标顶点在顶点数组中的下标
        public int weight; // 权重

        public Edge(int index) {
            this.index = index;
        }
    }

    public static void main(String[] args) {
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByBFS(v, e));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByDFS(v, e));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByBFS(v, Graph.toListsEdges(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByDFS(v, Graph.toListsEdges(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByBFS(Graph.toMapsGraph(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByDFS(Graph.toMapsGraph(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByBFS(Graph.toNodesGraph(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByDFS(Graph.toNodesGraph(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByBFS(Graph.toVertexesGraph(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjTableGraphByDFS(Graph.toVertexesGraph(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjMatrixGraphByBFS(v, Graph.toArraysEdges(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjMatrixGraphByDFS(v, Graph.toArraysEdges(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjMatrixGraphByBFS(v, Graph.toMapsEdges(v, e)));
        GraphTest.testGraphBFS((v, e) -> Graph.visitAdjMatrixGraphByDFS(v, Graph.toMapsEdges(v, e)));
    }

    public static class GraphTest {
        public static interface GraphFunc {
            List<List<String>> apply(String[] vertexes, int[][] edges);
        }

        private static void testGraphBFS(GraphFunc gf) {
            String[] vertexes = { "a", "b", "c", "d", "e", "f", "g" };
            int[][] edges = {
                    { 0, 1, 100 },
                    { 0, 2, 200 },
                    { 1, 3, 300 },
                    { 2, 3, 400 },
                    { 6, 5, 500 },
                    { 6, 4, 600 },
                    { 5, 3, 700 },
                    { 4, 3, 800 },
            };

            List<List<String>> lists = gf.apply(vertexes, edges);
            List<String> list = new java.util.ArrayList<>();
            for (List<String> t : lists) {
                list.addAll(t);
            }

            String[] result = list.toArray(new String[0]);
            String[] expect = vertexes;

            Arrays.sort(result);
            Arrays.sort(expect);

            TestUtils.check("图", result, expect);
        }
    }

    public static class TestUtils {

        public static boolean check(String name, Object result, Object expect) {
            boolean ok = java.util.Objects.deepEquals(result, expect);
            printCheck(name, toString(result), toString(expect), ok);
            return ok;
        }

        private static String toString(Object o) {
            if (o instanceof Object[]) {
                return java.util.Arrays.deepToString((Object[]) o);
            } else if (o instanceof int[]) {
                return java.util.Arrays.toString((int[]) o);
            } else if (o instanceof double[]) {
                return java.util.Arrays.toString((double[]) o);
            } else if (o instanceof char[]) {
                return java.util.Arrays.toString((char[]) o);
            } else {
                return java.util.Objects.toString(o);
            }
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