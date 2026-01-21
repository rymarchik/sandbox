package core.algorithms.leetcode.dp;

/**
 * You are given an integer array nums and an integer target.
 *<br>
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then
 concatenate all the integers.
 */
public class TargetSum_494 {

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        int sumPos = (sum + target) / 2;
        int[] dp = new int[sumPos + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int i = sumPos; i - num >= 0; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[sumPos];
    }

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)); // 5
        System.out.println(findTargetSumWays(new int[]{1}, 1)); // 5
    }
}
