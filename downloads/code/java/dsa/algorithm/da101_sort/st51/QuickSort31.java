package dsa.algorithm.da101_sort.st51;

import java.util.Arrays;

public class QuickSort31 {

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        // 分割成2个部分，左边小于等于分割数，右边大于等于分割数
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
                // 通过交换操作将大于等于分割数的放在分割数的右边
                // 通过交换操作将小于等于分割数的放在分割数的左边
                // i = j 时是自我交换，不影响结果
                swap(array, i++, j--);
            }
        }

        // i位置左边的都小于等于分割数
        // j位置右边的都大于等于分割数
        // 结束时 i - j = 1 或者 i - j = 2
        // --- 证明1 ---
        // i - j = 1时，即i = j + 1 或 i - 1 = j
        // 则左部分的右边界为 i - 1 = i - (i - j) = j
        // 则右部分的左边界为 j + 1 = j + (i - j) = i
        // --- 证明2 ---
        // i - j = 2时，即i - 1 = j + 1
        // 令 k = i - 1 = j + 1
        // 因为i位置左边的都小于等于分割数，且k = i - 1小于i
        // 所以k位置的数小于等于分割数
        // 因为j位置右边的都大于等于分割数，则k = j + 1大于j
        // 所以k位置的数大于等于分割数
        // 由上可知k位置的数小于等于分割数且k位置的数大于等于分割数
        // 所以k位置的数只能等于分割数
        // 因此分割时可以不需要包含k位置的数（备注：k = i - 1 = j + 1）
        // 则左部分的右边界为 i - 2 = i - (i - j) = j
        // 则右部分的左边界为 j + 2 = j + (i - j) = i
        // --- 总结 ---
        // 综上所述，左部分的右边界为j，右部分的左边界为i

        if (i > R) {
            // 因为循环结束后i左边（不包含）的都小于等于分割数
            // 所以只分为了一个区间且里面的数都小于等于分割数
            // 即分割数就是最大值

            // quickSort(array, L, j); // 重复的区间（会导致重复递归调用）
            // quickSort(array, i, R); // 无效的区间

            swap(array, p, j--);
        } else if (j < L) {
            // 循环结束后j右边（不包含）的都大于等于分割数
            // 所以只分为了一个区间且里面的数都大于等于分割数
            // 即分割数就是最小值

            // quickSort(array, L, j); // 重复的区间（会导致重复递归调用）
            // quickSort(array, i, R); // 无效的区间

            swap(array, i++, p);
        }

        quickSort(array, L, j);
        quickSort(array, i, R);
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