package dsa.algorithm.sort;

import java.util.Arrays;

public class CountSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };

        sort(array);
        print(array);
        check(array);
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
        for (int i = 1; i < len; i++) {
            count[array[i]] = count[array[i]] + 1;
        }

        // 用累加法计算某个值在排序后的结束位置（位置从1开始）
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i - 1] + 1;
        }

        int tmp[] = new int[len];
        for (int i = array.length - 1; i >= 0; i--) {
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

    private static void check(int[] array) {
        int bak[] = Arrays.copyOf(array, array.length);
        Arrays.sort(bak);

        if (Arrays.equals(array, bak)) {
            System.out.println("Success：排序测试成功\n");
        } else {
            System.out.println("Failure：排序测试失败\n");
        }
    }

    private static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
