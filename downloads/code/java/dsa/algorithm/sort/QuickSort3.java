package dsa.algorithm.sort;

import java.util.Arrays;

public class QuickSort3 {

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

        // 分割成3个部分，左边小于等于基点，中间只有基点一个元素，右边大于等于基点
        // 基点可以选任意位置
        int p = (L + R) / 2;
        int v = array[p];
        int i = L;
        int j = R;

        while (true) {
            // 向右找到第一个大于中轴数的元素
            while (i < j && array[i] <= v)
                i++;

            // 向左找到第一个小于中轴数的元素
            while (i < j && array[j] >= v)
                j--;

            if (i >= j)
                break;

            swap(array, i++, j--);
        }

        // 对结束位和基点位的元素进行比较，此时i = j，可以任选一个
        if (i < p && array[i] > v) {
            swap(array, i, p);
            p = i;
        } else if (i > p && array[i] < v) {
            swap(array, i, p);
            p = i;
        }

        quickSort(array, L, p - 1);
        quickSort(array, p + 1, R);
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