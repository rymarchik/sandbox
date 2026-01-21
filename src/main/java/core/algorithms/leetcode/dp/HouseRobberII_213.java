package core.algorithms.leetcode.dp;

/**
 * Та же задача, что и {@link HouseRobber_198}, только дома расположены по кругу
 */
public class HouseRobberII_213 {

    public static int robCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(
            robRange(nums, 0, nums.length - 2), // без последнего
            robRange(nums, 1, nums.length - 1)); // без первого
    }

    private static int robRange(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;
        int current = 0;

        for (int i = start; i <= end; i++) {
            current = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static void main(String[] args) {
        System.out.println(robCircular(new int[]{2, 3, 2})); // 3
        System.out.println(robCircular(new int[]{1, 2, 3, 1})); // 4
    }
}
