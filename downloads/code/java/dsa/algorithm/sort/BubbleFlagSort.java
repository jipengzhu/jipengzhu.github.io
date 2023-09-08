package dsa.algorithm.sort;

import java.util.Arrays;

public class BubbleFlagSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };

        sort(array);
        print(array);
        check(array);
    }

    public static void sort(int[] array) {
        int len = array.length;

        // 外层的边界是循环次数的范围：[0，len - 1)
        // 内层的边界是未排序部分的范围：[0, len - i)
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;

            for (int j = 0; j + 1 < len - i; j++) {
                // 比自己后面的元素大，则交换位置，排到后面去
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);

                    flag = false;
                }
            }

            // 没有发生过交换，代表已经有序了
            if (flag) {
                break;
            }
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
