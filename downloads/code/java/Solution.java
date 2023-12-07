import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        for (int i = 0; i < bank.length; i++) {
            int c = getDifferenceCharCount(startGene, bank[i]);
            if (c == 1) {
                graph.computeIfAbsent(startGene, k -> new HashMap<>()).put(bank[i], 1);
            }

            for (int j = i + 1; j < bank.length; j++) {
                c = getDifferenceCharCount(bank[i], bank[j]);
                if (c == 1) {
                    graph.computeIfAbsent(bank[i], k -> new HashMap<>()).put(bank[j], 1);
                    graph.computeIfAbsent(bank[j], k -> new HashMap<>()).put(bank[i], 1);
                }
            }
        }

        if (!graph.containsKey(startGene)) {
            return -1;
        }

        Map<String, Integer> steps = new HashMap<>();

        Set<String> visited = new HashSet<>();

        String p = startGene;

        Queue<String> queue = new LinkedList<>();

        queue.offer(p);

        // 标记为已访问
        visited.add(p);

        while (!queue.isEmpty()) {
            p = queue.poll();

            int step = steps.getOrDefault(p, 0);

            for (String q : graph.computeIfAbsent(p, k -> new HashMap<>()).keySet()) {
                if (!visited.contains(q)) {
                    queue.offer(q);

                    // 标记为已访问
                    visited.add(q);
                }

                // 更新到达当前点的最少移动次数
                if (!steps.containsKey(q) || steps.get(q) > step + 1) {
                    steps.put(q, step + 1);
                }
            }
        }

        return steps.getOrDefault(endGene, -1);
    }

    private int getDifferenceCharCount(String s, String t) {
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                c++;
            }
        }

        return c;
    }
}

class TestMain {

    public static void main(String[] args) {
        TestUtils.runTestCases(TestCase.class);
    }

    public static class TestCase {
        public static boolean testCase1() {
            String start = "AACCGGTT";
            String end = "AACCGGTA";
            String[] bank = { "AACCGGTA" };

            int result = new Solution().minMutation(start, end, bank);
            int expect = 1;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase2() {
            String start = "AACCGGTT";
            String end = "AAACGGTA";
            String[] bank = { "AACCGGTA", "AACCGCTA", "AAACGGTA" };

            int result = new Solution().minMutation(start, end, bank);
            int expect = 2;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase3() {
            String start = "AAAAACCC";
            String end = "AACCCCCC";
            String[] bank = { "AAAACCCC", "AAACCCCC", "AACCCCCC" };

            int result = new Solution().minMutation(start, end, bank);
            int expect = 3;

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