package dsa.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };

        sort(array);
        print(array);
        check(array);
    }

    public static void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }

        // 根据上面循环的结束条件可知两部分都有剩余数据时不会退出
        // 即退出时最多只可能有一个部分还有剩余数据
        // 所以下面的两个循环最多只有一个会生效而不必担心剩余数据追加后会导致数组乱序
        while (i <= mid)
            tmp[k++] = array[i++];
        while (j <= right)
            tmp[k++] = array[j++];

        for (int t = 0; t < k; t++) {
            array[left + t] = tmp[t];
        }
    }

    private static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}