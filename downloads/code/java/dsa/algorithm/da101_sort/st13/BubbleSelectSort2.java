package dsa.algorithm.da101_sort.st13;

import java.util.Arrays;

public class BubbleSelectSort2 {

    public static void sort(int[] array) {
        int len = array.length;

        // 外层的边界是循环次数的范围：[0，len - 1)
        // 内层的边界是未排序部分的范围：[i, len)
        for (int i = 0; i < len - 1; i++) {

            int min = -1;
            for (int j = len - 1; j > i; j--) {
                // 比自己前面的元素小，则交换位置，排到前面去
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);

                    // 当前条件中较小值的下标交换前是j，交换后则是j - 1
                    min = j - 1;
                } else {
                    // 当前条件中较小值的下标就是j - 1
                    min = j - 1;
                }
            }

            // 无意义的操作，因为前面交换操作的影响使得min = i
            // 即选择出最小值并交换到未排序部分的头部这个操作已经通过前面的交换操作完成了的
            // 所以min指针也是多余的（去掉min指针相关的操作就是真正的冒泡排序了）
            swap(array, min, i);
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
