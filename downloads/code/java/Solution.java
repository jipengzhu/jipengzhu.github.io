class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length; // 行数
        int n = matrix[0].length;// 列数
        int[] rows = new int[m]; // 存储每一行中第一个遇到的0点的列坐标
        int[] cols = new int[n]; // 存储每一列中第一个遇到的0点的行坐标
        for (int i = 0; i < rows.length; i++) {
            rows[i] = -1;
        }
        for (int j = 0; j < cols.length; j++) {
            cols[j] = -1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (rows[i] == -1) {
                        rows[i] = j;
                    }
                    if (cols[j] == -1) {
                        cols[j] = i;
                    }
                }

                if (rows[i] != -1 || cols[j] != -1) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < rows.length; i++) {
            int v = rows[i];
            if (v != -1) {
                // 补齐行数组中每一行的左边部分
                for (int j = 0; j < v; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < cols.length; j++) {
            int v = cols[j];
            if (v != -1) {
                // 补齐列数组中每一列的上边部分
                for (int i = 0; i < v; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        testSetZeroes1();
        testSetZeroes2();
    }

    public static void printArray(int[] array) {
        System.out.println(java.util.Arrays.toString(array));
    }

    public static void printArray(int[] array, int len) {
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
    }

    public static void printArrays(int[][] arrays) {
        System.out.println(java.util.Arrays.deepToString(arrays));
    }

    public static void testSetZeroes1() {
        int[][] matrix = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };
        new Solution().setZeroes(matrix);
        printArrays(matrix);
    }

    public static void testSetZeroes2() {
        int[][] matrix = {
                { 0, 1, 2, 0 },
                { 3, 4, 5, 2 },
                { 1, 3, 1, 5 }
        };
        new Solution().setZeroes(matrix);
        printArrays(matrix);
    }
}