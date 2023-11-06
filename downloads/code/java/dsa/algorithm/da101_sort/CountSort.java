package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class CountSort {

    public static void sort(int[] array) {
        int len = array.length;

        int max = array[0];
        for (int i = 1; i < len; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        int count[] = new int[max + 1];
        for (int i = 0; i < len; i++) {
            count[array[i]] = count[array[i]] + 1;
        }

        // 用累加法计算某个值在排序后的结束位置（位置从1开始）
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        int tmp[] = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int v = array[i];

            // 获取当前数的排位
            int j = count[v];

            // 计算当前数在新数组中的下标（排位数减去1）
            j = j - 1;

            // 将当前数放到新数组的正确位置上
            tmp[j] = v;

            // 更新可用的排位
            count[v] = count[v] - 1;

            // 下面是简洁写法
            // tmp[count[array[i]] - 1] = array[i];
            // count[array[i]] = count[array[i]] - 1;
        }

        for (int i = 0; i < tmp.length; i++) {
            array[i] = tmp[i];
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
