package core.algorithms.leetcode.dp;

import java.util.Arrays;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right
 * at any point in time.
 * <p>
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right
 * corner.
 */
public class UniquePaths_62 {

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // заполняем края единицами, т.к. робот ходит только вниз и вправо, и в край попасть может только 1 способом
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    // экономия памяти в виде использования только одномерного массива
    public static int uniquePathsOptimal(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] — это "сверху" (старое значение)
                // dp[j-1] — это "слева" (уже обновленное на этом шаге)
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2)); // 3
        System.out.println(uniquePathsOptimal(3, 7)); // 28
    }
}
