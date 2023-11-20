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
                // 大顶堆，谁大谁在上
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
        // SortTest.testFixedExample(array -> sort(array));
        SortTest.testRandomExample(t -> sort(t));
    }

    public static class SortTest {
        public static interface SortFunc {
            void sort(int[] array);
        }

        private static boolean testSort(int[] array, SortFunc sf) {
            System.out.println();
            System.out.println("--->");

            int[] mybak = Arrays.copyOf(array, array.length);

            print(array, "数组排序前");

            Arrays.sort(mybak);
            print(mybak, "正确结果为");

            sf.sort(array);
            print(array, "数组排序后");

            boolean ret = check(array, mybak);

            System.out.println("<---");
            System.out.println();

            return ret;
        }

        private static void testRandomExample(SortFunc sf) {
            boolean ret;
            for (int i = 0; i < 36; i++) {
                ret = testSort(random(9), sf);
                if (!ret) {
                    break;
                }

                ret = testSort(random(10), sf);
                if (!ret) {
                    break;
                }
            }
        }

        private static void testFixedExample(SortFunc sf) {
            int[] array = new int[] { 310, 78, 237, 773, 96, 165, 70, 757, 665, 508 };

            testSort(array, sf);
        }

        private static int[] random(int n) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 1000) + 1;
            }

            return array;
        }

        private static boolean check(int[] array, int[] bak) {
            boolean ok = Arrays.equals(array, bak);

            System.out.println();
            if (ok) {
                System.out.println("排序结果为: 正确(right)");
            } else {
                System.out.println("排序结果为: 错误(error)");
            }

            return ok;
        }

        private static void print(int[] array, String tip) {
            System.out.println(tip + ":" + Arrays.toString(array));
        }
    }
}