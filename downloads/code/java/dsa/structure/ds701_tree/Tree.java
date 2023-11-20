package dsa.structure.ds701_tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Tree {
    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode buildTreeByLevelOrder(Integer[] array) {
        if (array.length == 0) {
            return null;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        TreeNode root = new TreeNode(array[0]);
        queue.offer(root);

        int i = 1;
        while (i < array.length) {
            TreeNode node = queue.poll();

            if (node != null) {
                if (i < array.length) {
                    Integer v = array[i++];

                    node.left = v == null ? null : new TreeNode(v);

                    queue.offer(node.left);
                }

                if (i < array.length) {
                    Integer v = array[i++];

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

    public static TreeNode buildTreeByPreOrderAndInOrder(Integer[] preorder, Integer[] inorder) {
        // 前序遍历[根结点，左子树的前序遍历，右子树的前序遍历]
        // 中序遍历[左子树的的中序遍历，根结点，右子树的的中序遍历]
        // 先找到根结点（前序遍历的第一个）在中序遍历中的位置
        // 然后利用根结点位置在中序遍历中进行分割求出左子树的中序遍历和右子树的中序遍历
        // 然后利用左子树和右子树的长度在前序遍历求出左子树的前序遍历和右子树的前序遍历
        // 然后再继续生成左子树和右子树

        // 存储中序数组中结点到下标的映射，以便快速查找
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeByPreOrderAndInOrder(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private static TreeNode buildTreeByPreOrderAndInOrder(Integer[] preorder, int preX, int preY,
            Integer[] inorder, int inX, int inY, Map<Integer, Integer> map) {
        if (preX > preY) {
            return null;
        }

        int rootVal = preorder[preX];
        TreeNode rootNode = new TreeNode(rootVal);

        int rootIndex = map.get(rootVal);

        int leftInX = inX;
        int leftInY = rootIndex - 1;
        int leftCount = leftInY - leftInX + 1;
        int rightInX = rootIndex + 1;
        int rightInY = inY;
        int rightCount = rightInY - rightInX + 1;

        if (leftCount > 0) {
            int leftPreX = preX + 1;
            int leftPreY = leftPreX + leftCount - 1;

            rootNode.left = buildTreeByPreOrderAndInOrder(preorder, leftPreX, leftPreY, inorder, leftInX, leftInY, map);
        }

        if (rightCount > 0) {
            int rightPreY = preY;
            int rightPreX = rightPreY - rightCount + 1;

            rootNode.right = buildTreeByPreOrderAndInOrder(preorder, rightPreX, rightPreY, inorder, rightInX, rightInY,
                    map);
        }

        return rootNode;
    }

    public static TreeNode buildTreeByInOrderAndPostOrder(Integer[] inorder, Integer[] postorder) {
        // 后序遍历[左子树的前序遍历，右子树的前序遍历，根结点]
        // 中序遍历[左子树的的中序遍历，根结点，右子树的的中序遍历]
        // 先找到根结点（前序遍历的第一个）在中序遍历中的位置
        // 然后利用根结点位置在中序遍历中进行分割求出左子树的中序遍历和右子树的中序遍历
        // 然后利用左子树和右子树的长度在前序遍历求出左子树的前序遍历和右子树的前序遍历
        // 然后再继续生成左子树和右子树

        // 存储中序数组中结点到下标的映射，以便快速查找
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeByInOrderAndPostOrder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    private static TreeNode buildTreeByInOrderAndPostOrder(Integer[] inorder, int inX, int inY,
            Integer[] postorder, int postX, int postY, Map<Integer, Integer> map) {
        if (postX > postY) {
            return null;
        }

        int rootVal = postorder[postY];
        TreeNode rootNode = new TreeNode(rootVal);

        int rootIndex = map.get(rootVal);

        int leftInX = inX;
        int leftInY = rootIndex - 1;
        int leftCount = leftInY - leftInX + 1;
        int rightInX = rootIndex + 1;
        int rightInY = inY;
        int rightCount = rightInY - rightInX + 1;

        if (leftCount > 0) {
            int leftPostX = postX;
            int leftPostY = leftPostX + leftCount - 1;

            rootNode.left = buildTreeByInOrderAndPostOrder(inorder, leftInX, leftInY, postorder, leftPostX, leftPostY,
                    map);
        }

        if (rightCount > 0) {
            int rightPostY = postY - 1;
            int rightPostX = rightPostY - rightCount + 1;

            rootNode.right = buildTreeByInOrderAndPostOrder(inorder, rightInX, rightInY, postorder, rightPostX,
                    rightPostY, map);
        }

        return rootNode;
    }

    public static Integer[] visitTreeOfLevelOrderWithEmpty(TreeNode root) {
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

    public static List<List<Integer>> visitTreeOfLevelOrderByLoop(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            List<Integer> values = new LinkedList<>();
            for (TreeNode node : nodes) {
                values.add(node.val);
            }
            lists.add(values);

            nodes = visitTreeOfLevelOrderByLoop(nodes);
        }

        return lists;
    }

    private static List<TreeNode> visitTreeOfLevelOrderByLoop(List<TreeNode> nodes) {
        List<TreeNode> newNodes = new LinkedList<>();

        for (TreeNode node : nodes) {
            if (node.left != null) {
                newNodes.add(node.left);
            }

            if (node.right != null) {
                newNodes.add(node.right);
            }
        }

        return newNodes;
    }

    public static List<List<Integer>> visitTreeOfLevelOrderByRecursive(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        visitTreeOfLevelOrderByRecursive(nodes, lists);

        return lists;
    }

    private static void visitTreeOfLevelOrderByRecursive(List<TreeNode> nodes, List<List<Integer>> lists) {
        if (nodes.isEmpty()) {
            return;
        }

        List<Integer> values = new LinkedList<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }
        lists.add(values);

        List<TreeNode> newNodes = new LinkedList<>();
        for (TreeNode node : nodes) {
            if (node.left != null) {
                newNodes.add(node.left);
            }

            if (node.right != null) {
                newNodes.add(node.right);
            }
        }

        visitTreeOfLevelOrderByRecursive(newNodes, lists);
    }

    public static List<Integer> visitTreeOfPreOrderByRecursive(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        list.add(root.val);
        list.addAll(visitTreeOfPreOrderByRecursive(root.left));
        list.addAll(visitTreeOfPreOrderByRecursive(root.right));

        return list;
    }

    public static List<Integer> visitTreeOfInOrderByRecursive(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        list.addAll(visitTreeOfInOrderByRecursive(root.left));
        list.add(root.val);
        list.addAll(visitTreeOfInOrderByRecursive(root.right));

        return list;
    }

    public static List<Integer> visitTreeOfPostOrderByRecursive(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        list.addAll(visitTreeOfPostOrderByRecursive(root.left));
        list.addAll(visitTreeOfPostOrderByRecursive(root.right));
        list.add(root.val);

        return list;
    }

    public static List<List<Integer>> visitTreeOfLevelOrderByQueue(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> lists = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> values = new LinkedList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                values.add(node.val);
            }

            lists.add(values);
        }

        return lists;
    }

    public static List<Integer> visitTreeOfPreOrderByStack(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                list.add(p.val);

                stack.push(p);

                p = p.left;
            } else {
                p = stack.pop();

                p = p.right;
            }
        }

        return list;
    }

    public static List<Integer> visitTreeOfInOrderByStack(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {

                stack.push(p);

                p = p.left;
            } else {
                p = stack.pop();

                list.add(p.val);

                p = p.right;
            }
        }

        return list;
    }

    public static List<Integer> visitTreeOfPostOrderByStack(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        Map<TreeNode, Integer> map = new HashMap<>();

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                Integer flag = map.getOrDefault(p, 0);

                if (flag == 0) {
                    map.put(p, ++flag);

                    stack.push(p);

                    p = p.left;
                } else if (flag == 1) {
                    map.put(p, ++flag);

                    stack.push(p);

                    p = p.right;
                } else {
                    list.add(p.val);

                    p = stack.isEmpty() ? null : stack.pop();
                }
            } else {
                p = stack.pop();
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeTest.test();
    }

    public static class TreeTest {
        private static Integer[] listToArray(List<Integer> list) {
            return list.toArray(new Integer[0]);
        }

        private static Integer[] listsToArray(List<List<Integer>> lists) {
            List<Integer> list = new LinkedList<>();
            for (List<Integer> values : lists) {
                list.addAll(values);
            }

            return list.toArray(new Integer[0]);
        }

        private static Integer[] levelarray = { 1, 3, 5, 7, null, 9, 11, 13, 15, null, null, null, 17 };
        private static Integer[] levelorder = { 1, 3, 5, 7, 9, 11, 13, 15, 17 };
        private static Integer[] preorder = { 1, 3, 7, 13, 15, 5, 9, 17, 11 };
        private static Integer[] inorder = { 13, 7, 15, 3, 1, 9, 17, 5, 11 };
        private static Integer[] postorder = { 13, 15, 7, 3, 17, 9, 11, 5, 1 };

        private static void testTree(TreeNode tree) {
            Integer[] result = null;
            String tip = null;

            result = Tree.visitTreeOfLevelOrderWithEmpty(tree);
            tip = "层次遍历+空结点";
            check(tip, result, levelarray);

            result = listsToArray(Tree.visitTreeOfLevelOrderByLoop(tree));
            tip = "层次遍历+循环版";
            check(tip, result, levelorder);

            result = listsToArray(Tree.visitTreeOfLevelOrderByRecursive(tree));
            tip = "层次遍历+递归版";
            check(tip, result, levelorder);

            result = listsToArray(Tree.visitTreeOfLevelOrderByQueue(tree));
            tip = "层次遍历+队列版";
            check(tip, result, levelorder);

            result = listToArray(Tree.visitTreeOfPreOrderByRecursive(tree));
            tip = "先序遍历+递归版";
            check(tip, result, preorder);

            result = listToArray(Tree.visitTreeOfPreOrderByStack(tree));
            tip = "先序遍历+栈列版";
            check(tip, result, preorder);

            result = listToArray(Tree.visitTreeOfInOrderByRecursive(tree));
            tip = "中序遍历+递归版";
            check(tip, result, inorder);

            result = listToArray(Tree.visitTreeOfInOrderByStack(tree));
            tip = "中序遍历+栈列版";
            check(tip, result, inorder);

            result = listToArray(Tree.visitTreeOfPostOrderByRecursive(tree));
            tip = "后序遍历+递归版";
            check(tip, result, postorder);

            result = listToArray(Tree.visitTreeOfPostOrderByStack(tree));
            tip = "后序遍历+栈列版";
            check(tip, result, postorder);
        }

        public static void test() {
            test1();
            test2();
            test3();
        }

        public static void test1() {
            TreeNode tree = Tree.buildTreeByLevelOrder(levelarray);
            testTree(tree);
        }

        public static void test2() {
            TreeNode tree = Tree.buildTreeByPreOrderAndInOrder(preorder, inorder);
            testTree(tree);
        }

        public static void test3() {
            TreeNode tree = Tree.buildTreeByInOrderAndPostOrder(inorder, postorder);
            testTree(tree);
        }

        private static void check(String tip, Integer[] result, Integer[] expect) {
            System.out.println();
            System.out.println("--->");

            System.out.println(tip);
            System.out.println("result: " + Arrays.toString(result));
            System.out.println("expect: " + Arrays.toString(expect));

            boolean ok = Arrays.equals(result, expect);

            System.out.println();
            if (ok) {
                System.out.println("比较结果为: 正确(right)");
            } else {
                System.out.println("比较结果为: 错误(error)");
            }

            System.out.println("<---");
            System.out.println();

            if (!ok) {
                throw new RuntimeException("ERROR: 树的测试失败");
            }
        }
    }
}