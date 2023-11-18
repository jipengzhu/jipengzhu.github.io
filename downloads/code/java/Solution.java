import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    private int count = 0;
    private Map<TreeNode, Map<TreeNode, String>> paths = new HashMap<>();
    private Map<TreeNode, TreeNode> map = new HashMap<>(); // 存储每个结点到双亲结点的映射

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return count;
        }

        if (root.left == null && root.right == null) {
            List<TreeNode> list = new LinkedList<>();

            TreeNode p = root;
            while (p != null) {
                list.add(0, p);

                p = map.get(p);
            }

            for (int i = 0; i < list.size(); i++) {
                int sum = 0;
                for (int j = i; j < list.size(); j++) {
                    sum = sum + list.get(j).val;
                    if (sum == targetSum) {
                        // List<String> tmp = new LinkedList<>();
                        // for (int k = i; k <= j; k++) {
                        // TreeNode node = list.get(k);

                        // tmp.add(node.val + "");
                        // // tmp.add(node.val + "@" + node);
                        // }

                        // String path = String.join("->", tmp);

                        // // System.out.println(list);
                        // // System.out.println(path);

                        TreeNode x = list.get(i);
                        TreeNode y = list.get(j);

                        Map<TreeNode, String> nodes = paths.computeIfAbsent(x, k -> new HashMap<>());
                        if (nodes.containsKey(y)) {
                            continue;
                        } else {
                            // paths.put(y, path);
                            nodes.put(y, null);
                            count++;
                        }
                    }
                }
            }
        }

        map.put(root.left, root);
        map.put(root.right, root);

        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return count;
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
        Integer[] nums = { 10, 5, -3, 3, 2, null, 11, 3, -2, null, 1 };
        int sum = 8;

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().pathSum(tree, sum);
        int expect = 3;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase2() {
        Integer[] nums = { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 };
        int sum = 22;

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().pathSum(tree, sum);
        int expect = 3;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase3() {
        Integer[] nums = { 0, 1, 1 };
        int sum = 1;

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().pathSum(tree, sum);
        int expect = 4;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase4() {
        Integer[] nums = { 0, 0, 0 };
        int sum = 0;

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().pathSum(tree, sum);
        int expect = 5;

        return TestUtils.check(result, expect);
    }

    public static boolean testCase5() {
        Integer[] nums = { 1000000000, 1000000000, null, 294967296, null, 1000000000, null, 1000000000, null,
                1000000000 };
        int sum = 0;

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().pathSum(tree, sum);
        int expect = 0;

        return TestUtils.check(result, expect);
    }
}