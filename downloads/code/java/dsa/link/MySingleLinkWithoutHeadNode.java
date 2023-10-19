package dsa.link;

import java.util.Arrays;

/**
 * 1. 插入操作不需要考虑空链表
 * 2. 删除操作需要考虑空链表
 * 3. 无头结点时操作第一个元素需要更新head指针
 */
public class MySingleLinkWithoutHeadNode {
    private static class MyNode {
        public String value;
        public MyNode next;

        public MyNode(String value) {
            this.value = value;
        }
    }

    private MyNode head = null;

    public MySingleLinkWithoutHeadNode() {

    }

    public boolean addFirst(String s) {
        MyNode node = new MyNode(s);

        node.next = head;

        // update head pointer
        head = node;

        // error code: p is updated but head not
        // MyNode p = head;
        // node.next = p;
        // p = node;

        return true;
    }

    public String removeFirst() {
        // empty link
        if (head == null) {
            return null;
        }

        MyNode node = head;

        // update head pointer
        head = node.next;

        // error code: p is updated but head not
        // MyNode p = head;
        // node = p;
        // p = node.next;

        return node.value;
    }

    public boolean addLast(String s) {
        MyNode node = new MyNode(s);

        // no one node, handle as add first node
        if (head == null) {
            // return addFirst(s);

            node.next = head;

            // update head pointer
            head = node;

            return true;
        }

        MyNode p = head;

        // use p.next to find the last node
        while (p.next != null) {
            p = p.next;
        }

        node.next = p.next;

        p.next = node;

        return true;
    }

    public String removeLast() {
        // empty link
        if (head == null) {
            return null;
        }

        // only one node, handle as remove first node
        if (head.next == null) {
            // return removeFirst();

            MyNode node = head;

            // update head pointer
            head = node.next;

            return node.value;
        }

        MyNode p = head;

        // use p.next.next to find the penult node
        while (p.next.next != null) {
            p = p.next;
        }

        MyNode node = p.next;

        p.next = node.next;

        return node.value;
    }

    public int size() {
        int size = 0;

        MyNode p = head;
        while (p != null) {
            size++;

            p = p.next;
        }

        return size;
    }

    public String[] toArray() {
        int size = size();

        String[] array = new String[size];

        MyNode p = head;

        int i = 0;
        while (p != null) {
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
        MySingleLinkWithoutHeadNode link = new MySingleLinkWithoutHeadNode();

        // test ops for k times
        for (int k = 0; k < 3; k++) {
            test1(link, capacity);
            test2(link, capacity);
        }
    }

    public static void test1(MySingleLinkWithoutHeadNode link, int capacity) {
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

    public static void test2(MySingleLinkWithoutHeadNode link, int capacity) {
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
