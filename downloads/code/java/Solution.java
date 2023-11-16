public class Solution {
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // 从左上角往右下角扫描
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }

                if (j == 0 || board[i][j - 1] == 'O') {
                    // 左边有出路，什么都不做
                    // do nothing
                } else if (i == 0 || board[i - 1][j] == 'O') {
                    // 上边有出路，什么都不做
                    // do nothing
                } else {
                    // 左边和上边都没有出路，先标记为待处理
                    board[i][j] = 'A';
                }
            }
        }

        // 从右下角往左上角扫描
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (board[i][j] == 'X') {
                    continue;
                }

                // 左边或者上边有出路，可跳过
                else if (board[i][j] == 'O') {
                    continue;
                }

                if (j == cols - 1 || board[i][j + 1] == 'O') {
                    // 右边有出路，复原
                    board[i][j] = 'O';
                } else if (i == rows - 1 || board[i + 1][j] == 'O') {
                    // 下边有出路，复原
                    board[i][j] = 'O';
                } else {
                    // 每个方向都没有出路，标记为可吃掉
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static class TestUtils {
        public static boolean check(Object result, Object expect) {

            String resultString;
            String expectString;
            boolean ok;

            if (result instanceof int[] && expect instanceof int[]) {
                resultString = java.util.Arrays.toString((int[]) result);
                expectString = java.util.Arrays.toString((int[]) expect);

                ok = java.util.Arrays.equals((int[]) result, (int[]) expect);
            } else if (result instanceof float[] && expect instanceof float[]) {
                resultString = java.util.Arrays.toString((float[]) result);
                expectString = java.util.Arrays.toString((float[]) expect);

                ok = java.util.Arrays.equals((float[]) result, (float[]) expect);
            } else if (result instanceof double[] && expect instanceof double[]) {
                resultString = java.util.Arrays.toString((double[]) result);
                expectString = java.util.Arrays.toString((double[]) expect);

                ok = java.util.Arrays.equals((double[]) result, (double[]) expect);
            } else if (result instanceof char[] && expect instanceof char[]) {
                resultString = java.util.Arrays.toString((char[]) result);
                expectString = java.util.Arrays.toString((char[]) expect);

                ok = java.util.Arrays.equals((char[]) result, (char[]) expect);
            } else if (result instanceof boolean[] && expect instanceof boolean[]) {
                resultString = java.util.Arrays.toString((boolean[]) result);
                expectString = java.util.Arrays.toString((boolean[]) expect);

                ok = java.util.Arrays.equals((boolean[]) result, (boolean[]) expect);
            } else if (result instanceof Object[] && expect instanceof Object[]) {
                resultString = java.util.Arrays.deepToString((Object[]) result);
                expectString = java.util.Arrays.deepToString((Object[]) expect);

                ok = java.util.Arrays.deepEquals((Object[]) result, (Object[]) expect);
            } else {
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
            }

            printCheck(resultString, expectString, ok);

            return ok;
        }

        private static String normalizeString(String s) {
            s = s.replace("{", "[");
            s = s.replace("}", "]");

            if (s.startsWith("[") && s.endsWith("]")) {
                s = s.replace(" ", "")
                        .replace("'", "")
                        .replace("\"", "");
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

        public static java.util.List<java.util.List<String>> toLists(String[][] arrays) {
            java.util.List<java.util.List<String>> lists = new java.util.ArrayList<>();
            for (String[] array : arrays) {
                lists.add(java.util.Arrays.asList(array));
            }

            return lists;
        }
    }

    public static void main(String[] args) {
        // TestUtils.runTestCases(Solution.class);

        testCase3();
    }

    public static boolean testCase1() {
        char[][] grid = {
                { 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'X' }
        };

        new Solution().solve(grid);
        char[][] result = grid;
        char[][] expect = {
                { 'X', 'X', 'X', 'X' },
                { 'X', 'X', 'X', 'X' },
                { 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'X', 'X' }
        };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase2() {
        char[][] grid = { { 'X' } };

        new Solution().solve(grid);
        char[][] result = grid;
        char[][] expect = { { 'X' } };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase3() {
        char[][] grid = {
                { 'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'X', 'O' },
                { 'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X' },
                { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X' },
                { 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X' },
                { 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'O' },
                { 'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O' },
                { 'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'O' },
                { 'X', 'X', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'X' },
                { 'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O' },
                { 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O' }
        };

        new Solution().solve(grid);
        char[][] result = grid;
        char[][] expect = {
                { 'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'X', 'O' },
                { 'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X' },
                { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X' },
                { 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X' },
                { 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'O' },
                { 'X', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'O' },
                { 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'O' },
                { 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'X' },
                { 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O' },
                { 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O' }
        };

        // 倒数第3行，倒数第二列的那个O可以左走一步，再向下可以出去
        return TestUtils.check(result, expect);
    }
}