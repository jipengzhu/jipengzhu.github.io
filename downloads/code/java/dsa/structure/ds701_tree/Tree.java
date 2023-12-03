package dsa.structure.ds701_tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

            if (i < array.length) {
                Integer v = array[i++];

                node.left = v == null ? null : new TreeNode(v);

                if (node.left != null) {
                    queue.offer(node.left);
                }
            }

            if (i < array.length) {
                Integer v = array[i++];

                node.right = v == null ? null : new TreeNode(v);

                if (node.right != null) {
                    queue.offer(node.right);
                }
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

                    queue.offer(node.left);
                    queue.offer(node.right);

                    if (!hasValidNode) {
                        hasValidNode = node.left != null || node.right != null;
                    }
                } else {
                    list.add(null);
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
            List<TreeNode> newNodes = new LinkedList<>();
            for (TreeNode node : nodes) {
                values.add(node.val);

                if (node.left != null) {
                    newNodes.add(node.left);
                }

                if (node.right != null) {
                    newNodes.add(node.right);
                }
            }

            lists.add(values);

            nodes = newNodes;
        }

        return lists;
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
        List<TreeNode> newNodes = new LinkedList<>();
        for (TreeNode node : nodes) {
            values.add(node.val);

            if (node.left != null) {
                newNodes.add(node.left);
            }

            if (node.right != null) {
                newNodes.add(node.right);
            }
        }

        lists.add(values);

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

    public static List<Integer> visitTreeOfLevelOrderByQueue1(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            list.add(node.val);

        }

        return list;
    }

    public static List<List<Integer>> visitTreeOfLevelOrderByQueue2(TreeNode root) {
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
        private static Integer[] preorder = { 1, 3, 7, 13, 15, 5, 9, 11, 17 };
        private static Integer[] inorder = { 13, 7, 15, 3, 1, 9, 5, 11, 17 };
        private static Integer[] postorder = { 13, 15, 7, 3, 9, 17, 11, 5, 1 };

        private static void testTree(TreeNode tree) {
            Integer[] result = null;
            String tip = null;

            result = Tree.visitTreeOfLevelOrderWithEmpty(tree);
            tip = "树的层次遍历+空结点";
            TestUtils.check(tip, result, levelarray);

            result = listsToArray(Tree.visitTreeOfLevelOrderByLoop(tree));
            tip = "树的层次遍历+循环版";
            TestUtils.check(tip, result, levelorder);

            result = listsToArray(Tree.visitTreeOfLevelOrderByRecursive(tree));
            tip = "树的层次遍历+递归版";
            TestUtils.check(tip, result, levelorder);

            result = listToArray(Tree.visitTreeOfLevelOrderByQueue1(tree));
            tip = "树的层次遍历+队列版1";
            TestUtils.check(tip, result, levelorder);

            result = listsToArray(Tree.visitTreeOfLevelOrderByQueue2(tree));
            tip = "树的层次遍历+队列版2";
            TestUtils.check(tip, result, levelorder);

            result = listToArray(Tree.visitTreeOfPreOrderByRecursive(tree));
            tip = "树的先序遍历+递归版";
            TestUtils.check(tip, result, preorder);

            result = listToArray(Tree.visitTreeOfPreOrderByStack(tree));
            tip = "树的先序遍历+栈列版";
            TestUtils.check(tip, result, preorder);

            result = listToArray(Tree.visitTreeOfInOrderByRecursive(tree));
            tip = "树的中序遍历+递归版";
            TestUtils.check(tip, result, inorder);

            result = listToArray(Tree.visitTreeOfInOrderByStack(tree));
            tip = "树的中序遍历+栈列版";
            TestUtils.check(tip, result, inorder);

            result = listToArray(Tree.visitTreeOfPostOrderByRecursive(tree));
            tip = "树的后序遍历+递归版";
            TestUtils.check(tip, result, postorder);

            result = listToArray(Tree.visitTreeOfPostOrderByStack(tree));
            tip = "树的后序遍历+栈列版";
            TestUtils.check(tip, result, postorder);
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
    }

    public static class TestUtils {
        public static int[] random(int n) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * 1000) + 1;
            }

            return array;
        }

        public static void check(String name, int[] result, int[] expect) {
            String resultString = Arrays.toString(result);
            String expectString = Arrays.toString(expect);
            boolean ok = Arrays.equals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static void check(String name, Object[] result, Object[] expect) {
            String resultString = Arrays.deepToString(result);
            String expectString = Arrays.deepToString(expect);
            boolean ok = Arrays.deepEquals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static void check(String name, Object result, Object expect) {
            String resultString = result.toString();
            String expectString = expect.toString();
            boolean ok = Objects.equals(result, expect);

            printCheck(name, resultString, expectString, ok);
        }

        public static <T> void check(String name, java.util.List<T> result, T[] expect, Class<T> clazz) {
            check(name, toArray(result, clazz), expect);
        }

        public static <T> void check(String name, java.util.List<java.util.List<T>> result, T[][] expect,
                Class<T> clazz) {
            check(name, toArrays(result, clazz), expect);
        }

        @SuppressWarnings("unchecked")
        public static <T> T[] genArray(Class<T> clazz, int length) {
            return (T[]) java.lang.reflect.Array.newInstance(clazz, length);
        }

        @SuppressWarnings("unchecked")
        public static <T> T[][] genArrays(Class<T> clazz, int rows, int cols) {
            T[] array = genArray(clazz, 0);

            T[][] arrays = (T[][]) genArray(array.getClass(), rows);
            for (int i = 0; i < arrays.length; i++) {
                arrays[i] = genArray(clazz, cols);
            }

            return arrays;
        }

        public static <T> T[] toArray(java.util.List<T> list, Class<T> clazz) {
            if (list == null || list.isEmpty()) {
                return genArray(clazz, 0);
            }

            T[] array = genArray(clazz, list.size());
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

            return array;
        }

        public static <T> T[][] toArrays(java.util.List<java.util.List<T>> lists, Class<T> clazz) {
            if (lists == null || lists.isEmpty()) {
                return genArrays(clazz, 0, 0);
            }

            T[][] arrays = genArrays(clazz, lists.size(), 0);
            for (int i = 0; i < lists.size(); i++) {
                arrays[i] = toArray(lists.get(i), clazz);
            }

            return arrays;
        }

        public static void printCheck(String name, String result, String expect, boolean ok) {
            System.out.println();
            System.out.println("--->");

            System.out.println("result: " + result);
            System.out.println("expect: " + expect);

            System.out.println();
            if (ok) {
                System.out.println("比较结果为: 正确(right)");
            } else {
                System.out.println("比较结果为: 错误(error)");
            }

            System.out.println("<---");
            System.out.println();

            if (!ok) {
                throw new RuntimeException(String.format("ERROR: [%s]的测试失败", name));
            }
        }
    }
}