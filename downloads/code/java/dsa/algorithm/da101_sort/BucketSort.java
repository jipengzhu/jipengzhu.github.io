package dsa.algorithm.da101_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {

    public static void sort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int v = array[i];
            if (v < min) {
                min = v;
            }
            if (v > max) {
                max = v;
            }
        }
        float dis = max - min + 1;

        int bucketCount = 6;// 桶数量
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            lists.add(new ArrayList<>());
        }

        for (int i = 0; i < array.length; i++) {
            int v = array[i];
            int bucketIndex = (int) ((v - min) / dis * bucketCount);
            lists.get(bucketIndex).add(v);
        }

        int k = 0;
        for (List<Integer> list : lists) {
            list.sort(null);
            for (Integer v : list) {
                array[k++] = v;
            }
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