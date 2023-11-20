package dsa.algorithm.da101_sort;

import java.util.Arrays;

public class InsertSearchSort {

    public static void sort(int[] array) {
        int len = array.length;

        // 外层的边界是循环次数的范围：[0，len - 1)
        // 内层的边界是已排序部分的范围：[0, i + 1）
        for (int i = 0; i < len - 1; i++) {

            int v = array[i + 1];
            int pos = search(array, 0, i, v);
            // int pos = search1(array, 0, i, v);
            // int pos = search2(array, 0, i, v);

            int j = i; // j + 1 < len
            while (j >= pos) {
                array[j + 1] = array[j];
                j--;
            }

            // 插入元素排到当前元素（不大于插入元素）的后面
            array[pos] = v;
        }
    }

    private static int search(int[] array, int L, int R, int v) {
        int m = (L + R) / 2;

        if (v < array[m]) {
            R = m - 1;
            if (L > R) {
                // return m;
                return L; // 此时 L = R + 1 = (m - 1) + 1 = m
            } else {
                return search(array, L, R, v);
            }
        } else if (v > array[m]) {
            L = m + 1;
            if (L > R) {
                // return m + 1;
                return L; // 此时 L = m + 1
            } else {
                return search(array, L, R, v);
            }
        } else {
            if (L == R) {
                L = m + 1;

                // return m + 1;
                return L; // 此时 L = m + 1
            } else {
                // 等于的话还得往右边看看还有没有等于的
                L = m + 1;

                return search(array, L, R, v);
            }
        }
    }

    private static int search1(int[] array, int L, int R, int v) {
        while (L <= R) {
            int m = (L + R) / 2;
            if (v < array[m]) {
                R = m - 1;
            } else if (v > array[m]) {
                L = m + 1;
            } else {
                if (L == R) {
                    L = m + 1;

                    return L;
                } else {
                    L = m + 1;
                }
            }
        }

        return L;
    }

    private static int search2(int[] array, int L, int R, int v) {
        while (L <= R) {
            int m = (L + R) / 2;
            if (v < array[m]) {
                R = m - 1;
            } else {
                L = m + 1;
            }
        }

        return L;
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
