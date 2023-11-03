import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        list.add(root.val);

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

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        Integer[] nums = { 1, null, 2, 3 };

        TreeNode tree = TreeNode.genTree(nums);
        List<Integer> result = new Solution().postorderTraversal(tree);
        ListUtils.printList(result);
    }

    public static void testCase2() {
        Integer[] nums = {};

        TreeNode tree = TreeNode.genTree(nums);
        List<Integer> result = new Solution().postorderTraversal(tree);
        ListUtils.printList(result);
    }

    public static void testCase3() {
        Integer[] nums = { 1 };

        TreeNode tree = TreeNode.genTree(nums);
        List<Integer> result = new Solution().postorderTraversal(tree);
        ListUtils.printList(result);
    }
}