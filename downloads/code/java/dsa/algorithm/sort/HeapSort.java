package dsa.algorithm.sort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };
        sort(array);
        print(array);
    }

    public static void sort(int[] array) {
        int len = array.length;

        for (int i = (len - 2) / 2; i >= 0; i--) {
            shiftDown(array, i, len);
        }

        // for (int i = 0; i < len; i++) {
        // shiftUp(array, i);
        // }

        swap(array, 0, --len);
        while (len > 0) {
            shiftDown(array, 0, len);
            swap(array, 0, --len);
        }
    }

    public static void shiftUp(int[] arr, int i) {
        int j = (i - 1) / 2;
        while (i > 0) {
            if (arr[i] > arr[j]) {
                swap(arr, i, j);

                i = j;
                j = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    public static void shiftDown(int[] arr, int i, int heapSize) {
        int L = 2 * i + 1;
        while (L < heapSize) {
            int j = L + 1 < heapSize && arr[L + 1] > arr[L] ? L + 1 : L;
            if (arr[i] < arr[j]) {
                swap(arr, i, j);

                i = j;
                L = 2 * i + 1;
            } else {
                break;
            }
        }
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