import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> list = new LinkedList<>();

        int curLevel = 1;
        List<Integer> values = new LinkedList<>();

        Map<TreeNode, Integer> map = new HashMap<>(); // 存储每个结点的层次
        map.put(root, 1);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                queue.offer(node.left);

                map.put(node.left, map.get(node) + 1);
            }

            if (node.right != null) {
                queue.offer(node.right);

                map.put(node.right, map.get(node) + 1);
            }

            if (map.get(node) == curLevel) {
                values.add(node.val);
            } else {
                list.add(values);

                values = new ArrayList<>();
                values.add(node.val);
            }

            curLevel = map.get(node);
        }

        list.add(values);

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
        public static void printList(List<Object> list) {
            System.out.println(java.util.Arrays.toString(list.toArray(new Object[0])));
        }

        public static void printLists1(List<List<Integer>> lists) {
            Object[] arrays = new Object[lists.size()];
            for (int i = 0; i < lists.size(); i++) {

                List<Integer> list = lists.get(i);
                arrays[i] = list.toArray(new Object[0]);

            }

            System.out.println(java.util.Arrays.deepToString(arrays));
        }

        public static void printLists2(List<List<Double>> lists) {
            Object[] arrays = new Object[lists.size()];
            for (int i = 0; i < lists.size(); i++) {

                List<Double> list = lists.get(i);
                arrays[i] = list.toArray(new Object[0]);

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
        Integer[] nums = { 3, 9, 20, null, null, 15, 7 };

        TreeNode tree = TreeNode.genTree(nums);
        List<List<Integer>> result = new Solution().levelOrder(tree);
        ListUtils.printLists1(result);
    }

    public static void testCase2() {
        Integer[] nums = { 1 };

        TreeNode tree = TreeNode.genTree(nums);
        List<List<Integer>> result = new Solution().levelOrder(tree);
        ListUtils.printLists1(result);
    }

    public static void testCase3() {
        Integer[] nums = {};

        TreeNode tree = TreeNode.genTree(nums);
        List<List<Integer>> result = new Solution().levelOrder(tree);
        ListUtils.printLists1(result);
    }
}