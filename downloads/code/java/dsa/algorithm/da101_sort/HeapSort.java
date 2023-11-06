package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class HeapSort {

    public static void sort(int[] array) {
        // genHeapByShiftUp(array);
        genHeapByShiftDown(array);

        int len = array.length;

        swap(array, 0, --len);
        while (len > 0) {
            shiftDown(array, 0, len);
            swap(array, 0, --len);
        }
    }

    private static void genHeapByShiftUp(int[] arr) {
        // 第一层是被比较的对象，不需要调整
        // 因为第一层只有一个根节点，所以第一个不需要调整
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            shiftUp(arr, i);
        }
    }

    private static void genHeapByShiftDown(int[] arr) {
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

    public static void shiftUp(int[] arr, int i) {
        int j = (i - 1) / 2;
        while (j >= 0) {
            if (arr[i] > arr[j]) {
                swap(arr, i, j);

                i = j;
                j = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    public static void shiftDown(int[] arr, int i, int heapSize) {
        int j = 2 * i + 1;
        while (j < heapSize) {
            j = j + 1 < heapSize && arr[j + 1] > arr[j] ? j + 1 : j;
            if (arr[i] < arr[j]) {
                swap(arr, i, j);

                i = j;
                j = 2 * i + 1;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        // 注释掉，因为每次判断带来的开销并不比无效的自我交换带来的开销少
        // if (i == j) {
        // return;
        // }

        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        // SortUtils.testFixedExample();
        SortUtils.testRandomExample();
    }

    public static class SortUtils {
        private static boolean testSort(int[] array) {
            System.out.println();
            System.out.println("--->");

            int[] mybak = Arrays.copyOf(array, array.length);

            print(array, "数组排序前");

            Arrays.sort(mybak);
            print(mybak, "正确结果为");

            sort(array);
            print(array, "数组排序后");

            boolean ret = check(array, mybak);

            System.out.println("<---");
            System.out.println();

            return ret;
        }

        private static void testRandomExample() {
            boolean ret;
            for (int i = 0; i < 36; i++) {
                ret = testSort(random(9));
                if (!ret) {
                    break;
                }

                ret = testSort(random(10));
                if (!ret) {
                    break;
                }
            }
        }

        private static void testFixedExample() {
            int[] array = new int[] { 310, 78, 237, 773, 96, 165, 70, 757, 665, 508 };

            testSort(array);
        }

        private static int[] random(int n) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 1000) + 1;
            }

            return array;
        }

        private static boolean check(int[] array, int[] bak) {
            System.out.println();
            if (Arrays.equals(array, bak)) {
                System.out.println("排序结果为: 正确(right)");

                return true;
            } else {
                System.out.println("排序结果为: 错误(error)");

                return false;
            }
        }

        private static void print(int[] array, String tip) {
            System.out.println(tip + ":" + Arrays.toString(array));
        }
    }
}