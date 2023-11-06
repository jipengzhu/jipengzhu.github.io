package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class RadixSort {

    public static void sort(int[] array) {
        int len = array.length;

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < len; i++) {
            if (array[i] < min) {
                min = array[i];
            }

            if (array[i] > max) {
                max = array[i];
            }
        }

        // 修正数组的数据到正数（包含0）的区间
        // 如果最小值是负数，可以解决负数不能做下标的问题
        // 如果最小值是正数，可以减少统计数组的空间
        int offset = max - min;
        for (int i = 0; i < len; i++) {
            array[i] = array[i] + offset;
        }
        // min = min + offset;
        max = max + offset;

        // 对每个分量使用其他稳定性排序算法
        // 推荐用计数排序（其他稳定性排序算法的得建立数组并排序）
        int c = getFigure(max);
        for (int k = 1; k <= c; k++) {
            int[] count = new int[10];

            for (int i = 0; i < len; i++) {
                int d = getDigit(array[i], k);
                count[d] = count[d] + 1;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }

            int[] tmp = new int[len];
            for (int i = len - 1; i >= 0; i--) {
                int d = getDigit(array[i], k);

                // 获取当前数的排位
                int j = count[d];

                // 计算当前数在新数组中的下标（排位数减去1）
                j = j - 1;

                // 将当前数放到新数组的正确位置上
                tmp[j] = array[i]; // 注意当前数是array[i]而不是d

                // 更新可用的排位
                count[d] = count[d] - 1;

                // 下面是简洁写法
                // int d = getDigit(array[i], k);
                // tmp[count[d] - 1] = array[i];
                // count[d] = count[d] - 1;
            }

            // 只能拷贝回原数组，不能改变引用
            // 因为改变引用的话方法外部的数组引用和方法内部的数组引用就不是同一个数组了
            // if (k < c) {
            // array = tmp;
            // continue;
            // }

            // 拷贝回原数组
            for (int i = 0; i < tmp.length; i++) {
                array[i] = tmp[i];
            }
        }

        // 恢复数组的数据到原来的数据
        for (int i = 0; i < len; i++) {
            array[i] = array[i] - offset;
        }
    }

    private static int getFigure(int v) {
        if (v < 0) {
            v = -v;
        }

        // 求取最大位数
        int c = 0;
        while (v > 0) {
            v = v / 10;
            c++;
        }

        return c;
    }

    private static int getDigit(int v, int k) {
        if (v < 0) {
            v = -v;
        }

        while (v > 0 && k > 1) {
            v = v / 10;

            k--;
        }

        return v % 10;
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