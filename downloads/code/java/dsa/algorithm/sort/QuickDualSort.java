package dsa.algorithm.sort;

import java.util.Arrays;

public class QuickDualSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };
        sort(array);
        print(array);
    }

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = partition(array, left, right);
        quickSort(array, left, mid - 1);
        quickSort(array, mid + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        // 中轴数，随便选一个都行，不是中间的那个数

        int p = left;
        // 中轴数取右边时用数组{2, 3, 9, 4, 8, 0, 7, 6, 5, 1}测试
        // 在 left == 0 && right == 9 时会导致 i = 1 和 j = 0 出现堆栈溢出
        // int p = right;
        // 中轴数取中间时用数组{2, 3, 9, 4, 8, 0, 7, 6, 5, 1}
        // 在 left == 0 && right == 2 时会导致 i = 0 和 j < 0 出现越界异常
        // int p = left + (right - left) / 2;
        int pivot = array[p];
        int i = left;
        int j = right;

        while (true) {
            // 向右找到第一个大于中轴数的元素
            while (i <= j && array[i] <= pivot)
                i++;
            // 向左找到第一个小于中轴数的元素
            while (i <= j && array[j] >= pivot)
                j--;

            // 注意：i == j之后的j--操作会使得 i > j
            // 所以需要此判断来避免错误的（i > j）和无效的（i == j）交换
            if (i >= j) {
                break;
            }

            // 交换元素，使得左边的不大于中轴数，右边的不小于中轴数
            swap(array, i, j);
        }

        // 将中轴数放到中轴位上
        // use i or j ???
        // if j < 0 ???
        int q = j;
        array[p] = array[q];
        array[q] = pivot;

        return q;
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