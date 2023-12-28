package dsa.algorithm.da101_sort.st15;

import java.util.Arrays;

public class HeapSort {

    public static void sort(int[] array) {
        heapSort1(array);
        // heapSort2(array);
    }

    public static void heapSort1(int[] array) {
        genHeapByShiftUp(array);

        int len = array.length;
        while (len > 1) {
            swap(array, 0, --len);
            shiftDown(array, 0, len);
        }
    }

    public static void heapSort2(int[] array) {
        genHeapByShiftDown(array);

        int len = array.length;
        while (len > 1) {
            swap(array, 0, --len);
            shiftDown(array, 0, len);
        }
    }

    private static void genHeapByShiftUp(int[] arr) {
        // 从上往下处理时新结点是位于堆的底部，所以新结点需要的操作是向上调整
        // 因为不包含父结点的结点的不需要向上调整，所以第一个需要调整的结点是第一个包含父结点的结点
        // 所以第一个需要调整的结点的下标为i=1
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            shiftUp(arr, i);
        }
    }

    private static void genHeapByShiftDown(int[] arr) {
        // 从下往上处理时新结点是位于堆的顶部，所以新结点需要的操作是向下调整
        // 因为不包含子结点的结点的不需要向下调整，所以最后一个需要调整的结点是最后一个包含子结点的结点
        // 所以最后一个需要调整的结点的下标为i=((len - 1) - 1)) / 2 = (len - 2) / 2
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