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
        testCase1();
        testCase2();
    }

    public static void testCase1() {
        int[] nums = { 3, 0, 6, 1, 5 };

        int result = new Solution().hIndex(nums);
        System.out.println(result);
    }

    public static void testCase2() {
        int[] nums = { 1, 3, 1 };

        int result = new Solution().hIndex(nums);
        System.out.println(result);
    }
}