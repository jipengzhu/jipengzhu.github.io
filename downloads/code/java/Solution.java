import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Set<Node> visited = new HashSet<>();
        Map<Node, Node> caches = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        caches.put(node, new Node(node.val));
        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                Node newNode = caches.get(curNode);

                for (Node neighbor : curNode.neighbors) {
                    Node newNeighbor = caches.get(neighbor);
                    if (newNeighbor == null) {
                        newNeighbor = new Node(neighbor.val);
                        caches.put(neighbor, newNeighbor);
                    }

                    newNode.neighbors.add(newNeighbor);

                    if (visited.contains(neighbor)) {
                        continue;
                    }

                    queue.offer(neighbor);
                    visited.add(neighbor);
                }

                visited.add(curNode);
            }
        }

        return caches.get(node);
    }

    static class Node {
        public int val;
        public java.util.List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new java.util.ArrayList<Node>();
        }

        public static Node genGraph(int[][] adjList) {
            if (adjList.length == 0) {
                return null;
            }

            java.util.Map<Integer, Node> map = new java.util.HashMap<>();
            java.util.List<Node> list = new java.util.ArrayList<>();

            int v;

            for (int i = 0; i < adjList.length; i++) {
                v = i + 1;

                Node node = map.get(v);
                if (node == null) {
                    node = new Node(v);
                    map.put(v, node);
                }

                list.add(node);

                int[] array = adjList[i];

                for (int j = 0; j < array.length; j++) {
                    v = array[j];

                    Node neighbor = map.get(v);
                    if (neighbor == null) {
                        neighbor = new Node(v);
                        map.put(v, neighbor);
                    }

                    node.neighbors.add(neighbor);
                }
            }

            return list.get(0);
        }

        public static void printNode(Node node) {
            if (node == null) {
                System.out.println(node);

                return;
            }

            java.util.List<String> list = new java.util.ArrayList<>();

            java.util.Set<Node> visited = new java.util.HashSet<>();
            java.util.Queue<Node> queue = new java.util.LinkedList<>();

            queue.offer(node);
            visited.add(node);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node curNode = queue.poll();

                    java.util.List<String> tmp = new java.util.ArrayList<>();

                    for (Node neighbor : curNode.neighbors) {
                        String s = String.format("%2s@%25s", neighbor.val, neighbor);
                        tmp.add(s);

                        if (visited.contains(neighbor)) {
                            continue;
                        }

                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }

                    list.add("[" + String.join(",", tmp) + "]");
                }
            }

            System.out.println("[" + String.join(",", list) + "]");
        }

        public static void printResult(Node node, Node result) {
            System.out.println("--->");
            Node.printNode(node);
            System.out.println();
            Node.printNode(result);
            System.out.println("<---");
        }
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        int[][] adjList = {
                { 2, 4 },
                { 1, 3 },
                { 2, 4 },
                { 1, 3 }
        };

        Node node = Node.genGraph(adjList);
        Node result = new Solution().cloneGraph(node);

        Node.printResult(node, result);
    }

    public static void testCase2() {
        int[][] adjList = { {} };

        Node node = Node.genGraph(adjList);
        Node result = new Solution().cloneGraph(node);

        Node.printResult(node, result);
    }

    public static void testCase3() {
        int[][] adjList = {};

        Node node = Node.genGraph(adjList);
        Node result = new Solution().cloneGraph(node);

        Node.printResult(node, result);
    }
}