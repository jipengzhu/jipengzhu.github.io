import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();

        int i = 0;
        int j = i + 1;
        int e = nums.length - 1;
        while (i <= e && j <= e) {
            while (j <= e) {
                if (nums[j] == nums[j - 1] + 1) {
                    j++;
                } else {
                    if (i == j - 1) {
                        list.add(nums[i] + "");
                    } else {
                        list.add(nums[i] + "->" + nums[j - 1]);
                    }

                    i = j;
                    j = i + 1;
                }
            }
        }

        if (i <= e) {
            j = e;

            if (i == j) {
                list.add(nums[i] + "");
            } else {
                list.add(nums[i] + "->" + nums[j]);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        testSummaryRanges1();
        testSummaryRanges2();
    }

    public static void testSummaryRanges1() {
        int[] nums = { 0, 1, 2, 4, 5, 7 };
        List<String> list = new Solution().summaryRanges(nums);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();
    }

    public static void testSummaryRanges2() {
        int[] nums = { 0, 2, 3, 4, 6, 8, 9 };
        List<String> list = new Solution().summaryRanges(nums);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();
    }

}