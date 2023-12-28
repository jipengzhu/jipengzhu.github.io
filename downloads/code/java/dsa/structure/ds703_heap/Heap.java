package dsa.structure.ds703_heap;

import java.util.Arrays;

public class Heap {
    public void heapSort1(int[] array) {
        genHeapByShiftUp(array);

        int len = array.length;
        while (len > 1) {
            swap(array, 0, --len);
            shiftDown(array, 0, len);
        }
    }

    public void heapSort2(int[] array) {
        genHeapByShiftDown(array);

        int len = array.length;
        while (len > 1) {
            swap(array, 0, --len);
            shiftDown(array, 0, len);
        }
    }

    private void genHeapByShiftUp(int[] arr) {
        // 从上往下处理时新结点是位于堆的底部，所以新结点需要的操作是向上调整
        // 因为不包含父结点的结点的不需要向上调整，所以第一个需要调整的结点是第一个包含父结点的结点
        // 所以第一个需要调整的结点的下标为i=1
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            shiftUp(arr, i);
        }
    }

    private void genHeapByShiftDown(int[] arr) {
        // 从下往上处理时新结点是位于堆的顶部，所以新结点需要的操作是向下调整
        // 因为不包含子结点的结点的不需要向下调整，所以最后一个需要调整的结点是最后一个包含子结点的结点
        // 所以最后一个需要调整的结点的下标为i=((len - 1) - 1)) / 2 = (len - 2) / 2
        int len = arr.length;
        for (int i = (len - 2) / 2; i >= 0; i--) {
            shiftDown(arr, i, len);
        }
    }

    public void shiftUp(int[] arr, int i) {
        int j = (i - 1) / 2;
        while (j >= 0) {
            if (arr[i] > arr[j]) {
                // 大顶堆，谁大谁在上
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
            // 大顶堆，选择左右结点中较大的那一个的下标
            j = j + 1 < heapSize && arr[j + 1] > arr[j] ? j + 1 : j;
            if (arr[j] > arr[i]) {
                // 大顶堆，谁大谁在上
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
        // HeapTest.testFixedExample(t -> new Heap().heapSort1(t));
        HeapTest.testRandomExample(t -> new Heap().heapSort1(t));

        // HeapTest.testFixedExample(t -> new Heap().heapSort2(t));
        HeapTest.testRandomExample(t -> new Heap().heapSort2(t));
    }

    public static class HeapTest {
        public static interface HeapFunc {
            void apply(int[] array);
        }

        private static void testHeap(int[] array, HeapFunc f) {
            int[] mybak = Arrays.copyOf(array, array.length);
            Arrays.sort(mybak);

            f.apply(array);

            int[] result = array;
            int[] expect = mybak;

            TestUtils.check("堆", result, expect);
        }

        private static void testRandomExample(HeapFunc f) {
            for (int i = 0; i < 36; i++) {
                testHeap(random(9), f);
                testHeap(random(10), f);
            }
        }

        private static void testFixedExample(HeapFunc f) {
            int[] array = new int[] { 310, 78, 237, 773, 96, 165, 70, 757, 665, 508 };

            testHeap(array, f);
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