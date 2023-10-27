package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class QuickSort1 {

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