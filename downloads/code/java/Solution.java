public class Solution {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;

                    boolean[] result = { false };
                    backtrace(board, word, 1, i, j, visited, result);

                    visited[i][j] = false;

                    if (result[0]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void backtrace(char[][] board, String word, int i, int x, int y, boolean[][] visited, boolean[] result) {
        if (result[0]) {
            return;
        }

        if (i == word.length()) {
            result[0] = true;

            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        int[][] points = {
                { x, y - 1 }, { x - 1, y }, { x, y + 1 }, { x + 1, y }
        };

        for (int[] point : points) {
            x = point[0];
            y = point[1];

            if (x < 0 || x >= rows || y < 0 || y >= cols) {
                continue;
            }

            if (board[x][y] != word.charAt(i)) {
                continue;
            }

            if (!visited[x][y]) {
                visited[x][y] = true;
                backtrace(board, word, i + 1, x, y, visited, result);
                visited[x][y] = false;
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
            char[][] board = {
                    { 'A', 'B', 'C', 'E' },
                    { 'S', 'F', 'C', 'S' },
                    { 'A', 'D', 'E', 'E' }
            };
            String word = "ABCCED";

            boolean result = new Solution().exist(board, word);
            boolean expect = true;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase2() {
            char[][] board = {
                    { 'A', 'B', 'C', 'E' },
                    { 'S', 'F', 'C', 'S' },
                    { 'A', 'D', 'E', 'E' }
            };
            String word = "SEE";

            boolean result = new Solution().exist(board, word);
            boolean expect = true;

            return TestUtils.check(result, expect);
        }

        public static boolean testCase3() {
            char[][] board = {
                    { 'A', 'B', 'C', 'E' },
                    { 'S', 'F', 'C', 'S' },
                    { 'A', 'D', 'E', 'E' }
            };
            String word = "ABCB";

            boolean result = new Solution().exist(board, word);
            boolean expect = false;

            return TestUtils.check(result, expect);
        }
    }

    public static class TestUtils {

        public static boolean check(Object result, Object expect) {
            result = normalizeObject(result);
            expect = normalizeObject(expect);
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

        private static Object normalizeObject(Object o) {
            if (o instanceof java.util.List) {
                java.util.List<?> list = (java.util.List<?>) o;
                if (list != null && !list.isEmpty() && list.get(0) instanceof java.util.List) {
                    o = toArrays(list);
                } else {
                    o = toArray(list);
                }
            }

            return o;
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

        public static Object[] toArray(Object o) {
            java.util.List<?> list = (java.util.List<?>) o;
            if (list == null || list.isEmpty()) {
                return new Object[0];
            }

            return list.toArray();
        }

        public static Object[][] toArrays(Object o) {
            java.util.List<?> list = (java.util.List<?>) o;
            if (list == null || list.isEmpty()) {
                return new Object[0][0];
            }

            Object[][] arrays = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                arrays[i] = toArray(list.get(i));
            }

            return arrays;
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