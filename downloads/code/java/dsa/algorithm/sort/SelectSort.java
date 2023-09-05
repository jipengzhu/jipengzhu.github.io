package dsa.algorithm.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };
        sort(array);
        print(array);
    }

    public static void sort(int[] array) {
        int len = array.length;

        // 外层的边界是循环次数的范围：[0，len - 1)
        // 内层的边界是未排序部分的范围：[i + 1, len)
        for (int i = 0; i < len - 1; i++) {

            int k = i;

            // 当前元素比最小元素小，则被选为最小元素
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }

            swap(array, i, k);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}