package dsa.link;

import java.util.Arrays;

/**
 * 1. 插入操作不需要考虑空链表
 * 2. 删除操作需要考虑空链表
 */
public class MySingleLink {
    private static class MyNode {
        public String value;
        public MyNode next;

        public MyNode(String value) {
            this.value = value;
        }
    }

    private MyNode head = new MyNode(null);

    public MySingleLink() {

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
        if (head.next == null) {
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

    /**
     * 在尾部添加时需要找到倒数第一个
     * 使用p.next != null为循环条件和p.next == null为结束条件
     * 当链表为空时为了防止开始的时候p为null时导致空指针异常
     * 无头结点时需要特殊处理
     * 有头结点时任何时候p不可能为null
     */
    public boolean addLast(String s) {
        MyNode node = new MyNode(s);

        MyNode p = head;

        // use p.next to find the last node
        while (p.next != null) {
            p = p.next;
        }

        node.next = p.next;

        p.next = node;

        return true;
    }

    /**
     * 在尾部删除时需要找到倒数第二个
     * 使用p.next.next != null为循环条件和p.next.next == null为结束条件
     * 因为当链表为空时无法执行删除操作
     * 所以在头部和尾部删除时空链表需要特殊处理
     * 即需要判断链表为空时就不进行后续处理并返回
     * 当链表元素只有一个时为了防止开始的时候p.next为null时导致空指针异常
     * 无头结点时链表时需要特殊处理
     * 有头结点时任何时候p.next不可能为null
     */
    public String removeLast() {
        // empty link
        if (head.next == null) {
            return null;
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

        MyNode p = head.next;
        while (p != null) {
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
        MySingleLink link = new MySingleLink();

        // test ops for k times
        for (int k = 0; k < 3; k++) {
            test1(link, capacity);
            test2(link, capacity);
        }
    }

    public static void test1(MySingleLink link, int capacity) {
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

    public static void test2(MySingleLink link, int capacity) {
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
