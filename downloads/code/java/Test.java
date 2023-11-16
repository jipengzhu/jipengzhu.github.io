public class Test {

    public static void main(String[] args) {

        transformArrayLiteral();

        // testStringUtils();

        // testToString();
    }

    private static void transformArrayLiteral() {
        String s = "[[\"x1\",\"x5\"],[\"x5\",\"x2\"],[\"x2\",\"x4\"],[\"x2\",\"x2\"],[\"x2\",\"x9\"],[\"x9\",\"x9\"]]";
        boolean isChar = false;

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

    private static void testStringUtils() {
        System.out.println("");

        Integer[] a11 = { 1, 2, 3 };
        System.out.println(TestConverter.toString(a11));

        Double[] a12 = { 7d, 8d, 9d };
        System.out.println(TestConverter.toString(a12));

        String[] a13 = { "a", "b", "c" };
        System.out.println(TestConverter.toString(a13));

        int[] a2 = { 1, 2, 3 };
        System.out.println(TestConverter.toString(a2));

        double[] a3 = { 7d, 8d, 9d };
        System.out.println(TestConverter.toString(a3));

        System.out.println("");

        java.util.List<java.util.List<Integer>> lists1 = new java.util.LinkedList<>();
        java.util.List<Integer> list1 = new java.util.LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        lists1.add(list1);

        java.util.List<java.util.List<Double>> lists2 = new java.util.LinkedList<>();
        java.util.List<Double> list2 = new java.util.LinkedList<>();
        list2.add(7d);
        list2.add(8d);
        list2.add(9d);
        lists2.add(list2);

        java.util.List<java.util.List<String>> lists3 = new java.util.LinkedList<>();
        java.util.List<String> list3 = new java.util.LinkedList<>();
        list3.add("a");
        list3.add("b");
        list3.add("c");
        lists3.add(list3);

        System.out.println(TestConverter.toString(list1));
        System.out.println(TestConverter.toString(list2));
        System.out.println(TestConverter.toString(list3));

        System.out.println(TestConverter.toString(lists1));
        System.out.println(TestConverter.toString(lists2));
        System.out.println(TestConverter.toString(lists3));

        // System.out.println(TestConverter.toLines(list1));
        // System.out.println(TestConverter.toLines(list2));
        // System.out.println(TestConverter.toLines(list3));
    }

    public static class ArrayUtils {
        public static void printArray(int[] array) {
            System.out.println(java.util.Arrays.toString(array));
        }

        public static void printArray(int[] array, int len) {
            System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
        }

        public static void printArrays(int[][] arrays) {
            System.out.println(java.util.Arrays.deepToString(arrays));
        }

        public static void printArray(Integer[] array) {
            System.out.println(java.util.Arrays.toString(array));
        }

        public static void printArray(Integer[] array, int len) {
            System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
        }

        public static void printArrays(Integer[][] arrays) {
            System.out.println(java.util.Arrays.deepToString(arrays));
        }

        public static void printArray(String[] array) {
            System.out.println(java.util.Arrays.toString(array));
        }

        public static void printArray(String[] array, int len) {
            System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
        }

        public static void printArrays(String[][] arrays) {
            System.out.println(java.util.Arrays.deepToString(arrays));
        }
    }

    public static class ListUtils {
        public static void printList(java.util.List<? extends Object> list) {
            System.out.println(java.util.Arrays.toString(list.toArray(new Object[0])));
        }

        public static void printLines(java.util.List<? extends Object> list) {
            if (list == null) {
                return;
            }

            for (Object o : list) {
                System.out.println(o);
            }
            System.out.println();
        }

        public static void printLists1(java.util.List<java.util.List<Integer>> lists) {
            Object[] arrays = new Object[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = lists.get(i).toArray(new Object[0]);
            }

            System.out.println(java.util.Arrays.deepToString(arrays));
        }

        public static void printLists2(java.util.List<java.util.List<Double>> lists) {
            Object[] arrays = new Object[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = lists.get(i).toArray(new Object[0]);
            }

            System.out.println(java.util.Arrays.deepToString(arrays));
        }

        public static void printLists3(java.util.List<java.util.List<String>> lists) {
            Object[] arrays = new Object[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = lists.get(i).toArray(new Object[0]);
            }

            System.out.println(java.util.Arrays.deepToString(arrays));
        }
    }

    public static class TestConverter {
        public static String toString(Object[] array) {
            return java.util.Arrays.toString(array);
        }

        public static String toString(Object[][] arrays) {
            return java.util.Arrays.deepToString(arrays);
        }

        public static String toString(int[] array) {
            return java.util.Arrays.toString(array);
        }

        public static String toString(int[][] arrays) {
            return java.util.Arrays.deepToString(arrays);
        }

        public static String toString(double[] array) {
            return java.util.Arrays.toString(array);
        }

        public static String toString(double[][] arrays) {
            return java.util.Arrays.deepToString(arrays);
        }

        public static String toString(char[] array) {
            return java.util.Arrays.toString(array);
        }

        public static String toString(char[][] arrays) {
            return java.util.Arrays.deepToString(arrays);
        }

        public static String toString(java.util.List<? extends Object> list) {
            return list.toString();
        }

        public static String toLines(java.util.List<? extends Object> list) {
            if (list == null) {
                return "\n";
            }

            java.util.List<String> strings = new java.util.ArrayList<>(list.size());
            for (Object object : list) {
                strings.add(object.toString());
            }

            return String.join("\n", strings) + "\n";
        }
    }

    public static class TestUtils {
        public static boolean check(Object result, Object expect) {
            if (result instanceof String && expect instanceof String) {
                result = normalizeString((String) result);
                expect = normalizeString((String) expect);
            }

            boolean ok = java.util.Objects.equals(result, expect);
            printCheck(result, expect, ok);
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

        private static void printCheck(Object result, Object expect, boolean ok) {
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

                Integer leftVal = i < nums.length ? nums[i++] : null;
                if (leftVal != null) {
                    TreeNode leftNode = new TreeNode(leftVal);
                    node.left = leftNode;

                    queue.offer(leftNode);
                }

                Integer rightVal = i < nums.length ? nums[i++] : null;
                if (rightVal != null) {
                    TreeNode rightNode = new TreeNode(rightVal);
                    node.right = rightNode;

                    queue.offer(rightNode);
                }
            }

            return root;
        }

        public static Integer[] toArray(TreeNode root) {
            if (root == null) {
                return new Integer[0];
            }

            java.util.LinkedList<Integer> list = new java.util.LinkedList<>();
            list.add(root.val);

            java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;

                list.add(left != null ? left.val : null);
                list.add(right != null ? right.val : null);

                if (left != null) {
                    queue.offer(left);
                }

                if (right != null) {
                    queue.offer(right);
                }
            }

            while (!list.isEmpty() && list.peekLast() == null) {
                list.pollLast();
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
