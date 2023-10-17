public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head;
        ListNode m = null;
        while (p != null) {
            ListNode r = p;
            for (int i = 0; i < k; i++) {
                if (r == null) {
                    ListNode l = p;
                    if (m != null) {
                        m.next = l;
                    } else {
                        head = l;
                    }

                    return head;
                }

                r = r.next;
            }

            ListNode l = null;
            ListNode t = null;
            for (int i = 0; i < k; i++) {
                if (p == null) {
                    break;
                }

                if (i == 0) {
                    t = p;
                }

                // 先保存旧链表的剩余部分
                ListNode q = p.next;

                // 在新链表的头部插入
                p.next = l;
                l = p;

                // 继续处理旧链表的剩余部分
                p = q;
            }

            if (m != null) {
                m.next = l;
            } else {
                head = l;
            }

            m = t;
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
    }

    public static void testCase1() {
        int[] nums = { 1, 2, 3, 4, 5 };
        int k = 2;

        ListNode link = ListNode.genLinkedListWithoutHeadNode(nums);
        ListNode result = new Solution().reverseKGroup(link, k);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }

    public static void testCase2() {
        int[] nums = { 1, 2, 3, 4, 5 };
        int k = 3;

        ListNode link = ListNode.genLinkedListWithoutHeadNode(nums);
        ListNode result = new Solution().reverseKGroup(link, k);
        ArrayUtils.printArray(ListNode.toArrayWithoutHeadNode(result));
    }
}