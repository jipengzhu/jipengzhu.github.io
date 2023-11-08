public class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                return true;
            }
        }

        return hasPathSum(root.left, targetSum - root.val) &&
                hasPathSum(root.right, targetSum - root.val);
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
        Integer[] nums = { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1 };
        int sum = 22;

        TreeNode tree = TreeNode.genTree(nums);
        boolean result = new Solution().hasPathSum(tree, sum);
        System.out.println(result);
    }

    public static void testCase2() {
        Integer[] nums = { 1, 2, 3 };
        int sum = 5;

        TreeNode tree = TreeNode.genTree(nums);
        boolean result = new Solution().hasPathSum(tree, sum);
        System.out.println(result);
    }

    public static void testCase3() {
        Integer[] nums = {};
        int sum = 0;

        TreeNode tree = TreeNode.genTree(nums);
        boolean result = new Solution().hasPathSum(tree, sum);
        System.out.println(result);
    }
}