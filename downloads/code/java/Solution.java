import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    private List<String> list = new LinkedList<>();
    private Map<TreeNode, TreeNode> map = new HashMap<>(); // 存储每个结点到双亲结点的映射

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return list;
        }

        if (root.left == null && root.right == null) {
            List<String> tmp = new LinkedList<>();

            TreeNode p = root;
            while (p != null) {
                tmp.add(0, "" + p.val);

                p = map.get(p);
            }

            list.add(String.join("->", tmp));
        }

        map.put(root.left, root);
        map.put(root.right, root);

        binaryTreePaths(root.left);
        binaryTreePaths(root.right);

        return list;
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

    public static class StringUtils {
        public static String toString(Object[] array) {
            return java.util.Arrays.toString(array);
        }

        public static String toString(Object[] array, int len) {
            return toString(java.util.Arrays.copyOf(array, len));
        }

        public static String toString(Object[][] arrays) {
            return java.util.Arrays.deepToString(arrays);
        }

        public static String toString(int[] array) {
            return java.util.Arrays.toString(array);
        }

        public static String toString(int[] array, int len) {
            return toString(java.util.Arrays.copyOf(array, len));
        }

        public static String toString(int[][] arrays) {
            return java.util.Arrays.deepToString(arrays);
        }

        public static String toString(double[] array) {
            return java.util.Arrays.toString(array);
        }

        public static String toString(double[] array, int len) {
            return toString(java.util.Arrays.copyOf(array, len));
        }

        public static String toString(double[][] arrays) {
            return java.util.Arrays.deepToString(arrays);
        }

        public static String toString(java.util.List<? extends Object> list) {
            return toString(list.toArray(new Object[0]));
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

        public static String toStringDeep(java.util.List<? extends java.util.List<?>> lists) {
            Object[] arrays = new Object[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = lists.get(i).toArray(new Object[0]);
            }

            return java.util.Arrays.deepToString(arrays);
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
            if (s.startsWith("[") && s.endsWith("]")) {
                s = s.replace(" ", "")
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

    public static void main(String[] args) {
        TestUtils.runTestCases(Solution.class);
    }

    public static boolean testCase1() {
        Integer[] nums = { 1, 2, 3, null, 5 };

        TreeNode tree = TreeNode.genTree(nums);
        List<String> result = new Solution().binaryTreePaths(tree);
        String expect = "[\"1->2->5\",\"1->3\"]";

        return TestUtils.check(StringUtils.toString(result), expect);
    }

    public static boolean testCase2() {
        Integer[] nums = { 1 };

        TreeNode tree = TreeNode.genTree(nums);
        List<String> result = new Solution().binaryTreePaths(tree);
        String expect = "[\"1\"]";

        return TestUtils.check(StringUtils.toString(result), expect);
    }
}