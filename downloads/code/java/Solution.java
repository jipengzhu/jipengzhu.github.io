public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        // 本质上是求多个上升区间的区间差之和（股票的本质是低买高卖）

        int maxProfit = 0;
        int minPrice = prices[0]; // 记录上升区间的最小值
        int maxPrice = minPrice; // 记录上升区间的最大值

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= maxPrice) {
                // 还是上升趋势，继续扩大区间
                maxPrice = prices[i];
            } else {
                // 出现拐点了
                maxProfit = maxProfit + (maxPrice - minPrice);

                // 重设窗口位置
                minPrice = prices[i];
                maxPrice = minPrice;
            }
        }

        maxProfit = maxProfit + (maxPrice - minPrice);

        return maxProfit;
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        int[] nums = { 7, 1, 5, 3, 6, 4 };

        int result = new Solution().maxProfit(nums);
        System.out.println(result);
    }

    public static void testCase2() {
        int[] nums = { 1, 2, 3, 4, 5 };

        int result = new Solution().maxProfit(nums);
        System.out.println(result);
    }

    public static void testCase3() {
        int[] nums = { 7, 6, 4, 3, 1 };

        int result = new Solution().maxProfit(nums);
        System.out.println(result);
    }
}