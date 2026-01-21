package core.algorithms.leetcode.dp;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a
 * total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * <p>
 * Input: coins = [1,2,5], amount = 11 <br>
 * Output: 3 <br>
 * Explanation: 11 = 5 + 5 + 1
 */
public class CoinChange_322 {

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // массив сум, где результат функции это количество монет, необходимое для получения
        // этой суммы
        Arrays.fill(dp, amount + 1); // amount + 1 - псевдобесконечность, лучше использовать его вместо Integer.MAX_VALUE чтобы
        // избежать переполнения, т.к. MAX_VALUE + 1 даст отрицательное число
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) { // идем слева направо, таким образом мы можем брать одну и ту же монету много раз
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // количество монет, необходимое для получения суммы i - coin
                    // плюс текущая монета, т.е. + 1
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 3, 4}, 6)); // 2
        System.out.println(coinChange(new int[]{1, 2, 5}, 11)); // 3
    }
}
