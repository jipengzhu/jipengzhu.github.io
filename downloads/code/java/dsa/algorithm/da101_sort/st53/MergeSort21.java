package dsa.algorithm.da101_sort.st53;

import java.util.Arrays;

public class MergeSort21 {

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

    // 双层循环 + 正向合并
    private static void merge(int[] array, int L, int M, int R) {
        int c = R - L + 1;
        int[] tmp = new int[c];
        int i = L;
        int j = M + 1;
        int e1 = M;
        int e2 = R;
        int k = 0;

        while (i <= e1 && j <= e2) {
            while (i <= e1 && j <= e2 && array[i] <= array[j]) {
                tmp[k++] = array[i++];
            }

            while (i <= e1 && j <= e2 && array[j] <= array[i]) {
                tmp[k++] = array[j++];
            }
        }

        // 根据上面循环的结束条件可知两部分都有剩余数据时不会退出
        // 即退出时最多只可能有一个部分还有剩余数据
        // 所以下面的两个循环最多只有一个会生效而不必担心剩余数据追加后会导致数组乱序
        while (i <= e1)
            tmp[k++] = array[i++];
        while (j <= e2)
            tmp[k++] = array[j++];

        for (int t = 0; t < c; t++) {
            array[L + t] = tmp[t];
        }
    }

    private static void swap(int[] array, int i, int j) {
        // 注释掉，因为每次判断带来的开销并不比无效的自我交换带来的开销少
        // if (i == j) {
        // return;
        // }

        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        // SortTest.testFixedExample(array -> sort(array));
        SortTest.testRandomExample(t -> sort(t));
    }

    public static class SortTest {
        public static interface SortFunc {
            void sort(int[] array);
        }

        private static boolean testSort(int[] array, SortFunc sf) {
            System.out.println();
            System.out.println("--->");

            int[] mybak = Arrays.copyOf(array, array.length);

            print(array, "数组排序前");

            Arrays.sort(mybak);
            print(mybak, "正确结果为");

            sf.sort(array);
            print(array, "数组排序后");

            boolean ret = check(array, mybak);

            System.out.println("<---");
            System.out.println();

            return ret;
        }

        private static void testRandomExample(SortFunc sf) {
            boolean ret;
            for (int i = 0; i < 36; i++) {
                ret = testSort(random(9), sf);
                if (!ret) {
                    break;
                }

                ret = testSort(random(10), sf);
                if (!ret) {
                    break;
                }
            }
        }

        private static void testFixedExample(SortFunc sf) {
            int[] array = new int[] { 310, 78, 237, 773, 96, 165, 70, 757, 665, 508 };

            testSort(array, sf);
        }

        private static int[] random(int n) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 1000) + 1;
            }

            return array;
        }

        private static boolean check(int[] array, int[] bak) {
            boolean ok = Arrays.equals(array, bak);

            System.out.println();
            if (ok) {
                System.out.println("排序结果为: 正确(right)");
            } else {
                System.out.println("排序结果为: 错误(error)");
            }

            return ok;
        }

        private static void print(int[] array, String tip) {
            System.out.println(tip + ":" + Arrays.toString(array));
        }
    }
}