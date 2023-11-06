package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class QuickSort1 {

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        int p = partition(array, L, R);
        // int p = partition1(array, L, R);
        // int p = partition2(array, L, R);

        quickSort(array, L, p - 1);
        quickSort(array, p + 1, R);
    }

    private static int partition(int[] array, int L, int R) {
        // 分割数选L位置的数时先从右边开始选值填左边的空位
        // 分割数选R位置的数时先从左边开始选值填右边的空位

        int i = L;
        int j = R;

        // i和j中其中一个是指针，另一个是空位
        // 相等时代表指针移动到了空位上了，可以结束了
        while (i < j) {
            // 向右找到第一个大于分割数的元素
            while (i < j && array[i] <= array[j])
                i++;

            if (i < j) {
                // 抢占空位，此时j是空位，抢占后i就是空位了
                swap(array, i, j--);
            }

            // 向左找到第一个小于分割数的元素
            while (i < j && array[j] >= array[i])
                j--;

            if (i < j) {
                // 抢占空位，此时i是空位，抢占后j就是空位了
                swap(array, i++, j);
            }
        }

        // 获取分割点的位置，此时i = j，可以任选一个
        int p = i;

        return p;
    }

    private static int partition1(int[] array, int L, int R) {
        // 分割数选L位置的数时先从右边开始选值填左边的空位
        // 分割数选R位置的数时先从左边开始选值填右边的空位

        int i = L;
        int j = R;

        int v = array[j];

        // i和j中其中一个是指针，另一个是空位
        // 相等时代表指针移动到了空位上了，可以结束了
        while (i < j) {
            // 向右找到第一个大于分割数的元素
            while (i < j && array[i] <= v)
                i++;

            if (i < j) {
                // 抢占空位，此时j是空位，抢占后i就是空位了
                // swap(array, i, j--); // 简化为下面的操作，因为分割数已经用变量v保存起来了
                array[j--] = array[i];

            }

            // 向左找到第一个小于分割数的元素
            while (i < j && array[j] >= v)
                j--;

            if (i < j) {
                // 抢占空位，此时i是空位，抢占后j就是空位了
                // swap(array, i++, j); // 简化为下面的操作，因为分割数已经用变量v保存起来了
                array[i++] = array[j];
            }
        }

        // 获取分割点的位置，此时i = j，可以任选一个
        int p = i;

        // 将分割数放到中间的空位上
        array[p] = v;

        return p;
    }

    private static int partition2(int[] array, int L, int R) {
        // 分割数选L位置的数时先从右边开始选值填左边的空位
        // 分割数选R位置的数时先从左边开始选值填右边的空位

        int i = L;
        int j = R;

        int v = array[i];

        // i和j中其中一个是指针，另一个是空位
        // 相等时代表指针移动到了空位上了，可以结束了
        while (i < j) {
            // 向左找到第一个小于分割数的元素
            while (i < j && array[j] >= v)
                j--;

            if (i < j) {
                // 抢占空位，此时i是空位，抢占后j就是空位了
                // swap(array, i++, j); // 简化为下面的操作，因为分割数已经用变量v保存起来了
                array[i++] = array[j];
            }

            // 向右找到第一个大于分割数的元素
            while (i < j && array[i] <= v)
                i++;

            if (i < j) {
                // 抢占空位，此时j是空位，抢占后i就是空位了
                // swap(array, i, j--); // 简化为下面的操作，因为分割数已经用变量v保存起来了
                array[j--] = array[i];

            }
        }

        // 获取分割点的位置，此时i = j，可以任选一个
        int p = i;

        // 将分割数放到中间的空位上
        array[p] = v;

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