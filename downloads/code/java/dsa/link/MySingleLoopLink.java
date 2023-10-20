package dsa.link;

import java.util.Arrays;

/**
 * 1. 插入操作不需要考虑空链表
 * 2. 删除操作需要考虑空链表
 */
public class MySingleLoopLink {
    private static class MyNode {
        public String value;
        public MyNode next;

        public MyNode(String value) {
            this.value = value;
        }
    }

    private MyNode head = new MyNode(null);

    public MySingleLoopLink() {
        head.next = head;
    }

    public boolean addFirst(String s) {
        MyNode node = new MyNode(s);

        node.next = head.next;

        // update head pointer
        head.next = node;

        // error code: p.next is updated but head.next not
        // MyNode p = head;
        // node.next = p.next;
        // p.next = node;

        return true;
    }

    public String removeFirst() {
        // empty link
        if (head.next == head) {
            return null;
        }

        MyNode node = head.next;

        // update head pointer
        head.next = node.next;

        // error code: p.next is updated but head.next not
        // MyNode p = head;
        // node = p.next;
        // p.next = node.next;

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

        p.next = node;

        return true;
    }

    public String removeLast() {
        // empty link
        if (head.next == head) {
            return null;
        }

        MyNode p = head;

        // use p.next.next to find the penult node
        while (p.next.next != head) {
            p = p.next;
        }

        MyNode node = p.next;

        p.next = node.next;

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
        MySingleLoopLink link = new MySingleLoopLink();

        // test ops for k times
        for (int k = 0; k < 3; k++) {
            test1(link, capacity);
            test2(link, capacity);
        }
    }

    public static void test1(MySingleLoopLink link, int capacity) {
        // test for addLast and removeFirst
        System.out.println("\n---test for addLast and removeFirst---\n");

        // test ops for k times
        for (int k = 0; k < 3; k++) {
            System.out.println("\n---test for removeFirst---\n");
            while (true) {
                String s = link.removeFirst();

                System.out.println(String.format("remove value %2s , link is %s", s, link.toString()));

                if (s == null) {
                    break;
                }
            }

            System.out.println("\n---test for addLast---\n");
            for (int i = 0; i <= capacity; i++) {
                String s = "" + i;

                link.addLast(s);

                System.out.println(String.format("add    value %2s , link is %s", s, link.toString()));
            }
        }
    }

    public static void test2(MySingleLoopLink link, int capacity) {
        // test for addFirst and removeLast
        System.out.println("\n---test for addFirst and removeLast---\n");

        // test ops for k times
        for (int k = 0; k < 3; k++) {
            System.out.println("\n---test for removeLast---\n");
            while (true) {
                String s = link.removeLast();

                System.out.println(String.format("remove value %2s , link is %s", s, link.toString()));

                if (s == null) {
                    break;
                }
            }

            System.out.println("\n---test for addFirst---\n");
            for (int i = 0; i <= capacity; i++) {
                String s = "" + i;

                link.addFirst(s);

                System.out.println(String.format("add    value %2s , link is %s", s, link.toString()));
            }
        }
    }
}
