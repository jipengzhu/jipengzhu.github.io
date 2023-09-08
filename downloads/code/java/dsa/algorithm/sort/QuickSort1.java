package dsa.algorithm.sort;

import java.util.Arrays;

public class QuickSort1 {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };

        sort(array);
        print(array);
        check(array);
    }

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        int p = partition(array, L, R);
        quickSort(array, L, p - 1);
        quickSort(array, p + 1, R);
    }

    private static int partition(int[] array, int L, int R) {
        // 基点选L时先从右边开始选值填左边的空位
        // 基点选R时先从左边开始选值填右边的空位
        int p = R;
        int v = array[p];
        int i = L;
        int j = R;

        while (true) {
            // 向右找到第一个大于中轴数的元素
            while (i < j && array[i] <= v)
                i++;

            if (i >= j)
                break;

            // 交换大于中轴数的元素到右边的空位
            array[j--] = array[i];

            // 向左找到第一个小于中轴数的元素
            while (i < j && array[j] >= v)
                j--;

            if (i >= j)
                break;

            // 交换小于中轴数的元素到左边的空位
            array[i++] = array[j];
        }

        // 更新中轴数的位置为中间的空位，此时i = j，可以任选一个
        p = i;

        // 将中轴数放到中间的空位上
        array[p] = v;

        return p;
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