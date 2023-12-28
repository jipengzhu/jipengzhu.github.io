package dsa.structure.ds703_heap;

import java.util.Arrays;

public class PriorityQueue {
    private int size = 0;
    private int[] array = null;

    public PriorityQueue(int capacity) {
        array = new int[capacity];
    }

    public boolean enqueue(int v) {
        int i;
        if (size == array.length) {
            i = indexOfObsolete(array, v);
            if (i < 0) {
                return true;
            }
        } else {
            i = size;
            size++;
        }

        array[i] = v;

        // 如果不存在淘汰，新的值v是直接添加到堆的底部，需要向上调整
        // 如果存在淘汰，由于是小顶堆，所以需要淘汰的最大值位于堆的底部
        // 由于新的值v会替换淘汰的最大值的位置，所以新的值v是位于堆的底部，需要向上调整
        shiftUp(array, i);

        return true;
    }

    public Integer dequeue() {
        if (size == 0) {
            return null;
        }

        int v = array[0];

        // swap(array, 0, --size);
        array[0] = array[--size];
        shiftDown(array, 0, size);

        return v;
    }

    public int size() {
        return size;
    }

    public void fillin(int[] array) {
        for (int v : array) {
            enqueue(v);
        }
    }

    public int[] collect() {
        // 不能直接使用size，因为size在出队的时候会改变
        int c = size;

        int[] a = new int[c];
        for (int i = 0; i < c; i++) {
            a[i] = dequeue();
        }

        return a;
    }

    public int[] toArray() {
        // // just for test
        // int[] a = Arrays.copyOf(array, array.length);
        // Arrays.sort(a);
        // return a;

        int[] bakArray = Arrays.copyOf(array, array.length);
        int bakSize = size;

        int[] ret = collect();

        array = bakArray;
        size = bakSize;

        return ret;
    }

    private int indexOfObsolete(int[] arr, int v) {
        int p = -1;

        // 小顶堆，淘汰最大的
        int max = v;
        
        // 由于最大的值没有子结点，所以查找的起始位置为第一个叶子结点
        // 因为最后一个非叶子结点的下标为 ((size - 1) - 1) / 2 = (size - 2) / 2
        // 所以第一个叶子结点的下标为 (size - 2) / 2 + 1
        for (int i = (size - 2) / 2 + 1; i < size; i++) {
            if (arr[i] > max) {
                max = arr[i];

                p = i;
            }
        }

        return p;
    }

    public void shiftUp(int[] arr, int i) {
        int j = (i - 1) / 2;
        while (j >= 0) {
            if (arr[i] < arr[j]) {
                // 小顶堆，谁小谁在上
                swap(arr, i, j);

                i = j;
                j = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    public void shiftDown(int[] arr, int i, int heapSize) {
        int j = 2 * i + 1;
        while (j < heapSize) {
            // 小顶堆，选择左右结点中较小的那一个的下标
            j = j + 1 < heapSize && arr[j + 1] < arr[j] ? j + 1 : j;
            if (arr[j] < arr[i]) {
                // 小顶堆，谁小谁在上
                swap(arr, j, i);

                i = j;
                j = 2 * i + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        // 注释掉，因为每次判断带来的开销并不比无效的自我交换带来的开销少
        // if (i == j) {
        // return;
        // }

        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        // PriorityQueueTest.testFixedExample(t -> testPriorityQueue(t));
        PriorityQueueTest.testRandomExample(t -> testPriorityQueue(t));
    }

    private static int[] testPriorityQueue(int[] array) {
        // int capacity = array.length * 2;
        int capacity = array.length / 2;
        PriorityQueue queue = new PriorityQueue(capacity);

        queue.fillin(array);

        return queue.toArray();
    }

    public static class PriorityQueueTest {
        public static interface PriorityQueueFunc {
            int[] apply(int[] array);
        }

        private static void testPriorityQueue(int[] array, PriorityQueueFunc f) {
            int[] mybak = Arrays.copyOf(array, array.length);
            Arrays.sort(mybak);

            int[] result = f.apply(array);
            int[] expect = Arrays.copyOf(mybak, result.length);

            TestUtils.check("优先级队列", result, expect);
        }

        private static void testRandomExample(PriorityQueueFunc f) {
            for (int i = 0; i < 36; i++) {
                testPriorityQueue(random(19), f);
                testPriorityQueue(random(20), f);
            }
        }

        private static void testFixedExample(PriorityQueueFunc f) {
            int[] array = new int[] { 310, 78, 237, 773, 96, 165, 70, 757, 665, 508 };

            testPriorityQueue(array, f);
        }

        public static int[] random(int n) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 1000) + 1;
            }

            return array;
        }
    }

    public static class TestUtils {

        public static boolean check(String name, Object result, Object expect) {
            boolean ok = java.util.Objects.deepEquals(result, expect);
            printCheck(name, toString(result), toString(expect), ok);
            return ok;
        }

        private static String toString(Object o) {
            if (o instanceof Object[]) {
                return java.util.Arrays.deepToString((Object[]) o);
            } else if (o instanceof int[]) {
                return java.util.Arrays.toString((int[]) o);
            } else if (o instanceof double[]) {
                return java.util.Arrays.toString((double[]) o);
            } else if (o instanceof char[]) {
                return java.util.Arrays.toString((char[]) o);
            } else {
                return java.util.Objects.toString(o);
            }
        }

        public static void printCheck(String name, String result, String expect, boolean ok) {
            System.out.println();
            System.out.println("--->");

            System.out.println("result: " + result);
            System.out.println("expect: " + expect);

            System.out.println();
            if (ok) {
                System.out.println("比较结果为: 正确(right)");
            } else {
                System.out.println("比较结果为: 错误(error)");
            }

            System.out.println("<---");
            System.out.println();

            if (!ok) {
                throw new RuntimeException(String.format("ERROR: [%s]的测试失败", name));
            }
        }
    }
}
