package dsa.algorithm.sort;

import java.util.Arrays;

public class QuickSort3 {

    public static void main(String[] args) {

        int[] array = random(10);
        // for debug
        // array = new int[]{310, 78, 237, 773, 96, 165, 70, 757, 665, 508};
        array = new int[]{522, 516, 880, 362, 8, 825, 881, 311, 150, 912};

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