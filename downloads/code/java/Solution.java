class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                return new int[] { i + 1, j + 1 };
            }
        }

        return new int[] { -1, -1 };
    }

    public static void printArray(int[] array) {
        System.out.println(java.util.Arrays.toString(array));
    }

    public static void printArray(int[] array, int len) {
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
    }

    public static void main(String[] args) {
        testTwoSum1();
        testTwoSum2();
        testTwoSum3();
    }

    public static void testTwoSum1() {
        int[] numbers = { 2, 7, 11, 15 };
        int target = 9;
        printArray(new Solution().twoSum(numbers, target));
    }

    public static void testTwoSum2() {
        int[] numbers = { 2, 3, 4 };
        int target = 6;
        printArray(new Solution().twoSum(numbers, target));
    }

    public static void testTwoSum3() {
        int[] numbers = { -1, 0 };
        int target = -1;
        printArray(new Solution().twoSum(numbers, target));
    }
}