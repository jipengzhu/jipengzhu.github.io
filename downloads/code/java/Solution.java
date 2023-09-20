import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int d = 0; // 移动方向 0：向右 1：向下 2：向左 3：向上
        int[] b = new int[4];// 方向边界 0：右边界 1：下边界 2：左边界 3：上边界
        b[0] = matrix[0].length - 1;
        b[1] = matrix.length - 1;
        b[2] = 0;
        b[3] = 0;

        List<Integer> list = new ArrayList<>();
        int count = matrix.length * matrix[0].length;
        int i = 0;
        int j = 0;
        while (list.size() < count) {
            int k = b[d];
            if (d == 0) {
                while (j <= k) {
                    list.add(matrix[i][j]);
                    j++;

                }

                j--;
                i++;

                d = (d + 1) % 4;
                b[3] = b[3] + 1;
            } else if (d == 1) {
                while (i <= k) {
                    list.add(matrix[i][j]);
                    i++;
                }

                i--;
                j--;

                d = (d + 1) % 4;
                b[0] = b[0] - 1;
            } else if (d == 2) {
                while (j >= k) {
                    list.add(matrix[i][j]);
                    j--;
                }

                j++;
                i--;

                d = (d + 1) % 4;
                b[1] = b[1] - 1;
            } else if (d == 3) {
                while (i >= k) {
                    list.add(matrix[i][j]);
                    i--;
                }

                i++;
                j++;

                d = (d + 1) % 4;
                b[2] = b[2] + 1;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        testSpiralOrder1();
        testSpiralOrder2();
    }

    public static void printArray(int[] array) {
        System.out.println(java.util.Arrays.toString(array));
    }

    public static void printArray(int[] array, int len) {
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
    }

    public static int[] listToArray(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return new int[0];
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    public static void testSpiralOrder1() {
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        printArray(listToArray(new Solution().spiralOrder(matrix)));
    }

    public static void testSpiralOrder2() {
        int[][] matrix = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };
        printArray(listToArray(new Solution().spiralOrder(matrix)));
    }
}