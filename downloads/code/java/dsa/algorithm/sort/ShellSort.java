package dsa.algorithm.sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };
        sort(array);
        print(array);
    }

    public static void sort(int[] array) {
        int len = array.length;

        int gap = len / 2;
        while (gap > 0) {
            // gap是分组数，也是步长，只对第一个分组排序
            for (int i = 0; i + gap < len; i = i + gap) {
                int v = array[i + gap];

                int j = i;
                // 比插入元素大，则后退一步，让出位置
                while (j >= 0 && array[j] > v) {
                    array[j + gap] = array[j];
                    j = j - gap;
                }

                // 插入元素排到当前元素（不大于插入元素）的后面
                array[j + gap] = v;
            }

            gap = gap / 2;
        }
    }

    public static void sortAllGroup(int[] array) {
        int len = array.length;

        int gap = len / 2;
        while (gap > 0) {
            // gap是分组数，也是步长，k是第K组的起始偏移量
            for (int k = 0; k < gap; k++) {
                for (int i = k; i + gap < len; i = i + gap) {
                    int v = array[i + gap];

                    int j = i;
                    // 比插入元素大，则后退一步，让出位置
                    while (j >= 0 && array[j] > v) {
                        array[j + gap] = array[j];
                        j = j - gap;
                    }

                    // 插入元素排到当前元素（不大于插入元素）的后面
                    array[j + gap] = v;
                }
            }

            gap = gap / 2;
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