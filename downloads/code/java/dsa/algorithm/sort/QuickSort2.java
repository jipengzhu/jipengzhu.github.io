package dsa.algorithm.sort;

import java.util.Arrays;

public class QuickSort2 {

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
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        // 分割成2个部分，左边小于等于基点，右边大于等于基点
        // 基点可以选任意位置
        int v = array[(L + R) / 2];
        int i = L;
        int j = R;

        while (true) {
            // 向右找到第一个等于或者大于中轴数的元素
            while (i <= j && array[i] < v)
                i++;

            // 向左找到第一个等于或者小于中轴数的元素
            while (i <= j && array[j] > v)
                j--;

            if (i > j)
                break;

            swap(array, i++, j--);
        }

        quickSort(array, L, j);
        quickSort(array, i, R);
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