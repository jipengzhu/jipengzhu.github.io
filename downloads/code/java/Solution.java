public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;

        int x = p == null ? 0 : p.val;
        int y = q == null ? 0 : q.val;
        int value = x + y + carry;
        carry = value > 9 ? 1 : 0;
        value = value > 9 ? value - 10 : value;

        ListNode head = new ListNode(value);
        p = p == null ? p : p.next;
        q = q == null ? q : q.next;

        ListNode r = head;
        while (p != null || q != null) {
            x = p == null ? 0 : p.val;
            y = q == null ? 0 : q.val;
            value = x + y + carry;
            carry = value > 9 ? 1 : 0;
            value = value > 9 ? value - 10 : value;

            ListNode node = new ListNode(value);
            r.next = node;
            r = node;

            p = p == null ? p : p.next;
            q = q == null ? q : q.next;
        }

        if (carry > 0) {
            value = carry;

            ListNode node = new ListNode(value);
            r.next = node;
            r = node;
        }

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public static ListNode genLinkedList(int[] nums, boolean withHead, int loopPos) {
            int val = withHead ? Integer.MIN_VALUE : nums[0];
            ListNode head = new ListNode(val);

            ListNode loop = withHead ? null : loopPos == 0 ? head : null;

            int b = withHead ? 0 : 1;
            ListNode p = head;
            for (int i = b; i < nums.length; i++) {
                ListNode node = new ListNode(nums[i]);
                p.next = node;
                p = node;

                if (loopPos == i) {
                    loop = node;
                }
            }

            if (loop != null) {
                p.next = loop;
            }

            return head;
        }

        public static ListNode genLinkedListWithHeadNode(int[] nums) {
            return genLinkedList(nums, true, -1);
        }

        public static ListNode genLinkedListWithoutHeadNode(int[] nums) {
            return genLinkedList(nums, false, -1);
        }

        public static ListNode genLoopedLinkedListWithHeadNode(int[] nums, int pos) {
            return genLinkedList(nums, true, pos);
        }

        public static ListNode genLoopedLinkedListWithoutHeadNode(int[] nums, int pos) {
            return genLinkedList(nums, false, pos);
        }

        public static int getCount(ListNode head, boolean withHead) {
            ListNode p = withHead ? head.next : head;

            int c = 0;
            while (p != null) {
                c++;
                p = p.next;
            }

            return c;
        }

        public static int[] toArray(ListNode head, boolean withHead) {
            int c = getCount(head, withHead);
            int[] nums = new int[c];

            ListNode p = withHead ? head.next : head;

            int k = 0;
            while (p != null) {
                nums[k] = p.val;

                k++;
                p = p.next;
            }

            return nums;
        }

        public static int[] toArrayWithHeadNode(ListNode head) {
            return toArray(head, true);
        }

        public static int[] toArrayWithoutHeadNode(ListNode head) {
            return toArray(head, false);
        }
    }

    public static class ArrayUtils {
        public static void printArray(int[] array) {
            System.out.println(java.util.Arrays.toString(array));
        }

        public static void printArray(int[] array, int len) {
            System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
        }

        public static void printArrays(int[][] arrays) {
            System.out.println(java.util.Arrays.deepToString(arrays));
        }
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        int[] nums1 = { 2, 4, 3 };
        int[] nums2 = { 5, 6, 4 };

        ListNode l1 = ListNode.genLinkedListWithoutHeadNode(nums1);
        ListNode l2 = ListNode.genLinkedListWithoutHeadNode(nums2);
        ListNode result = new Solution().addTwoNumbers(l1, l2);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }

    public static void testCase2() {
        int[] nums1 = { 0 };
        int[] nums2 = { 0 };

        ListNode l1 = ListNode.genLinkedListWithoutHeadNode(nums1);
        ListNode l2 = ListNode.genLinkedListWithoutHeadNode(nums2);
        ListNode result = new Solution().addTwoNumbers(l1, l2);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }

    public static void testCase3() {
        int[] nums1 = { 9, 9, 9, 9, 9, 9, 9 };
        int[] nums2 = { 9, 9, 9, 9 };

        ListNode l1 = ListNode.genLinkedListWithoutHeadNode(nums1);
        ListNode l2 = ListNode.genLinkedListWithoutHeadNode(nums2);
        ListNode result = new Solution().addTwoNumbers(l1, l2);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }
}