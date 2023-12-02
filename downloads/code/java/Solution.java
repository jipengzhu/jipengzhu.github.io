import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int snakesAndLadders(int[][] board) {
        if (board.length == 0) {
            return 0;
        }

        int rows = board.length;
        int cols = board[0].length;

        int count = rows * cols;

        // 存储关联的传送点
        // 多用一个空间来省去值和下标的转换
        int[] jumps = new int[count + 1];

        // 存储到达某个点时需要移动的最少次数
        // 多用一个空间来省去值和下标的转换
        int[] steps = new int[count + 1];

        int v = 1;
        boolean reversed = false;
        for (int i = rows - 1; i >= 0; i--) {
            if (!reversed) {
                for (int j = 0; j < cols; j++) {
                    jumps[v++] = board[i][j];
                }
            } else {
                for (int j = cols - 1; j >= 0; j--) {
                    jumps[v++] = board[i][j];
                }
            }

            reversed = !reversed;
        }

        boolean[] visited = new boolean[count + 1];

        int p = 1;

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(p);

        // 标记为已访问
        visited[p] = true;

        while (!queue.isEmpty()) {
            p = queue.poll();

            int step = steps[p];

            int b = p + 1;
            int e = p + 6;
            if (e > count) {
                e = count;
            }

            for (int q = b; q <= e; q++) {
                int r;
                if (jumps[q] == -1) {
                    r = q;
                } else {
                    r = jumps[q];
                }

                if (!visited[r]) {
                    queue.offer(r);

                    // 标记为已访问
                    visited[r] = true;
                }

                // 更新到达当前点的最少移动次数
                if (steps[r] == 0 || steps[r] > step + 1) {
                    steps[r] = step + 1;
                }
            }
        }

        return steps[count] > 0 ? steps[count] : -1;
    }

    public static class TestUtils {
        public static boolean check(int[] result, int[] expect) {
            String resultString = java.util.Arrays.toString(result);
            String expectString = java.util.Arrays.toString(expect);
            boolean ok = java.util.Arrays.equals(result, expect);

            printCheck(resultString, expectString, ok);

            return ok;
        }

        public static boolean check(double[] result, double[] expect) {
            String resultString = java.util.Arrays.toString(result);
            String expectString = java.util.Arrays.toString(expect);
            boolean ok = java.util.Arrays.equals(result, expect);

            printCheck(resultString, expectString, ok);

            return ok;
        }

        public static boolean check(char[] result, char[] expect) {
            String resultString = java.util.Arrays.toString(result);
            String expectString = java.util.Arrays.toString(expect);
            boolean ok = java.util.Arrays.equals(result, expect);

            printCheck(resultString, expectString, ok);

            return ok;
        }

        public static boolean check(Object[] result, Object[] expect) {
            String resultString = java.util.Arrays.deepToString(result);
            String expectString = java.util.Arrays.deepToString(expect);
            boolean ok = java.util.Arrays.deepEquals(result, expect);

            printCheck(resultString, expectString, ok);

            return ok;
        }

        public static boolean check(Object result, Object expect) {
            String resultString;
            String expectString;
            boolean ok;

            if (result instanceof Object[]) {
                resultString = java.util.Arrays.deepToString((Object[]) result);
            } else {
                resultString = result.toString();
            }

            if (expect instanceof Object[]) {
                expectString = java.util.Arrays.deepToString((Object[]) expect);
            } else {
                expectString = expect.toString();
            }

            resultString = normalizeString(resultString);
            expectString = normalizeString(expectString);

            ok = java.util.Objects.equals(resultString, expectString);

            printCheck(resultString, expectString, ok);

            return ok;
        }

        public static <T> boolean check(java.util.List<T> result, T[] expect, Class<T> clazz) {
            return check(toArray(result, clazz), expect);
        }

        public static <T> boolean check(java.util.List<java.util.List<T>> result, T[][] expect, Class<T> clazz) {
            return check(toArrays(result, clazz), expect);
        }

        private static String normalizeString(String s) {
            boolean isArray = (s.startsWith("{") && s.endsWith("}")) || (s.startsWith("[") && s.endsWith("]"));
            if (isArray) {
                s = s.replace(" ", "")
                        .replace("'", "")
                        .replace("\"", "")
                        .replace("{", "[")
                        .replace("}", "]");
            }

            return s;
        }

        private static void printCheck(String result, String expect, boolean ok) {
            // System.out.println();
            // System.out.println("--->");

            System.out.println("result: " + result);
            System.out.println("expect: " + expect);

            System.out.println();
            if (ok) {
                System.out.println("比较结果为: 正确(right)");
            } else {
                System.out.println("比较结果为: 错误(error)");
            }

            // System.out.println();
            // System.out.println("<---");
        }

        public static void runTestCases(Class<?> clazz) {
            java.lang.reflect.Method[] methods = clazz.getDeclaredMethods();
            java.util.Arrays.sort(methods, (o1, o2) -> {
                return o1.getName().compareTo(o2.getName());
            });
            for (java.lang.reflect.Method method : methods) {
                if (method.getName().startsWith("test")) {
                    System.out.println(String.format("--- %s --->", method.getName()));

                    Object object = null;
                    try {
                        object = method.invoke(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(String.format("<--- %s ---", method.getName()));

                    if (object instanceof Boolean) {
                        Boolean ok = (Boolean) object;
                        if (!ok) {
                            break;
                        }
                    } else {
                        throw new RuntimeException(String.format("测试用例方法%s的返回值需要为布尔类型", method.getName()));
                    }
                }
            }
        }

        @SuppressWarnings("unchecked")
        public static <T> T[] genArray(Class<T> clazz, int length) {
            return (T[]) java.lang.reflect.Array.newInstance(clazz, length);
        }

        @SuppressWarnings("unchecked")
        public static <T> T[][] genArrays(Class<T> clazz, int rows, int cols) {
            T[] array = genArray(clazz, 0);

            T[][] arrays = (T[][]) genArray(array.getClass(), rows);
            for (int i = 0; i < arrays.length; i++) {
                arrays[i] = genArray(clazz, cols);
            }

            return arrays;
        }

        public static <T> T[] toArray(java.util.List<T> list, Class<T> clazz) {
            if (list == null || list.isEmpty()) {
                return genArray(clazz, 0);
            }

            T[] array = genArray(clazz, list.size());
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

            return array;
        }

        public static <T> T[][] toArrays(java.util.List<java.util.List<T>> lists, Class<T> clazz) {
            if (lists == null || lists.isEmpty()) {
                return genArrays(clazz, 0, 0);
            }

            T[][] arrays = genArrays(clazz, lists.size(), 0);
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = toArray(lists.get(i), clazz);
            }

            return arrays;
        }

        public static <T> java.util.List<T> toList(T[] array) {
            return java.util.Arrays.asList(array);
        }

        public static <T> java.util.List<java.util.List<T>> toLists(T[][] arrays) {
            java.util.List<java.util.List<T>> lists = new java.util.ArrayList<>();
            for (T[] array : arrays) {
                lists.add(toList(array));
            }

            return lists;
        }
    }

    public static void main(String[] args) {
        TestUtils.runTestCases(Solution.class);
    }

    public static boolean testCase1() {
        int[][] board = {
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 35, -1, -1, 13, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 15, -1, -1, -1, -1 }
        };

        int result = new Solution().snakesAndLadders(board);
        int expect = 4;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase2() {
        int[][] board = {
                { -1, -1 },
                { -1, 3 }
        };

        int result = new Solution().snakesAndLadders(board);
        int expect = 1;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase3() {
        int[][] board = {
                { -1, -1, -1 },
                { -1, 9, -1 },
                { -1, -1, -1 }
        };

        int result = new Solution().snakesAndLadders(board);
        int expect = 1;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase4() {
        int[][] board = {
                { -1, 4, -1 },
                { 6, 2, 6 },
                { -1, 3, -1 }
        };

        int result = new Solution().snakesAndLadders(board);
        int expect = 2;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase5() {
        int[][] board = {
                { 1, 1, -1 },
                { 1, 1, 1 },
                { -1, 1, 1 }
        };

        int result = new Solution().snakesAndLadders(board);
        int expect = -1;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase6() {
        int[][] board = {
                { -1, -1, 30, 14, 15, -1 },
                { 23, 9, -1, -1, -1, 9 },
                { 12, 5, 7, 24, -1, 30 },
                { 10, -1, -1, -1, 25, 17 },
                { 32, -1, 28, -1, -1, 32 },
                { -1, -1, 23, -1, 13, 19 }
        };

        int result = new Solution().snakesAndLadders(board);
        int expect = 2;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase7() {
        int[][] board = {
                { -1, 1, 1, 1 },
                { -1, 7, 1, 1 },
                { 16, 1, 1, 1 },
                { -1, 1, 9, 1 }
        };

        int result = new Solution().snakesAndLadders(board);
        int expect = 3;

        return TestUtils.check(result, expect);
    }
}