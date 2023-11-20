package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class InsertShellSort {

    public static void sort(int[] array) {
        int len = array.length;

        int gap = len / 2;
        while (gap > 0) {
            // gap是分组数，也是步长，只对第一个分组排序
            for (int i = 0; i + gap < len; i = i + gap) {

                int v = array[i + gap];

                int j = i; // j + gap < len
                // 比插入元素大，则后退一步，让出位置
                while (j >= 0 && array[j] > v) {
                    array[j + gap] = array[j];
                    j = j - gap;
                }

                // 插入元素排到当前元素（不大于插入元素）的后面
                array[j + gap] = v;
            }

            gap = gap / 2;
        }
    }

    public static void sortAllGroup(int[] array) {
        int len = array.length;

        int gap = len / 2;
        while (gap > 0) {
            // gap是分组数，也是步长，k是第K组的起始偏移量
            for (int k = 0; k < gap; k++) {
                for (int i = k; i + gap < len; i = i + gap) {

                    int v = array[i + gap];

                    int j = i;
                    // 比插入元素大，则后退一步，让出位置
                    while (j >= 0 && array[j] > v) {
                        array[j + gap] = array[j];
                        j = j - gap;
                    }

                    // 插入元素排到当前元素（不大于插入元素）的后面
                    array[j + gap] = v;
                }
            }

            gap = gap / 2;
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