import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        transformArrayLiteral();

        // testToString();
    }

    private static void transformArrayLiteral() {
        String s = "[[\"X\",\"X\",\"X\",\"X\",\"O\",\"O\",\"X\",\"X\",\"O\"],[\"O\",\"O\",\"O\",\"O\",\"X\",\"X\",\"O\",\"O\",\"X\"],[\"X\",\"O\",\"X\",\"O\",\"O\",\"X\",\"X\",\"O\",\"X\"],[\"O\",\"O\",\"X\",\"X\",\"X\",\"O\",\"O\",\"O\",\"O\"],[\"X\",\"O\",\"O\",\"X\",\"X\",\"X\",\"X\",\"X\",\"O\"],[\"O\",\"O\",\"X\",\"O\",\"X\",\"O\",\"X\",\"O\",\"X\"],[\"O\",\"O\",\"O\",\"X\",\"X\",\"O\",\"X\",\"O\",\"X\"],[\"O\",\"O\",\"O\",\"X\",\"O\",\"O\",\"O\",\"X\",\"O\"],[\"O\",\"X\",\"O\",\"O\",\"O\",\"X\",\"O\",\"X\",\"O\"]]";
        // boolean isChar = false;
        boolean isChar = true;

        jsArrayLiteral2JavaArrayLiteral(s, true, isChar);
        jsArrayLiteral2JavaArrayLiteral(s, false, isChar);
    }

    private static void jsArrayLiteral2JavaArrayLiteral(String s, boolean pretty, boolean isChar) {
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

        System.out.println();
        System.out.println(s);

        // if (pretty) {
        // File file = new File("tmp_pretty_array.txt");
        // try (FileOutputStream fo = new FileOutputStream(file)) {
        // fo.write(s.getBytes());
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
    }

    private static void testToString() {
        int[] a = new int[] { 1 };
        int[][] b = new int[][] { { 1 }, { 2 } };
        Object[] c = b;
        int[][] d = new int[][] { { 1 }, { 2 } };
        Object[] e = d;
        System.out.println(a.getClass().isArray());
        System.out.println(b.getClass().isArray());
        System.out.println(b instanceof Object[]);
        System.out.println(java.util.Arrays.deepToString(c));
        System.out.println(java.util.Arrays.equals(c, e));
        System.out.println(java.util.Arrays.deepEquals(c, e));
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
