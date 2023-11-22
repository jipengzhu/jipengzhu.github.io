import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            Double value = values[i];

            String s1 = equation.get(0);
            String s2 = equation.get(1);

            graph.computeIfAbsent(s1, s -> new HashMap<>()).put(s2, value);
            graph.computeIfAbsent(s2, s -> new HashMap<>()).put(s1, 1 / value);
        }

        double[] array = new double[queries.size()];
        for (int i = 0; i < array.length; i++) {
            List<String> query = queries.get(i);

            String x = query.get(0);
            String y = query.get(1);

            if (!graph.containsKey(x) || !graph.containsKey(y)) {
                array[i] = -1.0d;
            } else if (x.equals(y)) {
                array[i] = 1.0d;
            } else {
                Set<String> visited = new HashSet<>();
                Deque<String> stack = new LinkedList<>();

                String p = x;
                while (p != null || !stack.isEmpty()) {
                    if (p != null) {
                        // 标记为已访问
                        visited.add(p);

                        // 找到除数了
                        if (p.equals(y)) {
                            stack.push(p);

                            break;
                        }

                        String q = null;
                        for (String s : graph.get(p).keySet()) {
                            if (!visited.contains(s)) {
                                q = s;
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
                    // List<String> path = new LinkedList<>();
                    // while (!stack.isEmpty()) {
                    // path.add(0, stack.pop());
                    // }

                    // double value = 1.0d;
                    // for (int j = 0; j < path.size() - 1; j++) {
                    // String s1 = path.get(j);
                    // String s2 = path.get(j + 1);

                    // value = value * graph.get(s1).get(s2);
                    // }

                    double value = 1.0d;
                    String s2 = stack.pop();
                    while (!stack.isEmpty()) {
                        String s1 = stack.pop();

                        value = value * graph.get(s1).get(s2);

                        s2 = s1;
                    }

                    array[i] = value;
                } else {
                    array[i] = -1.0d;
                }
            }
        }

        return array;
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

        public static <T> java.util.List<java.util.List<T>> toLists(T[][] arrays) {
            java.util.List<java.util.List<T>> lists = new java.util.ArrayList<>();
            for (T[] array : arrays) {
                lists.add(java.util.Arrays.asList(array));
            }

            return lists;
        }
    }

    public static void main(String[] args) {
        TestUtils.runTestCases(Solution.class);
    }

    public static boolean testCase1() {
        String[][] equations = {
                { "a", "b" },
                { "b", "c" }
        };
        double[] values = { 2.0, 3.0 };
        String[][] queries = {
                { "a", "c" },
                { "b", "a" },
                { "a", "e" },
                { "a", "a" },
                { "x", "x" }
        };

        double[] result = new Solution().calcEquation(TestUtils.toLists(equations), values,
                TestUtils.toLists(queries));
        double[] expect = { 6.00000, 0.50000, -1.00000, 1.00000, -1.00000 };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase2() {
        String[][] equations = {
                { "a", "b" },
                { "b", "c" },
                { "bc", "cd" }
        };
        double[] values = { 1.5, 2.5, 5.0 };
        String[][] queries = {
                { "a", "c" },
                { "c", "b" },
                { "bc", "cd" },
                { "cd", "bc" }
        };

        double[] result = new Solution().calcEquation(TestUtils.toLists(equations), values,
                TestUtils.toLists(queries));
        double[] expect = { 3.75000, 0.40000, 5.00000, 0.20000 };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase3() {
        String[][] equations = {
                { "a", "b" },
        };
        double[] values = { 0.5 };
        String[][] queries = {
                { "a", "b" },
                { "b", "a" },
                { "a", "c" },
                { "x", "y" }
        };

        double[] result = new Solution().calcEquation(TestUtils.toLists(equations), values,
                TestUtils.toLists(queries));
        double[] expect = { 0.50000, 2.00000, -1.00000, -1.00000 };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase4() {
        String[][] equations = {
                { "a", "b" },
        };
        double[] values = { 0.5 };
        String[][] queries = {
                { "a", "b" },
                { "b", "a" },
                { "a", "c" },
                { "c", "a" },
                { "x", "y" }
        };

        double[] result = new Solution().calcEquation(TestUtils.toLists(equations), values,
                TestUtils.toLists(queries));
        double[] expect = { 0.50000, 2.00000, -1.00000, -1.00000, -1.00000 };

        return TestUtils.check(result, expect);
    }

    public static boolean testCase5() {
        String[][] equations = {
                { "x1", "x2" },
                { "x2", "x3" },
                { "x3", "x4" },
                { "x4", "x5" }
        };
        double[] values = { 3.0, 4.0, 5.0, 6.0 };
        String[][] queries = {
                { "x1", "x5" },
                { "x5", "x2" },
                { "x2", "x4" },
                { "x2", "x2" },
                { "x2", "x9" },
                { "x9", "x9" }
        };

        double[] result = new Solution().calcEquation(TestUtils.toLists(equations), values,
                TestUtils.toLists(queries));
        double[] expect = { 360.0, 0.008333333333333333, 20.0, 1.0, -1.0, -1.0 };

        return TestUtils.check(result, expect);
    }
}