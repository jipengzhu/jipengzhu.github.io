package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class CountSort {

    public static void main(String[] args) {

        int[] array = random(10);
        // for debug
        // array = new int[]{310, 78, 237, 773, 96, 165, 70, 757, 665, 508};

        int[] bak = Arrays.copyOf(array, array.length);

        print(array, "数组排序前");
        sort(array);
        print(array, "数组排序后");

        check(array, bak);
    }

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
            tmp[count[array[i]] - 1] = array[i];
            count[array[i]] = count[array[i]] - 1;
        }

        for (int i = 0; i < tmp.length; i++) {
            array[i] = tmp[i];
        }
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static int[] random(int n) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000) + 1;
        }

        return array;
    }

    private static void check(int[] array, int[] bak) {
        Arrays.sort(bak);

        print(bak, "正确结果为");

        System.out.println();
        if (Arrays.equals(array, bak)) {
            System.out.println("排序结果为: 正确(right)");
        } else {
            System.out.println("排序结果为: 错误(error)");
        }
    }

    private static void print(int[] array, String tip) {

        System.out.println(tip + ":" + Arrays.toString(array));
    }
}
