public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = maxDepth(root.left);
        int r = maxDepth(root.right);

        int k = l;
        if (r > l) {
            k = r;
        }

        return k + 1;
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

                Integer leftVal = nums[i++];
                if (leftVal != null) {
                    TreeNode leftNode = new TreeNode(leftVal);
                    node.left = leftNode;

                    queue.offer(leftNode);
                }

                Integer rightVal = nums[i++];
                if (rightVal != null) {
                    TreeNode rightNode = new TreeNode(rightVal);
                    node.right = rightNode;

                    queue.offer(rightNode);
                }
            }

            return root;
        }
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
    }

    public static void testCase1() {
        Integer[] nums = { 3, 9, 20, null, null, 15, 7 };

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().maxDepth(tree);
        System.out.println(result);
    }

    public static void testCase2() {
        Integer[] nums = { 1, null, 2 };

        TreeNode tree = TreeNode.genTree(nums);
        int result = new Solution().maxDepth(tree);
        System.out.println(result);
    }
}