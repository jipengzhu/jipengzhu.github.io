import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int k = 0;
        TreeNode p = root;
        while (p != null) {
            k++;

            p = p.left;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        p = root;
        while (p != null) {
            stack.push(p);

            p = p.right;
        }

        int totalMissedCount = countMissedNodes(stack.peek().right, k, stack.size() + 1);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int missedCount = countMissedNodes(node.left, k, stack.size() + 2);
            if (missedCount == 0) {
                break;
            } else {
                totalMissedCount = totalMissedCount + missedCount;
            }
        }

        return (int) (Math.pow(2, k) - 1 - totalMissedCount);
    }

    private int countMissedNodes(TreeNode node, int tk, int nk) {
        if (nk == tk) {
            if (node == null) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (node == null) {
                return 0;
            } else {
                return countMissedNodes(node.left, tk, nk + 1) + countMissedNodes(node.right, tk, nk + 1);
            }
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

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        Integer[] nums = { 1, 2, 3, 4, 5, 6 };

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().countNodes(tree);
        System.out.println(result);
    }

    public static void testCase2() {
        Integer[] nums = {};

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().countNodes(tree);
        System.out.println(result);
    }

    public static void testCase3() {
        Integer[] nums = { 1 };

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().countNodes(tree);
        System.out.println(result);
    }
}