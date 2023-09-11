public class Solution {
    public int hIndex(int[] citations) {
        java.util.Arrays.sort(citations);

        int h = 0;
        int i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }

        return h;
    }

    public static void main(String[] args) {
        testHIndex1();
        testHIndex2();
    }

    public static void testHIndex1() {
        int[] nums = { 3, 0, 6, 1, 5 };

        int h = new Solution().hIndex(nums);
        System.out.println(h);
    }

    public static void testHIndex2() {
        int[] nums = { 1, 3, 1 };

        int h = new Solution().hIndex(nums);
        System.out.println(h);
    }
}