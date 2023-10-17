public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p = list1;
        ListNode q = list2;
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode r = head;

        while (p != null && q != null) {
            while (p != null && q != null && p.val <= q.val) {
                r.next = new ListNode(p.val);
                r = r.next;

                p = p.next;
            }

            while (p != null && q != null && q.val <= p.val) {
                r.next = new ListNode(q.val);
                r = r.next;

                q = q.next;
            }
        }

        while (p != null) {
            r.next = new ListNode(p.val);
            r = r.next;

            p = p.next;
        }

        while (q != null) {
            r.next = new ListNode(q.val);
            r = r.next;

            q = q.next;
        }

        return head.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public static ListNode genLinkedList(int[] nums, boolean withHead, int loopPos) {
            if (nums.length == 0) {
                return null;
            }

            int val = withHead ? Integer.MIN_VALUE : nums[0];
            ListNode head = new ListNode(val);

            ListNode loop = withHead ? null : loopPos == 0 ? head : null;

            int b = withHead ? 0 : 1;
            ListNode p = head;
            for (int i = b; i < nums.length; i++) {
                p.next = new ListNode(nums[i]);
                p = p.next;

                if (loopPos == i) {
                    loop = p;
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
        int[] nums1 = { 1, 2, 4 };
        int[] nums2 = { 1, 3, 4 };

        ListNode l1 = ListNode.genLinkedListWithoutHeadNode(nums1);
        ListNode l2 = ListNode.genLinkedListWithoutHeadNode(nums2);
        ListNode result = new Solution().mergeTwoLists(l1, l2);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }

    public static void testCase2() {
        int[] nums1 = {};
        int[] nums2 = {};

        ListNode l1 = ListNode.genLinkedListWithoutHeadNode(nums1);
        ListNode l2 = ListNode.genLinkedListWithoutHeadNode(nums2);
        ListNode result = new Solution().mergeTwoLists(l1, l2);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }

    public static void testCase3() {
        int[] nums1 = {};
        int[] nums2 = { 0 };

        ListNode l1 = ListNode.genLinkedListWithoutHeadNode(nums1);
        ListNode l2 = ListNode.genLinkedListWithoutHeadNode(nums2);
        ListNode result = new Solution().mergeTwoLists(l1, l2);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }
}