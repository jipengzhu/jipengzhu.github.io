public class Solution {
    private int totalSum = 0;

    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);

        return totalSum;
    }

    private void sumNumbers(TreeNode node, int parentSum) {
        if (node == null) {
            return;
        }

        node.val = parentSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            totalSum = totalSum + node.val;
        }

        sumNumbers(node.left, node.val);
        sumNumbers(node.right, node.val);
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
        Integer[] nums = { 1, 2, 3 };

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().sumNumbers(tree);
        System.out.println(result);
    }

    public static void testCase2() {
        Integer[] nums = { 4, 9, 0, 5, 1 };

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().sumNumbers(tree);
        System.out.println(result);
    }

    public static void testCase3() {
        Integer[] nums = { 1, 0 };

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().sumNumbers(tree);
        System.out.println(result);
    }
}