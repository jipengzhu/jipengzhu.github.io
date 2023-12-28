package dsa.algorithm.da101_sort.st71;

import java.util.Arrays;

public class CountSort {

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

        int count[] = new int[max + 1];

        // 计算某个值的数量
        for (int i = 0; i < len; i++) {
            count[array[i]] = count[array[i]] + 1;
        }

        // 用累加法计算某个值在排序后的最高排位（排位从1开始）
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        // 从后向前处理保证计数排序算法的稳定性
        int[] tmp = new int[len];
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

        // 拷贝回原数组
        for (int i = 0; i < tmp.length; i++) {
            array[i] = tmp[i];
        }

        // 恢复数组的数据到原来的数据
        for (int i = 0; i < len; i++) {
            array[i] = array[i] - offset;
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
