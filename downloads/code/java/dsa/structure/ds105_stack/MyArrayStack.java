package dsa.structure.ds105_stack;

import java.util.Arrays;

public class MyArrayStack {
    private int capacity;
    private int top = -1;
    private String[] table;

    public MyArrayStack(int capacity) {
        this.capacity = capacity;
        this.table = new String[capacity];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public boolean push(String s) {
        if (isFull()) {
            return false;
        }

        table[top + 1] = s;
        top++;

        return true;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }

        String v = table[top];
        table[top] = null;
        top--;

        return v;
    }

    // for test print
    public String toString() {
        return Arrays.toString(table);
    }

    public static void main(String[] args) {
        int capacity = 7;
        MyArrayStack stack = new MyArrayStack(capacity);

        // test for push
        System.out.println("\n---test push---\n");
        for (int i = 0; i < capacity + 1; i++) {
            String s = "" + i;

            boolean ok = stack.push(s);
            if (ok) {
                System.out.println(String.format("push value %2s , stack is %s", s, stack.toString()));
            } else {
                System.out.println("stack is full");
                break;
            }
        }

        // test for pop and push
        System.out.println("\n---test pop and push---\n");
        for (int i = 0; i < capacity + 2; i++) {
            String s = "" + (i + capacity);

            if (i < capacity / 2) {
                s = stack.pop();
                System.out.println(String.format("pop  value %2s , stack is %s", s, stack.toString()));
            } else {
                boolean ok = stack.push(s);
                if (ok) {
                    System.out.println(String.format("push value %2s , stack is %s", s, stack.toString()));
                } else {
                    System.out.println("stack is full");
                    break;
                }
            }
        }

        // test for pop
        System.out.println("\n---test pop---\n");
        while (true) {
            String s = stack.pop();
            if (s != null) {
                System.out.println(String.format("pop  value %2s , stack is %s", s, stack.toString()));
            } else {
                System.out.println("stack is empty");
                break;
            }
        }
    }
}
