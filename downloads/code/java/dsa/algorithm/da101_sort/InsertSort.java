package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class InsertSort {

    public static void sort(int[] array) {
        int len = array.length;

        // 外层的边界是循环次数的范围：[0，len - 1)
        // 内层的边界是已排序部分的范围：[0, i + 1）
        for (int i = 0; i < len - 1; i++) {

            int v = array[i + 1];

            int j = i; // j + 1 < len
            // 当前元素比插入元素大，则后退一步，让出位置
            while (j >= 0 && array[j] > v) {
                array[j + 1] = array[j];
                j--;
            }

            // 插入元素排到当前元素（不大于插入元素）的后面
            array[j + 1] = v;
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
