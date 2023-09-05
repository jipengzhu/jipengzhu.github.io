package dsa.structure.queue;

import java.util.Arrays;

import dsa.link.MySingleLink;

public class MyLinkedQueue {

    private MySingleLink link = new MySingleLink();

    public MyLinkedQueue() {

    }

    public boolean enqueue(String s) {
        return link.addLast(s);
    }

    public String dequeue() {
        return link.removeFirst();
    }

    // for test print
    public String toString() {
        return Arrays.toString(link.toArray());
    }

    public static void main(String[] args) {
        int capacity = 7;
        MyLinkedQueue queue = new MyLinkedQueue();

        // test for enqueue
        System.out.println("\n---test enqueue---\n");
        for (int i = 0; i < capacity + 1; i++) {
            String s = "" + i;

            boolean ok = queue.enqueue(s);
            if (ok) {
                System.out.println(String.format("enqueue value %2s , queue is %s", s, queue.toString()));
            } else {
                System.out.println("queue is full");
                break;
            }
        }

        // test for dequeue and enqueue
        System.out.println("\n---test dequeue and enqueue---\n");
        for (int i = 0; i < capacity + 1; i++) {
            String s = "" + (i + capacity);

            if (i < capacity / 2) {
                s = queue.dequeue();
                System.out.println(String.format("dequeue value %2s , queue is %s", s, queue.toString()));
            } else {
                boolean ok = queue.enqueue(s);
                if (ok) {
                    System.out.println(String.format("enqueue value %2s , queue is %s", s, queue.toString()));
                } else {
                    System.out.println("queue is full");
                    break;
                }
            }
        }

        // test for dequeue
        System.out.println("\n---test dequeue---\n");
        while (true) {
            String s = queue.dequeue();
            if (s != null) {
                System.out.println(String.format("dequeue value %2s , queue is %s", s, queue.toString()));
            } else {
                System.out.println("queue is empty");
                break;
            }
        }
    }
}