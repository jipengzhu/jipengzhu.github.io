package dsa.structure.ds105_stack;

import java.util.Arrays;

import dsa.link.MyDoubleLink;

public class MyLinkedStack {

    private MyDoubleLink link = new MyDoubleLink();

    public MyLinkedStack() {

    }

    public boolean push(String s) {
        return link.addLast(s);
    }

    public String pop() {
        return link.removeLast();
    }

    // for test print
    public String toString() {
        return Arrays.toString(link.toArray());
    }

    public static void main(String[] args) {
        int capacity = 7;
        MyLinkedStack stack = new MyLinkedStack();

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