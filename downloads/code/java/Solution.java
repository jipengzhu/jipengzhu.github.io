public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }

        genHeap(nums);

        // int len = nums.length;
        // swap(nums, 0, --len);
        // while (len > 0) {
        // shiftDown(nums, 0, len - 1);
        // swap(nums, 0, --len);
        // }

        // return nums[nums.length - k];

        int len = nums.length;
        swap(nums, 0, --len);
        while (len > nums.length - k) {
            shiftDown(nums, 0, len - 1);
            swap(nums, 0, --len);
        }

        return nums[len];
    }

    private void genHeap(int[] nums) {
        int len = nums.length;
        for (int i = (len - 2) / 2; i >= 0; i--) {
            shiftDown(nums, i, len);
        }
    }

    private void shiftDown(int[] nums, int i, int heapSize) {
        int j = 2 * i + 1;
        while (j < heapSize) {
            j = j + 1 < heapSize && nums[j + 1] > nums[j] ? j + 1 : j;

            if (nums[i] < nums[j]) {
                swap(nums, i, j);

                i = j;
                j = 2 * i + 1;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        // 注释掉，因为每次判断带来的开销并不比无效的自我交换带来的开销少
        // if (i == j) {
        // return;
        // }

        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
    }

    public static void testCase1() {
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        int k = 2;

        int result = new Solution().findKthLargest(nums, k);
        System.out.println(result);
    }

    public static void testCase2() {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k = 4;

        int result = new Solution().findKthLargest(nums, k);
        System.out.println(result);
    }
}