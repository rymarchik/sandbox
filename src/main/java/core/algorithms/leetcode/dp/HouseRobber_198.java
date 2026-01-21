package core.algorithms.leetcode.dp;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Input: nums = [2,7,9,3,1] <br>
 * Output: 12 <br>
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 */
public class HouseRobber_198 {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1];
    }

    // Оптимизация по памяти - O(1), т.к. не создается массив
    public static int robOptimal(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int prev2 = 0; // аналог dp[i-2]
        int prev1 = 0; // аналог dp[i-1]
        int current = 0;

        for (int num : nums) {
            current = Math.max(num + prev2, prev1);
            prev2 = prev1; // заготовка для след итерации в цикле
            prev1 = current; // заготовка для след итерации в цикле
        }
        return current;
    }


    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1})); // 4
        System.out.println(rob(new int[]{100, 1, 1, 100})); // 200
        System.out.println(robOptimal(new int[]{2, 7, 9, 3, 1})); // 12
    }
}
