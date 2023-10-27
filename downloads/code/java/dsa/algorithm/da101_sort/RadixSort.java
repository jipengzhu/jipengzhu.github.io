package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class RadixSort {

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

        // 求取最大数
        int max = array[0];
        for (int i = 1; i < len; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        // 求取最大位数
        int t = 0;
        while (max > 0) {
            max = max / 10;
            t++;
        }

        for (int k = 1; k <= t; k++) {

            // 对每个分量使用基数排序
            int[] count = new int[10];
            for (int i = 0; i < len; i++) {
                int d = getDigit(array[i], k);
                count[d] = count[d] + 1;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }

            int[] tmp = new int[len];
            for (int i = len - 1; i >= 0; i--) {
                int d = getDigit(array[i], k);
                tmp[count[d] - 1] = array[i];
                count[d] = count[d] - 1;
            }

            for (int i = 0; i < tmp.length; i++) {
                array[i] = tmp[i];
            }
        }
    }

    private static int getDigit(int v, int k) {
        if (k > 1) {
            while (k-- > 1) {
                v = v / 10;
            }
        }

        return v % 10;
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