import java.util.List;

public class Test {

    public static void main(String[] args) {
        jsArrayLiteral2JavaArrayLiteral();
    }

    private static void jsArrayLiteral2JavaArrayLiteral() {
        String s = "[[1,2],[2,3]]";
        s = s.replace('[', '{');
        s = s.replace(']', '}');
        s = s.replace("{{", "{\n{");
        s = s.replace("},", "},\n");
        s = s.replace("}}", "}\n}");
        System.out.println(s);
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

        public static void printArray(Integer[] array) {
            System.out.println(java.util.Arrays.toString(array));
        }

        public static void printArray(Integer[] array, int len) {
            System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
        }

        public static void printArrays(Integer[][] arrays) {
            System.out.println(java.util.Arrays.deepToString(arrays));
        }

        public static void printArray(String[] array) {
            System.out.println(java.util.Arrays.toString(array));
        }

        public static void printArray(String[] array, int len) {
            System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
        }

        public static void printArrays(String[][] arrays) {
            System.out.println(java.util.Arrays.deepToString(arrays));
        }
    }

    public static class ListUtils {
        public static int[] listToArray(List<Integer> list) {
            if (list == null || list.isEmpty()) {
                return new int[0];
            }

            int[] array = new int[list.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

            return array;
        }

        public static int[][] listsToArrays(List<List<Integer>> lists) {
            if (lists == null || lists.isEmpty()) {
                return new int[0][0];
            }

            int row = lists.size();
            int col = lists.get(0).size();
            int[][] arrays = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    arrays[i][j] = lists.get(i).get(j);
                }
            }

            return arrays;
        }
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

    int result;
    int expect;

    // String sp1 = "^\s{1,}print.*|System.out.println\((?!(java.util|expect|result))";
    // String sp2 = "ArrayUtils.print.*\((?!(expect|result))|System.out.println\((?!(java.util|expect|result))";
    // String sp3 = "public.*To[al].*\(";
    // String sc = "ArrayUtils.print|System.out.println\((?!java.util)";

    // String cp1 = "new Solution\(\).*\)(?!;)";

    // String tp1 = "test(?!Case).*\d\("
    // String tc = "testCase\d\("
}
