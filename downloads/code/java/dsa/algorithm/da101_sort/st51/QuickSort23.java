package dsa.algorithm.da101_sort.st51;

import java.util.Arrays;

public class QuickSort23 {

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
            // 向右找到第一个大于等于分割数的元素
            while (i <= j && array[i] < v)
                i++;

            // 向左找到第一个小于等于分割数的元素
            while (i <= j && array[j] > v)
                j--;

            if (i <= j) {
                // 如果分割数被换位置了，需要更新p为换位之后的位置，避免后面找不到分割点
                if (p == i) {
                    p = j;
                } else if (p == j) {
                    p = i;
                }

                // 通过交换操作将大于等于分割数的放在分割数的右边
                // 通过交换操作将小于等于分割数的放在分割数的左边
                // i = j 时是自我交换，不影响结果
                swap(array, i++, j--);
            }
        }

        // 循环结束后i左边（不包含）的都小于等于分割数
        // 循环结束后j右边（不包含）的都大于等于分割数
        // 循环结束后i - j = 1 或者 i - j = 2

        int m;
        if (i > R) {
            // 因为循环结束后i左边（不包含）的都小于等于分割数
            // 所以只分为了一个区间且里面的数都小于等于分割数
            // 即分割数就是最大值

            // 分割点选择区间的尾部
            m = R;
        } else if (j < L) {
            // 循环结束后j右边（不包含）的都大于等于分割数
            // 所以只分为了一个区间且里面的数都大于等于分割数
            // 即分割数就是最小值

            // 分割点选择区间的头部
            m = L;
        } else {
            // L <= j < i <= R
            if (p < i) {
                // 分割点选择左区间的尾部
                m = i - 1;
            } else if (p > j) {
                // 分割点选择右区间的头部
                m = j + 1;
            } else {
                // i <= p <= j，因为i > j，所以不存在这种情况
                m = -1;
            }
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