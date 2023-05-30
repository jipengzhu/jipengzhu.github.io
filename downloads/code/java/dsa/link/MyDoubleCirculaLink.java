package dsa.link;

import java.util.Arrays;

/**
 * 1. 插入操作不需要考虑空链表
 * 2. 删除操作需要考虑空链表
 */
public class MyDoubleCirculaLink {
    private static class MyNode {
        public String value;
        public MyNode next;
        public MyNode prev;

        public MyNode(String value) {
            this.value = value;
        }
    }

    private MyNode head = new MyNode(null);

    public MyDoubleCirculaLink() {
        head.next = head;
        head.prev = head;
    }

    public boolean addFirst(String s) {
        MyNode node = new MyNode(s);

        node.next = head.next;
        node.prev = head;

        node.next.prev = node;
        node.prev.next = node;

        return true;
    }

    public String removeFirst() {
        // empty link
        if (head.next == head) {
            return null;
        }

        MyNode node = head.next;

        node.next.prev = node.prev;
        node.prev.next = node.next;

        return node.value;
    }

    public boolean addLast(String s) {
        MyNode node = new MyNode(s);

        MyNode p = head;

        // use p.next to find the last node
        while (p.next != head) {
            p = p.next;
        }

        node.next = p.next;
        node.prev = p;

        node.next.prev = node;
        node.prev.next = node;

        return true;
    }

    public String removeLast() {
        // empty link
        if (head.next == head) {
            return null;
        }

        MyNode p = head;

        // use p.next to find the last node
        while (p.next != head) {
            p = p.next;
        }

        MyNode node = p;

        node.next.prev = node.prev;
        node.prev.next = node.next;

        return node.value;
    }

    public int size() {
        int size = 0;

        MyNode p = head.next;
        while (p != head) {
            size++;

            p = p.next;
        }

        return size;
    }

    public String[] toArray() {
        int size = size();

        String[] array = new String[size];

        MyNode p = head.next;

        int i = 0;
        while (p != head) {
            array[i] = p.value;

            i++;

            p = p.next;
        }

        return array;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public static void main(String[] args) {
        int capacity = 7;
        MyDoubleCirculaLink link = new MyDoubleCirculaLink();

        test1(link, capacity);
        test1(link, capacity);
        test2(link, capacity);
        test2(link, capacity);
        test1(link, capacity);
        test2(link, capacity);
    }

    public static void test1(MyDoubleCirculaLink link, int capacity) {
        // test for addLast and removeFirst
        System.out.println("\n---test for addLast and removeFirst---\n");
        while (true) {
            String s = link.removeFirst();

            System.out.println(String.format("remove value %2s , link is %s", s, link.toString()));

            if (s == null) {
                break;
            }
        }
        for (int i = 0; i < capacity + 1; i++) {
            String s = "" + i;

            link.addLast(s);

            System.out.println(String.format("add    value %2s , link is %s", s, link.toString()));
        }
        while (true) {
            String s = link.removeFirst();

            System.out.println(String.format("remove value %2s , link is %s", s, link.toString()));

            if (s == null) {
                break;
            }
        }
    }

    public static void test2(MyDoubleCirculaLink link, int capacity) {
        // test for addFirst and removeLast
        System.out.println("\n---test for addFirst and removeLast---\n");
        while (true) {
            String s = link.removeLast();

            System.out.println(String.format("remove value %2s , link is %s", s, link.toString()));

            if (s == null) {
                break;
            }
        }
        for (int i = 0; i < capacity + 1; i++) {
            String s = "" + i;

            link.addFirst(s);

            System.out.println(String.format("add    value %2s , link is %s", s, link.toString()));
        }
        while (true) {
            String s = link.removeLast();

            System.out.println(String.format("remove value %2s , link is %s", s, link.toString()));

            if (s == null) {
                break;
            }
        }
    }
}