package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class InsertBinarySort {

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

        // 外层的边界是循环次数的范围：[0，len - 1)
        // 内层的边界是已排序部分的范围：[0, i + 1）
        for (int i = 0; i < len - 1; i++) {

            int v = array[i + 1];
            int pos = search(array, 0, i, v);
            // int pos = search1(array, 0, i, v);
            // int pos = search2(array, 0, i, v);

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
            R = m - 1;
            if (L > R) {
                // return m;
                return L; // 此时 L = R + 1 = (m - 1) + 1 = m
            } else {
                return search(array, L, R, v);
            }
        } else if (v > array[m]) {
            L = m + 1;
            if (L > R) {
                // return m + 1;
                return L; // 此时 L = m + 1
            } else {
                return search(array, L, R, v);
            }
        } else {
            if (L == R) {
                L = m + 1;

                // return m + 1;
                return L; // 此时 L = m + 1
            } else {
                // 等于的话还得往右边看看还有没有等于的
                L = m + 1;

                return search(array, L, R, v);
            }
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
                if (L == R) {
                    L = m + 1;

                    return L;
                } else {
                    L = m + 1;
                }
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
