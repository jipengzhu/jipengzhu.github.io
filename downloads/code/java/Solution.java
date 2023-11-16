import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<String>> adjMap = new HashMap<>();
        Map<String, Double> valMap = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            Double value = values[i];

            String s1 = equation.get(0);
            String s2 = equation.get(1);

            adjMap.computeIfAbsent(s1, s -> new LinkedList<>()).add(s2);
            adjMap.computeIfAbsent(s2, s -> new LinkedList<>()).add(s1);

            valMap.put(String.format("%s/%s", s1, s2), value);
            valMap.put(String.format("%s/%s", s2, s1), 1 / value);
        }

        double[] array = new double[queries.size()];
        for (int i = 0; i < array.length; i++) {
            List<String> query = queries.get(i);

            String x = query.get(0);
            String y = query.get(1);

            if (!adjMap.containsKey(x) || !adjMap.containsKey(y)) {
                array[i] = -1.0d;
            } else if (x.equals(y)) {
                array[i] = 1.0d;
            } else {
                Map<String, List<String>> unvisitedMap = new HashMap<>();
                Deque<String> stack = new LinkedList<>();

                String p = x;
                while (p != null || !stack.isEmpty()) {
                    if (p != null) {
                        if (p.equals(y)) {
                            stack.push(p);

                            break;
                        }

                        List<String> unvisitedList = unvisitedMap.computeIfAbsent(p, k -> {
                            List<String> list = new LinkedList<>();

                            for (String s : adjMap.get(k)) {
                                // 不能走回头路
                                if (s.equals(stack.peek())) {
                                    continue;
                                }

                                list.add(s);
                            }

                            return list;
                        });

                        if (!unvisitedList.isEmpty()) {
                            stack.push(p); // 保存回退点

                            p = unvisitedList.remove(0); // 前进
                        } else {
                            p = null;
                        }
                    } else {
                        p = stack.pop(); // 回溯
                    }
                }

                if (stack.isEmpty()) {
                    array[i] = -1.0d;
                } else {
                    List<String> path = new LinkedList<>();
                    while (!stack.isEmpty()) {
                        path.add(0, stack.pop());
                    }

                    double value = 1.0d;
                    for (int j = 0; j < path.size() - 1; j++) {
                        String s1 = path.get(j);
                        String s2 = path.get(j + 1);

                        value = value * valMap.get(String.format("%s/%s", s1, s2));
                    }

                    array[i] = value;
                }
            }
        }

        return array;
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