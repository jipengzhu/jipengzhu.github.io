package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class HeapSort {

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

        for (int i = (len - 2) / 2; i >= 0; i--) {
            shiftDown(array, i, len);
        }

        // for (int i = 1; i < len; i++) {
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