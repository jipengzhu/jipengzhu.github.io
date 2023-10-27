package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class MergeSort {

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
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        int M = L + (R - L) / 2;
        mergeSort(array, L, M);
        mergeSort(array, M + 1, R);

        merge(array, L, M, R);
    }

    private static void merge(int[] array, int L, int M, int R) {
        int[] tmp = new int[R - L + 1];
        int i = L;
        int j = M + 1;
        int k = 0;

        while (i <= M && j <= R) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }

        // 根据上面循环的结束条件可知两部分都有剩余数据时不会退出
        // 即退出时最多只可能有一个部分还有剩余数据
        // 所以下面的两个循环最多只有一个会生效而不必担心剩余数据追加后会导致数组乱序
        while (i <= M)
            tmp[k++] = array[i++];
        while (j <= R)
            tmp[k++] = array[j++];

        for (int t = 0; t < k; t++) {
            array[L + t] = tmp[t];
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