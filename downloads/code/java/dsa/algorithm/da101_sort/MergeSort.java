package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        int M = L + (R - L) / 2;
        mergeSort(array, L, M);
        mergeSort(array, M + 1, R);

        merge(array, L, M, R);
    }

    private static void merge(int[] array, int L, int M, int R) {
        int[] tmp = new int[R - L + 1];
        int i = L;
        int j = M + 1;
        int k = 0;

        while (i <= M && j <= R) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }

        // 根据上面循环的结束条件可知两部分都有剩余数据时不会退出
        // 即退出时最多只可能有一个部分还有剩余数据
        // 所以下面的两个循环最多只有一个会生效而不必担心剩余数据追加后会导致数组乱序
        while (i <= M)
            tmp[k++] = array[i++];
        while (j <= R)
            tmp[k++] = array[j++];

        for (int t = 0; t < k; t++) {
            array[L + t] = tmp[t];
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