package dsa.algorithm.sort;

import java.util.Arrays;

public class InsertBinarySort {

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
            int pos = search(array, 0, i, v);

            int j = i; // j + 1 < len
            while (j >= pos) {
                array[j + 1] = array[j];
                j--;
            }

            // 插入元素排到当前元素（不大于插入元素）的后面
            array[pos] = v;
        }
    }

    private static int search(int[] array, int L, int R, int v) {
        int m = (L + R) / 2;

        if (v < array[m]) {
            if (m - 1 < L) {
                return m;
            } else {
                return search(array, L, m - 1, v);
            }
        } else if (v > array[m]) {
            if (m + 1 > R) {
                return m + 1;
            } else {
                return search(array, m + 1, R, v);
            }
        } else {
            return m + 1;
        }
    }

    private static int search1(int[] array, int L, int R, int v) {
        while (L <= R) {
            int m = (L + R) / 2;
            if (v < array[m]) {
                R = m - 1;
            } else if (v > array[m]) {
                L = m + 1;
            } else {
                return m + 1;
            }
        }

        return L;
    }

    private static int search2(int[] array, int L, int R, int v) {
        while (L <= R) {
            int m = (L + R) / 2;
            if (v < array[m]) {
                R = m - 1;
            } else {
                L = m + 1;
            }
        }

        return L;
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
