class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int e = intervals.length - 1;

        int i = 0;
        while (i <= e && newInterval[0] > intervals[i][1]) {
            i++;
        }
        if (i > e) {
            int[][] result = new int[intervals.length + 1][2];
            int k = 0;
            while (k < intervals.length) {
                result[k][0] = intervals[k][0];
                result[k][1] = intervals[k][1];

                k++;
            }

            result[k][0] = newInterval[0];
            result[k][1] = newInterval[1];

            return result;
        }

        int j = e;
        while (j >= 0 && newInterval[1] < intervals[j][0]) {
            j--;
        }
        if (j < 0) {
            int[][] result = new int[intervals.length + 1][2];
            int k = 0;

            result[k][0] = newInterval[0];
            result[k][1] = newInterval[1];

            while (k < intervals.length) {
                result[k + 1][0] = intervals[k][0];
                result[k + 1][1] = intervals[k][1];

                k++;
            }

            return result;
        }

        int[][] result = new int[i + e - j + 1][2];
        int p = 0;
        for (int k = 0; k < i; k++) {
            result[p][0] = intervals[k][0];
            result[p][1] = intervals[k][1];
            p++;
        }

        int L = newInterval[0];
        if (intervals[i][0] < L) {
            L = intervals[i][0];
        }
        int R = newInterval[1];
        if (intervals[j][1] > R) {
            R = intervals[j][1];
        }
        result[p][0] = L;
        result[p][1] = R;
        p++;
        
        for (int k = j + 1; k <= e; k++) {
            result[p][0] = intervals[k][0];
            result[p][1] = intervals[k][1];
            p++;
        }

        return result;
    }

    public static void main(String[] args) {
        testInsert1();
        testInsert2();
        testInsert3();
        testInsert4();
        testInsert5();
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

    public static void testInsert1() {
        int[][] intervals = {
                { 1, 3 },
                { 6, 9 }
        };
        int[] newInterval = { 2, 5 };
        printArrays(new Solution().insert(intervals, newInterval));
    }

    public static void testInsert2() {
        int[][] intervals = {
                { 1, 2 },
                { 3, 5 },
                { 6, 7 },
                { 8, 10 },
                { 12, 16 }
        };
        int[] newInterval = { 4, 8 };
        printArrays(new Solution().insert(intervals, newInterval));
    }

    public static void testInsert3() {
        int[][] intervals = {

        };
        int[] newInterval = { 5, 7 };
        printArrays(new Solution().insert(intervals, newInterval));
    }

    public static void testInsert4() {
        int[][] intervals = {
                { 1, 5 }
        };
        int[] newInterval = { 2, 3 };
        printArrays(new Solution().insert(intervals, newInterval));
    }

    public static void testInsert5() {
        int[][] intervals = {
                { 1, 5 }
        };
        int[] newInterval = { 2, 7 };
        printArrays(new Solution().insert(intervals, newInterval));
    }
}