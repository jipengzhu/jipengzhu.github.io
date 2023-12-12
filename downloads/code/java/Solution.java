public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int[] time : times) {
            int i = time[0] - 1;
            int j = time[1] - 1;
            int v = time[2];

            graph[i][j] = v;
        }

        int[][] dists = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dists[i][j] = graph[i][j];
            }
        }

        for (int t = 0; t < n; t++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dists[i][t] == Integer.MAX_VALUE || dists[t][j] == Integer.MAX_VALUE) {
                        // 不可能变小，跳过
                        continue;
                    }

                    if (dists[i][t] + dists[t][j] < dists[i][j]) {
                        dists[i][j] = dists[i][t] + dists[t][j];
                    }
                }
            }
        }

        int[] dist = dists[k - 1];
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (i == k - 1) {
                continue;
            }

            int d = dist[i];

            if (d == Integer.MAX_VALUE) {
                return -1;
            } else if (d > maxValue) {
                maxValue = d;
            }
        }

        return maxValue;
    }
}

class TestMain {

    public static void main(String[] args) {
        TestUtils.runTestCases(TestCase.class);
    }

    public static class TestCase {

        public static boolean testCase1() {
            int[][] times = {
                    { 2, 1, 1 },
                    { 2, 3, 1 },
                    { 3, 4, 1 }
            };
            int n = 4;
            int k = 2;

            int result = new Solution().networkDelayTime(times, n, k);
            int expect = 2;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase2() {
            int[][] times = {
                    { 2, 1, 1 },
                    { 2, 3, 1 },
                    { 3, 4, 1 }
            };
            int n = 4;
            int k = 3;

            int result = new Solution().networkDelayTime(times, n, k);
            int expect = -1;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase3() {
            int[][] times = {
                    { 1, 2, 1 }
            };
            int n = 2;
            int k = 1;

            int result = new Solution().networkDelayTime(times, n, k);
            int expect = 1;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase4() {
            int[][] times = {
                    { 1, 2, 1 }
            };
            int n = 2;
            int k = 2;

            int result = new Solution().networkDelayTime(times, n, k);
            int expect = -1;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase5() {
            int[][] times = {
                    { 2, 7, 63 },
                    { 4, 3, 60 },
                    { 1, 3, 53 },
                    { 5, 6, 100 },
                    { 1, 4, 40 },
                    { 4, 7, 95 },
                    { 4, 6, 97 },
                    { 3, 4, 68 },
                    { 1, 7, 75 },
                    { 2, 6, 84 },
                    { 1, 6, 27 },
                    { 5, 3, 25 },
                    { 6, 2, 2 },
                    { 3, 7, 57 },
                    { 5, 4, 2 },
                    { 7, 1, 53 },
                    { 5, 7, 35 },
                    { 4, 1, 60 },
                    { 5, 2, 95 },
                    { 3, 5, 28 },
                    { 6, 1, 61 },
                    { 2, 5, 28 }
            };
            int n = 7;
            int k = 3;

            int result = new Solution().networkDelayTime(times, n, k);
            int expect = 119;

            return TestUtils.check(result, expect);
        }
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
}