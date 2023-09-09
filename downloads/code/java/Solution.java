import java.util.Arrays;

public class Solution {
    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void printArray(int[] array, int len) {
        System.out.println(Arrays.toString(Arrays.copyOf(array, len)));
    }

    public static void main(String[] args) {
        testMerge1();
        testMerge2();
        testMerge3();

        // testRemoveElement1();
        // testRemoveElement2();

        // testRemoveDuplicates1();
        // testRemoveDuplicates2();

        // testRemoveKDuplicates1();
        // testRemoveKDuplicates2();

        // testMaxProfit1();
        // testMaxProfit2();
    }

    public static void testMerge1() {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int m = 3;

        int[] nums2 = { 2, 5, 6 };
        int n = 3;

        new Solution().merge(nums1, m, nums2, n);
        printArray(nums1);
    }

    public static void testMerge2() {
        int[] nums1 = { 1 };
        int m = 1;

        int[] nums2 = {};
        int n = 0;

        new Solution().merge(nums1, m, nums2, n);
        printArray(nums1);
    }

    public static void testMerge3() {
        int[] nums1 = { 0 };
        int m = 0;

        int[] nums2 = { 1 };
        int n = 1;

        new Solution().merge(nums1, m, nums2, n);
        printArray(nums1);
    }

    public static void testRemoveElement1() {
        int[] nums = { 3, 2, 2, 3 };
        int val = 3;

        int len = new Solution().removeElement(nums, val);
        printArray(nums, len);
    }

    public static void testRemoveElement2() {
        int[] nums = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val = 2;

        int len = new Solution().removeElement(nums, val);
        printArray(nums, len);
    }

    public static void testRemoveDuplicates1() {
        int[] nums = { 1, 1, 2 };

        int len = new Solution().removeDuplicates(nums);
        printArray(nums, len);
    }

    public static void testRemoveDuplicates2() {
        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };

        int len = new Solution().removeDuplicates(nums);
        printArray(nums, len);
    }

    public static void testRemoveKDuplicates1() {
        int[] nums = { 1, 1, 1, 2, 2, 3 };

        int len = new Solution().removeKDuplicates(nums);
        printArray(nums, len);
    }

    public static void testRemoveKDuplicates2() {
        int[] nums = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };

        int len = new Solution().removeKDuplicates(nums);
        printArray(nums, len);
    }

    public static void testMaxProfit1() {
        int[] nums = { 7, 1, 5, 3, 6, 4 };

        int max = new Solution().maxProfit(nums);
        System.out.println(max);
    }

    public static void testMaxProfit2() {
        int[] nums = { 7, 6, 4, 3, 1 };

        int max = new Solution().maxProfit(nums);
        System.out.println(max);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            while (i >= 0 && j >= 0 && nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            }
            while (i >= 0 && j >= 0 && nums2[j] >= nums1[i]) {
                nums1[k--] = nums2[j--];
            }
        }

        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                count++;
            } else {
                if (count != 0) {
                    nums[i - count] = nums[i];
                }
            }
        }

        return nums.length - count;
    }

    public int removeDuplicates(int[] nums) {
        int count = 0;
        int prev = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev) {
                count++;
            } else {
                prev = nums[i];
                if (count != 0) {
                    nums[i - count] = nums[i];
                }
            }
        }

        return nums.length - count;
    }

    public int removeKDuplicates(int[] nums) {
        int count = 0;
        int prev1 = nums[0];
        int prev2 = nums[1];

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == prev2 && prev2 == prev1) {
                count++;
            } else {
                prev1 = prev2;
                prev2 = nums[i];
                if (count != 0) {
                    nums[i - count] = nums[i];
                }
            }
        }

        return nums.length - count;
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int maxPrice = prices[i + 1];
            for (int j = i + 2; j < prices.length; j++) {
                if (prices[j] > maxPrice) {
                    maxPrice = prices[j];
                }
            }

            if (maxPrice - prices[i] > maxProfit) {
                maxProfit = maxPrice - prices[i];
            }
        }

        return maxProfit;
    }
}
