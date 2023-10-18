import java.util.HashMap;
import java.util.Map;

public class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> caches = new HashMap<>();

        Node p = head;
        while (p != null) {
            Node node = new Node(p.val);
            caches.put(p, node);

            p = p.next;
        }

        p = head;
        while (p != null) {
            Node node = caches.get(p);
            if (p.next != null) {
                node.next = caches.get(p.next);
            }
            if (p.random != null) {
                node.random = caches.get(p.random);
            }

            p = p.next;
        }

        return caches.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public static Node genLinkedList(int[][] arrays) {
            if (arrays.length == 0) {
                return null;
            }

            Node[] caches = new Node[arrays.length];

            for (int i = 0; i < arrays.length; i++) {
                int v = arrays[i][0];
                caches[i] = new Node(v);
            }

            for (int i = 0; i < arrays.length; i++) {
                Node node = caches[i];

                int j = i + 1;
                if (j < arrays.length) {
                    node.next = caches[j];
                }

                int k = arrays[i][1];
                if (k >= 0) {
                    node.random = caches[k];
                }
            }

            return caches[0];
        }

        public static Map<Node, Integer> getIndexMap(Node head) {
            Map<Node, Integer> map = new HashMap<>();

            int index = 0;

            Node p = head;
            while (p != null) {
                map.put(p, index++);
                p = p.next;
            }

            return map;
        }

        public static void printLinkedList(Node head) {
            Map<Node, Integer> indexMap = getIndexMap(head);

            Node p = head;
            while (p != null) {
                String nodeValue = p.val + "";
                String randomValue = p.random != null ? p.random.val + "" : "null";
                String randomIndex = p.random != null ? indexMap.get(p.random) + "" : "-1";

                String s = String.format("node: %25s => [%5s], random: %25s => [%5s], randomIndex: %s",
                        p, nodeValue, p.random, randomValue, randomIndex);
                System.out.println(s);

                p = p.next;
            }
        }
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        int[][] arrays = {
                { 7, -1 },
                { 13, 0 },
                { 11, 4 },
                { 10, 2 },
                { 1, 0 }
        };

        Node head = Node.genLinkedList(arrays);
        Node result = new Solution().copyRandomList(head);

        System.out.println();
        Node.printLinkedList(head);
        System.out.println();
        Node.printLinkedList(result);
        System.out.println();
    }

    public static void testCase2() {
        int[][] arrays = {
                { 1, 1 },
                { 2, 1 }
        };

        Node head = Node.genLinkedList(arrays);
        Node result = new Solution().copyRandomList(head);

        System.out.println();
        Node.printLinkedList(head);
        System.out.println();
        Node.printLinkedList(result);
        System.out.println();
    }

    public static void testCase3() {
        int[][] arrays = {
                { 3, -1 },
                { 3, 0 },
                { 3, -1 }
        };

        Node head = Node.genLinkedList(arrays);
        Node result = new Solution().copyRandomList(head);

        System.out.println();
        Node.printLinkedList(head);
        System.out.println();
        Node.printLinkedList(result);
        System.out.println();
    }
}