import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        int k = 0;
        while (k < nums.length) {

            int target = -nums[k];
            int i = k + 1;
            int j = nums.length - 1;

            while (i < j) {
                if (nums[i] + nums[j] > target) {
                    j--;
                    // 跳过重复元素
                    while (nums[j] == nums[j + 1] && i < j) {
                        j--;
                    }
                } else if (nums[i] + nums[j] < target) {
                    i++;
                    // 跳过重复元素
                    while (nums[i] == nums[i - 1] && i < j) {
                        i++;
                    }
                } else {
                    List<Integer> list = new ArrayList<Integer>();

                    list.add(nums[k]);
                    list.add(nums[i]);
                    list.add(nums[j]);

                    result.add(list);

                    i++;
                    // 跳过重复元素
                    while (nums[i] == nums[i - 1] && i < j) {
                        i++;
                    }

                    j--;
                    // 跳过重复元素
                    while (nums[j] == nums[j + 1] && i < j) {
                        j--;
                    }
                }
            }

            k++;
            // 跳过重复元素
            while (k < nums.length && nums[k] == nums[k - 1]) {
                k++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        testThreeSum1();
        testThreeSum2();
        testThreeSum3();
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

    public static int[][] listToArray(List<List<Integer>> lists) {
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

    public static void testThreeSum1() {
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        printArrays(listToArray(new Solution().threeSum(nums)));
    }

    public static void testThreeSum2() {
        int[] nums = { 0, 1, 1 };
        printArrays(listToArray(new Solution().threeSum(nums)));
    }

    public static void testThreeSum3() {
        int[] nums = { 0, 0, 0 };
        printArrays(listToArray(new Solution().threeSum(nums)));
    }
}