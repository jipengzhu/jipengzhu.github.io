import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        transformArrayLiteral();

        // testTestUtils();
    }

    private static void transformArrayLiteral() {
        String s = "";
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
        String b71 = "[ [ '1', '2', '3' ], [ '7', '8', '9' ] ]";
        TestUtils.check(a71, b71);

        TestUtils.toLists(a71);

        Integer[] a91 = { 1, 2, 3 };
        Integer[] b91 = TestUtils.toArray(TestUtils.toList(a91), Integer.class);

        TestUtils.check(a91, b91);
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

        @SuppressWarnings("unchecked")
        public static <T> T[] toArray(java.util.List<T> list, Class<T> clazz) {
            T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, list.size());
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

            return array;
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

    static class ListNode {
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

    static public class TreeNode {
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

        public static TreeNode genTree(Integer[] nums) {
            if (nums.length == 0) {
                return null;
            }

            java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
            TreeNode root = new TreeNode(nums[0]);
            queue.offer(root);

            int i = 1;
            while (i < nums.length) {
                TreeNode node = queue.poll();

                if (node != null) {
                    if (i < nums.length) {
                        Integer v = nums[i++];

                        node.left = v == null ? null : new TreeNode(v);

                        queue.offer(node.left);
                    }

                    if (i < nums.length) {
                        Integer v = nums[i++];

                        node.right = v == null ? null : new TreeNode(v);

                        queue.offer(node.right);
                    }
                } else {
                    i = i + 2;

                    queue.offer(null);
                    queue.offer(null);
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
                    } else {
                        list.add(null);
                    }

                    if (node != null && node.left != null) {
                        queue.offer(node.left);

                        hasValidNode = true;
                    } else {
                        queue.offer(null);
                    }

                    if (node != null && node.right != null) {
                        queue.offer(node.right);

                        hasValidNode = true;
                    } else {
                        queue.offer(null);
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

    int result;
    int expect;

    // String sp1 =
    // "^\s{1,}print.*|System.out.println\((?!("|java.util|expect|result|o\)|s\))).+\)";
    // String sp2 = "ToArr.{1,5}\((?!(new|result|tree|ListNode|head))";
    // String sc1 = "(?<!out)\.print"
    // String sc2 = "\..*?ToArr.*\("

    // String tp1 = "new Solution\(\).*\)(?!;)";
    // String tp2 = "test(?!Case).*\d\("
    // String tc = "testCase\d\("
}
