import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        transformArrayLiteral();

        // testTestUtils();

        // testToString();
    }

    private static void transformArrayLiteral() {
        String s = "[[0, -1, 1], [0, 1, -1], [-1, 0, 1], [-1, 1, 0], [1, 0, -1], [1, -1, 0]]";
        boolean isChar = false;
        // boolean isChar = true;
        boolean toFile = false;
        // boolean toFile = true;

        jsArrayLiteral2JavaArrayLiteral(s, true, isChar, toFile);
        jsArrayLiteral2JavaArrayLiteral(s, false, isChar, toFile);
    }

    private static void jsArrayLiteral2JavaArrayLiteral(String s, boolean pretty, boolean isChar, boolean toFile) {
        s = s.replace('[', '{');
        s = s.replace(']', '}');

        if (pretty) {
            s = s.replace("{{", "{\n{");
            s = s.replace("},", "},\n");
            s = s.replace("}}", "}\n}");
        }

        if (isChar) {
            s = s.replace("\"", "'");
        }

        if (toFile) {
            String fileName = "tmp_array.txt";
            if (pretty) {
                fileName = "tmp_pretty_array.txt";
            }

            File file = new File(fileName);
            try (FileOutputStream fo = new FileOutputStream(file)) {
                fo.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println();
            System.out.println(s);
        }
    }

    private static void testTestUtils() {
        TestMain.TestCase.testTestUtils();
    }

    private static void testToString() {
        TestMain.TestCase.testToString();
    }

    public static class TestMain {
        public static class TestCase {
            public static void testTestUtils() {
                int[] a11 = { 1, 2, 3 };
                int[] b11 = { 1, 2, 3 };
                TestUtils.check(a11, b11);

                double[] a13 = { 1.0d, 2.0d, 3.0d };
                double[] b13 = { 1.0d, 2.0d, 3.0d };
                TestUtils.check(a13, b13);

                char[] a15 = { '1', '2', '3' };
                char[] b15 = { '1', '2', '3' };
                TestUtils.check(a15, b15);

                Integer[] a31 = { 1, 2, 3 };
                Integer[] b31 = { 1, 2, 3 };
                TestUtils.check(a31, b31);

                Double[] a33 = { 1.0d, 2.0d, 3.0d };
                Double[] b33 = { 1.0d, 2.0d, 3.0d };
                TestUtils.check(a33, b33);

                Character[] a35 = { '1', '2', '3' };
                Character[] b35 = { '1', '2', '3' };
                TestUtils.check(a35, b35);

                String[] a37 = { "1", "2", "3" };
                String[] b37 = { "1", "2", "3" };
                TestUtils.check(a37, b37);

                int[][] a51 = { { 1, 2, 3 }, { 7, 8, 9 } };
                int[][] b51 = { { 1, 2, 3 }, { 7, 8, 9 } };
                TestUtils.check(a51, b51);

                double[][] a53 = { { 1.0d, 2.0d, 3.0d }, { 7.0d, 8.0d, 9.0d } };
                double[][] b53 = { { 1.0d, 2.0d, 3.0d }, { 7.0d, 8.0d, 9.0d } };
                TestUtils.check(a53, b53);

                char[][] a55 = { { '1', '2', '3' }, { '7', '8', '9' } };
                char[][] b55 = { { '1', '2', '3' }, { '7', '8', '9' } };
                TestUtils.check(a55, b55);

                Character[][] a57 = { { '1', '2', '3' }, { '7', '8', '9' } };
                Character[][] b57 = { { '1', '2', '3' }, { '7', '8', '9' } };
                TestUtils.check(a57, b57);

                String[][] a59 = { { "1", "2", "3" }, { "7", "8", "9" } };
                String[][] b59 = { { "1", "2", "3" }, { "7", "8", "9" } };
                TestUtils.check(a59, b59);

                Character[][] a71 = { { '1', '2', '3' }, { '7', '8', '9' } };
                String b71 = "[['1','2','3'],['7','8','9']]";
                TestUtils.check(a71, b71);

                Integer[] a91 = { 1, 2, 3 };
                Integer[] b91 = TestUtils.toArray(TestUtils.toList(a91), Integer.class);

                TestUtils.check(a91, b91);

                Integer[][] a93 = { { 1, 2, 3 }, { 7, 8, 9 } };
                Integer[][] b93 = TestUtils.toArrays(TestUtils.toLists(a93), Integer.class);

                TestUtils.check(a93, b93);

                TestUtils.genArray(Integer.class, 3)[2] = 1;
                TestUtils.genArrays(Integer.class, 3, 3)[2][2] = 1;
            }

            public static void testToString() {

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

        public static class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
                next = null;
            }

            public static ListNode genLinkedList(int[] nums, boolean withHead, int loopPos) {
                if (nums.length == 0) {
                    return null;
                }

                int val = withHead ? Integer.MIN_VALUE : nums[0];
                ListNode head = new ListNode(val);

                ListNode loop = withHead ? null : loopPos == 0 ? head : null;

                int b = withHead ? 0 : 1;
                ListNode p = head;
                for (int i = b; i < nums.length; i++) {
                    p.next = new ListNode(nums[i]);
                    p = p.next;

                    if (loopPos == i) {
                        loop = p;
                    }
                }

                if (loop != null) {
                    p.next = loop;
                }

                return head;
            }

            public static ListNode genLinkedListWithHeadNode(int[] nums) {
                return genLinkedList(nums, true, -1);
            }

            public static ListNode genLinkedListWithoutHeadNode(int[] nums) {
                return genLinkedList(nums, false, -1);
            }

            public static ListNode genLoopedLinkedListWithHeadNode(int[] nums, int pos) {
                return genLinkedList(nums, true, pos);
            }

            public static ListNode genLoopedLinkedListWithoutHeadNode(int[] nums, int pos) {
                return genLinkedList(nums, false, pos);
            }

            public static int getCount(ListNode head, boolean withHead) {
                ListNode p = withHead ? head.next : head;

                int c = 0;
                while (p != null) {
                    c++;
                    p = p.next;
                }

                return c;
            }

            public static int[] toArray(ListNode head, boolean withHead) {
                int c = getCount(head, withHead);
                int[] nums = new int[c];

                ListNode p = withHead ? head.next : head;

                int k = 0;
                while (p != null) {
                    nums[k] = p.val;

                    k++;
                    p = p.next;
                }

                return nums;
            }

            public static int[] toArrayWithHeadNode(ListNode head) {
                return toArray(head, true);
            }

            public static int[] toArrayWithoutHeadNode(ListNode head) {
                return toArray(head, false);
            }
        }

        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }

            public static TreeNode genTree(Integer[] array) {
                if (array.length == 0) {
                    return null;
                }

                java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
                TreeNode root = new TreeNode(array[0]);
                queue.offer(root);

                int i = 1;
                while (i < array.length) {
                    TreeNode node = queue.poll();

                    if (i < array.length) {
                        Integer v = array[i++];

                        node.left = v == null ? null : new TreeNode(v);

                        if (node.left != null) {
                            queue.offer(node.left);
                        }
                    }

                    if (i < array.length) {
                        Integer v = array[i++];

                        node.right = v == null ? null : new TreeNode(v);

                        if (node.right != null) {
                            queue.offer(node.right);
                        }
                    }
                }

                return root;
            }

            public static Integer[] toArray(TreeNode root) {
                if (root == null) {
                    return new Integer[0];
                }

                java.util.LinkedList<Integer> list = new java.util.LinkedList<>();

                java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
                queue.offer(root);

                while (!queue.isEmpty()) {
                    boolean hasValidNode = false;

                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        TreeNode node = queue.poll();

                        if (node != null) {
                            list.add(node.val);

                            queue.offer(node.left);
                            queue.offer(node.right);

                            if (!hasValidNode) {
                                hasValidNode = node.left != null || node.right != null;
                            }
                        } else {
                            list.add(null);
                        }
                    }

                    if (!hasValidNode) {
                        // while (!queue.isEmpty()) {
                        // queue.poll();
                        // }

                        break;
                    }
                }

                while (!list.isEmpty() && list.get(list.size() - 1) == null) {
                    list.remove(list.size() - 1);
                }

                return list.toArray(new Integer[0]);
            }
        }
    }
}
