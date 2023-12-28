package dsa.algorithm.da101_sort.st51;

import java.util.Arrays;

public class QuickSort13 {

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        int p = partition(array, L, R);

        quickSort(array, L, p - 1);
        quickSort(array, p + 1, R);
    }

    private static int partition(int[] array, int L, int R) {
        // 分割成3个部分，左边小于等于分割数，中间只有分割数一个元素，右边大于等于分割数
        // 分割数选L位置的数时先从右边开始查找
        // 分割数选R位置的数时先从左边开始查找

        int i = L;
        int j = R;

        while (i < j) {
            // 此时i位置的数是分割数
            // 从右向左找到第一个小于分割数的元素
            while (i < j && array[j] >= array[i])
                j--;

            if (i < j) {
                // 通过交换操作将小于分割数的放在分割数的左边
                // 交换后j位置的数是分割数
                swap(array, i++, j);
            }

            // 此时j位置的数是分割数
            // 从左向右找到第一个大于分割数的元素
            while (i < j && array[i] <= array[j])
                i++;

            if (i < j) {
                // 通过交换操作将大于分割数的放在分割数的右边
                // 交换后i位置的数是分割数
                swap(array, i, j--);
            }

        }

        // 获取分割数的位置，此时i = j，可以任选一个
        int p = i;

        return p;
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