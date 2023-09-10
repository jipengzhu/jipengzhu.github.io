public class Solution {
    public boolean canJump(int[] nums) {
        return canJump(nums, 0, nums.length - 1);
    }

    public boolean canJump(int[] nums, int i, int j) {
        int x = i + nums[i];
        if (x >= j) {
            return true;
        }

        int k = i;
        while (++k <= x) {
            if (canJump(nums, k, j)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        testCanJump1();
        testCanJump2();
    }

    public static void testCanJump1() {
        int[] nums = { 2, 3, 1, 1, 4 };

        boolean canJump = new Solution().canJump(nums);
        System.out.println(canJump);
    }

    public static void testCanJump2() {
        int[] nums = { 3, 2, 1, 0, 4 };

        boolean canJump = new Solution().canJump(nums);
        System.out.println(canJump);
    }
}