import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solution {

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] hasOuts = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }

                if (hasOuts[i][j]) {
                    // 有出路，跳过
                    continue;
                }

                boolean hasOut = false;
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    hasOut = true;
                } else {
                    int[][] points = {
                            { i, j - 1 }, { i - 1, j }, { i, j + 1 }, { i + 1, j }
                    };

                    for (int[] point : points) {
                        // 如果某个相邻点有出路，可以通过相邻点出去
                        if (hasOuts[point[0]][point[1]]) {
                            hasOut = true;

                            break;
                        }
                    }
                }

                if (hasOut) {
                    hasOuts[i][j] = true;

                    continue;
                }

                Map<Integer, Map<Integer, Integer>> visited = new HashMap<>();
                Deque<int[]> stack = new LinkedList<>();

                int[] p = new int[] { i, j };
                while (p != null || !stack.isEmpty()) {
                    if (p != null) {
                        int x = p[0];
                        int y = p[1];

                        // 标记为已访问
                        visited.computeIfAbsent(x, k -> new HashMap<>()).put(y, null);

                        // 找到出路了
                        if (x == 0 || x == rows - 1 || y == 0 || y == cols - 1) {
                            stack.push(p);

                            break;
                        }

                        int[][] points = new int[][] {
                                { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
                        };

                        int[] q = null;
                        for (int[] point : points) {
                            x = point[0];
                            y = point[1];

                            if (x < 0 || x >= rows || y < 0 || y >= cols) {
                                // 无效点，跳过
                                continue;
                            }

                            if (visited.computeIfAbsent(x, k -> new HashMap<>()).containsKey(y)) {
                                // 已访问，跳过
                                continue;
                            }

                            if (board[x][y] == 'O') {
                                q = point;

                                break;
                            }
                        }

                        if (q != null) {
                            stack.push(p); // 保存回退点

                            p = q; // 前进
                        } else {
                            // p = stack.isEmpty() ? null : stack.pop(); // 回溯
                            p = null; // 在下一次循环时跳转到回溯分支
                        }
                    } else {
                        p = stack.pop(); // 回溯
                    }
                }

                if (p != null || !stack.isEmpty()) {
                    // 找到了出路
                    while (!stack.isEmpty()) {
                        int[] point = stack.pop();
                        hasOuts[point[0]][point[1]] = true;
                    }
                } else {
                    // 没有找到出路
                    for (Integer x : visited.keySet()) {
                        for (Integer y : visited.get(x).keySet()) {
                            board[x][y] = 'X';
                        }

                    }
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
        TestUtils.runTestCases(Solution.class);
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

        return TestUtils.check(result, expect);
    }

    public static boolean testCase4() {
        char[][] grid = {
                { 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'O', 'X' },
                { 'X', 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'X', 'X' }
        };

        new Solution().solve(grid);
        char[][] result = grid;
        char[][] expect = {
                { 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'X', 'X', 'X' }
        };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase5() {
        char[][] grid = {
                { 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'O', 'X' },
                { 'X', 'O', 'O', 'O', 'X' },
                { 'X', 'O', 'X', 'X', 'X' }
        };

        new Solution().solve(grid);
        char[][] result = grid;
        char[][] expect = {
                { 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'O', 'X' },
                { 'X', 'O', 'O', 'O', 'X' },
                { 'X', 'O', 'X', 'X', 'X' }
        };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase6() {
        char[][] grid = {
                { 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'O' },
                { 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X' },
                { 'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X' },
                { 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O' },
                { 'X', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O' },
                { 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X' },
                { 'O', 'O', 'O', 'X', 'X', 'O', 'X', 'O', 'X' },
                { 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O' },
                { 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'X', 'O' }
        };

        new Solution().solve(grid);
        char[][] result = grid;
        char[][] expect = {
                { 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'O' },
                { 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X' },
                { 'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X' },
                { 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O' },
                { 'X', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O' },
                { 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X' },
                { 'O', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'X' },
                { 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O' },
                { 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'X', 'O' }
        };

        return TestUtils.check(result, expect);
    }
}