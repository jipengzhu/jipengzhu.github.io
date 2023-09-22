import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        int max = 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (Integer num : set) {
            if (!set.contains(num - 1)) {
                int end = num + 1;
                while (set.contains(end)) {
                    end++;
                }

                int tmp = end - num;
                if (tmp > max) {
                    max = tmp;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        testLongestConsecutive1();
        testLongestConsecutive2();
    }

    public static void printArray(int[] array) {
        System.out.println(java.util.Arrays.toString(array));
    }

    public static void printArray(int[] array, int len) {
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
    }

    public static void printArrays(int[][] arrays) {
        System.out.println(java.util.Arrays.deepToString(arrays));
    }

    public static void testLongestConsecutive1() {
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        System.out.println(new Solution().longestConsecutive(nums));
    }

    public static void testLongestConsecutive2() {
        int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        System.out.println(new Solution().longestConsecutive(nums));
    }
}