package dsa.algorithm.sort;

import java.util.Arrays;

public class BubbleDualSort {

    public static void main(String[] args) {

        int[] array = { 7, 3, 0, 5, 4, 1, 8, 9, 6, 2 };
        sort(array);
        print(array);
    }

    public static void sort(int[] array) {
        int len = array.length;

        int L = 0, R = len - 1;
        while (L < R) {
            for (int i = L; i < R; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
            R--;

            for (int i = R; i > L; i--) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                }

            }
            L++;
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
