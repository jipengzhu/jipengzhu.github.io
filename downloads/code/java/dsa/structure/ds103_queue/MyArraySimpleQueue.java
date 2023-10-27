package dsa.structure.ds103_queue;

import java.util.Arrays;

public class MyArraySimpleQueue {
    private int capacity;
    private int front = 0;
    private int rear = 0;
    private String[] table;

    public MyArraySimpleQueue(int capacity) {
        this.capacity = capacity;
        this.table = new String[capacity];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == capacity;
    }

    public boolean enqueue(String s) {
        if (isFull()) {
            return false;
        }

        table[rear] = s;
        rear++;

        return true;
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }

        String v = table[front];
        table[front] = null;
        front++;

        return v;
    }

    // for test print
    public String toString() {
        return Arrays.toString(table);
    }

    public static void main(String[] args) {
        int capacity = 7;
        MyArraySimpleQueue queue = new MyArraySimpleQueue(capacity);

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
