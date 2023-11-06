package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class QuickSort2 {

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
        // 分割数可以选任意位置的数

        int i = L;
        int j = R;

        int p = (L + R) / 2;
        int v = array[p];

        while (i <= j) {
            // 向右找到第一个大于分割数的元素
            while (i <= j && array[i] <= v)
                i++;

            // 向左找到第一个小于分割数的元素
            while (i <= j && array[j] >= v)
                j--;

            if (i <= j) {
                // i = j 时是自我交换，不影响结果
                swap(array, i++, j--);
            }
        }

        // i位置左边的都小于等于分割数
        // j位置右边的都大于等于分割数
        // 即左边部分 L <= k < i，k位置的数都小于等于p位置的数
        // 即中间部分 i <= k <= j，因为i > j，所以这种情况不存在
        // 即右边部分 j < k <= R，k位置的数都大于等于p位置的数

        int m;
        if (p < i) {
            // 分割点选择左边部分的末尾
            m = i - 1 > L ? i - 1 : L;
        } else if (p > j) {
            // 分割点选择右边部分的开头
            m = j + 1 < R ? j + 1 : R;
        } else {
            // 分割点不变
            m = p;
        }

        if (m != p) {
            swap(array, p, m);
            p = m;
        }

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