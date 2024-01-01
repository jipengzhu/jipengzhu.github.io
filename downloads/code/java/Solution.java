public class Solution {
    public int[] tps(int[][] graph) {
        int n = graph.length;

        int[] path = new int[n + 1];
        int[] minCost = { Integer.MAX_VALUE };

        boolean[] visited = new boolean[n];
        int[] trail = new int[n];

        int start = 0;

        visited[start] = true;
        trail[0] = start;
        path[n] = start;

        dfs(graph, start, 0, minCost, 1, n, visited, trail, path);

        // int val = 0;
        // for (int i = 0; i < ans.length - 1; i++) {
        // val += graph[ans[i]][ans[i + 1]];
        // }
        // System.out.println(val);
        // System.out.println(minCost[0]);
        // System.out.println(val == minCost[0]);

        return path;
    }

    /**
     * 
     * @param graph
     * @param v       当前顶点序号
     * @param curCost 到达当前顶点时的开销
     * @param minCost 回到起点时的最小开销
     * @param k       当前是第几个顶点（从1开始数）
     * @param n       总共有多少个顶点
     * @param visited 记录结点的访问状态
     * @param trail   记录结点的前进轨迹
     * @param path    记录最终的结果路径
     */
    private void dfs(int[][] graph, int v, int curCost, int[] minCost, int k, int n, boolean[] visited,
            int[] trail, int[] path) {
        // 当前开销已经比最小值大了，需要进行剪枝
        if (curCost > minCost[0]) {
            return;
        }

        if (k == n) {
            int start = 0;
            if (curCost + graph[v][start] < minCost[0]) {
                minCost[0] = curCost + graph[v][start];

                int index = 0;
                for (int t : trail) {
                    path[index++] = t;
                }
            }

            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                trail[k] = i;

                visited[i] = true;
                dfs(graph, i, curCost + graph[v][i], minCost, k + 1, n, visited, trail, path);
                visited[i] = false; // 回溯时撤销选择
            }
        }
    }
}

class TestMain {

    public static void main(String[] args) {
        TestUtils.runTestCases(TestCase.class);
    }

    public static class TestCase {

        public static boolean testCase1() {
            int[][] graph = {
                    { 0, 2, 6, 5 },
                    { 2, 0, 4, 4 },
                    { 6, 4, 0, 2 },
                    { 5, 4, 2, 0 }
            };

            int[] result = new Solution().tps(graph);
            int[] expect = { 0, 1, 2, 3, 0 };

            return TestUtils.check(result, expect);
        }
    }

    public static class TestUtils {

        public static boolean check(Object result, Object expect) {
            boolean ok = java.util.Objects.deepEquals(result, expect);
            printCheck(toString(result), toString(expect), ok);
            return ok;
        }

        public static boolean check(Object result, String expect) {
            boolean ok = java.util.Objects.equals(normalizeString(toString(result)),
                    normalizeString(toString(expect)));
            printCheck(normalizeString(toString(result)), normalizeString(toString(expect)), ok);
            return ok;
        }

        public static <T> boolean check(java.util.List<T> result, T[] expect, Class<T> clazz) {
            return check(toArray(result, clazz), expect);
        }

        public static <T> boolean check(java.util.List<java.util.List<T>> result, T[][] expect, Class<T> clazz) {
            return check(toArrays(result, clazz), expect);
        }

        private static String toString(Object o) {
            if (o instanceof Object[]) {
                return java.util.Arrays.deepToString((Object[]) o);
            } else if (o instanceof int[]) {
                return java.util.Arrays.toString((int[]) o);
            } else if (o instanceof double[]) {
                return java.util.Arrays.toString((double[]) o);
            } else if (o instanceof char[]) {
                return java.util.Arrays.toString((char[]) o);
            } else {
                return java.util.Objects.toString(o);
            }
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