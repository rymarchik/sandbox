package core.algorithms.leetcode.dp;

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements
 * in both subsets is equal or false otherwise.
 */
public class PartitionEqualSubsetSum_416 {

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        int currentMax = 0; // Ограничиваем цикл текущей суммой

        for (int num : nums) {
            // Если число больше цели, мы никогда не соберем половину
            if (num > target) {
                return false;
            }
            // Идем от текущего максимума, чтобы не бежать по всему массиву зря
            for (int i = Math.min(target, currentMax + num); i >= num; i--) {
                if (!dp[i] && dp[i - num]) {
                    dp[i] = true;
                }
            }
            currentMax = Math.min(target, currentMax + num);
            // Ранний выход
            if (dp[target]) {
                return true;
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5})); // yes
        System.out.println(canPartition(new int[]{1, 2, 3, 5})); // no
    }
}
