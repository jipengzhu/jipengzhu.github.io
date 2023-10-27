package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class ShellSort {

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

        int gap = len / 2;
        while (gap > 0) {
            // gap是分组数，也是步长，只对第一个分组排序
            for (int i = 0; i + gap < len; i = i + gap) {

                int v = array[i + gap];

                int j = i; // j + gap < len
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