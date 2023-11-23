package dsa.structure.ds703_heap;

import java.util.Arrays;
import java.util.Objects;

public class Heap {
    public int[] heapSort1(int[] array) {
        genHeapByShiftUp(array);

        int len = array.length;

        swap(array, 0, --len);
        while (len > 0) {
            shiftDown(array, 0, len);
            swap(array, 0, --len);
        }

        return array;
    }

    public int[] heapSort2(int[] array) {
        genHeapByShiftDown(array);

        int len = array.length;

        swap(array, 0, --len);
        while (len > 0) {
            shiftDown(array, 0, len);
            swap(array, 0, --len);
        }

        return array;
    }

    private void genHeapByShiftUp(int[] arr) {
        // 第一层是被比较的对象，不需要调整
        // 因为第一层只有一个根节点，所以第一个不需要调整
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            shiftUp(arr, i);
        }
    }

    private void genHeapByShiftDown(int[] arr) {
        // 最后一层是被比较的对象，不需要调整
        // 所以是从倒数第二层最右边的结点开始的
        // 设x为倒数第二层最右边的结点的下标
        // 则 2 * x + 2 < len
        // 则 x <= (len - 2) / 2（因为除法为整数除法，所以len为奇数时会等于整数除法的结果）
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
            int[] apply(int[] array);
        }

        private static void testHeap(int[] array, HeapFunc f) {
            int[] mybak = Arrays.copyOf(array, array.length);
            Arrays.sort(mybak);

            int[] result = f.apply(array);
            int[] expect = Arrays.copyOf(mybak, mybak.length);

            TestUtils.check("堆", result, expect);
        }

        private static void testRandomExample(HeapFunc f) {
            for (int i = 0; i < 36; i++) {
                testHeap(TestUtils.random(9), f);
                testHeap(TestUtils.random(10), f);
            }
        }

        private static void testFixedExample(HeapFunc f) {
            int[] array = new int[] { 310, 78, 237, 773, 96, 165, 70, 757, 665, 508 };

            testHeap(array, f);
        }
    }

    public static class TestUtils {
        public static int[] random(int n) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 1000) + 1;
            }

            return array;
        }

        public static void check(String name, int[] result, int[] expect) {
            String resultString = Arrays.toString(result);
            String expectString = Arrays.toString(expect);
            boolean ok = Arrays.equals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static void check(String name, Object[] result, Object[] expect) {
            String resultString = Arrays.deepToString(result);
            String expectString = Arrays.deepToString(expect);
            boolean ok = Arrays.deepEquals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static void check(String name, Object result, Object expect) {
            String resultString = result.toString();
            String expectString = expect.toString();
            boolean ok = Objects.equals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static <T> void check(String name, java.util.List<T> result, T[] expect, Class<T> clazz) {
            check(name, toArray(result, clazz), expect);
        }

        public static <T> void check(String name, java.util.List<java.util.List<T>> result, T[][] expect,
                Class<T> clazz) {
            check(name, toArrays(result, clazz), expect);
        }

        @SuppressWarnings("unchecked")
        public static <T> T[] genArray(Class<T> clazz, int length) {
            return (T[]) java.lang.reflect.Array.newInstance(clazz, length);
        }

        @SuppressWarnings("unchecked")
        public static <T> T[][] genArrays(Class<T> clazz, int rows, int cols) {
            T[] array = genArray(clazz, 0);

            T[][] arrays = (T[][]) genArray(array.getClass(), rows);
            for (int i = 0; i < arrays.length; i++) {
                arrays[i] = genArray(clazz, cols);
            }

            return arrays;
        }

        public static <T> T[] toArray(java.util.List<T> list, Class<T> clazz) {
            if (list == null || list.isEmpty()) {
                return genArray(clazz, 0);
            }

            T[] array = genArray(clazz, list.size());
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

            return array;
        }

        public static <T> T[][] toArrays(java.util.List<java.util.List<T>> lists, Class<T> clazz) {
            if (lists == null || lists.isEmpty()) {
                return genArrays(clazz, 0, 0);
            }

            T[][] arrays = genArrays(clazz, lists.size(), 0);
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = toArray(lists.get(i), clazz);
            }

            return arrays;
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