package dsa.algorithm.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };

        sort(array);
        print(array);
        check(array);
    }

    public static void sort(int[] array) {
        int len = array.length;

        // 外层的边界是循环次数的范围：[0，len - 1)
        // 内层的边界是已排序部分的范围：[0, i + 1）
        for (int i = 0; i < len - 1; i++) {

            int v = array[i + 1];

            int j = i; // j + 1 < len
            // 当前元素比插入元素大，则后退一步，让出位置
            while (j >= 0 && array[j] > v) {
                array[j + 1] = array[j];
                j--;
            }

            // 插入元素排到当前元素（不大于插入元素）的后面
            array[j + 1] = v;
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
