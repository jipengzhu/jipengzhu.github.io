public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        int c = 0;
        int i = 0;
        while (c < nums.length) {

            int j = i;
            int x = nums[i]; // 保存当前元素
            int y = -1; // 保存下一个元素

            while (true) {
                // 计算下一个元素的位置
                j = (j + k) % nums.length;

                // 保存下一个元素
                y = nums[j];

                // 用当前元素占领下一个元素的位置
                nums[j] = x;

                // 处理完的个数加1
                c++;

                // 个数达标了，全部处理完毕，结束
                if (c == nums.length) {
                    break;
                }

                // 回到起点了，不能重复处理，结束
                if (j == i) {
                    break;
                }

                // 更新当前元素
                x = y;
            }

            i = i + 1;
        }
    }

    public static void main(String[] args) {
        testRotate1();
        testRotate2();
    }

    public static void printArray(int[] array) {
        System.out.println(java.util.Arrays.toString(array));
    }

    public static void printArray(int[] array, int len) {
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
    }

    public static void testRotate1() {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        int k = 3;

        new Solution().rotate(nums, k);
        printArray(nums);
    }

    public static void testRotate2() {
        int[] nums = { -1, -100, 3, 99 };
        int k = 2;

        new Solution().rotate(nums, k);
        printArray(nums);
    }
}