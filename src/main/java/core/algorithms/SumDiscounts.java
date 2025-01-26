package core.algorithms;

import java.util.stream.IntStream;

/**
 * Given an array of items prices. Each price has discount price = previous item price. First item doesn't have any discount.
 * What is the final price customer has to pay after discount applied
 */
public class SumDiscounts {

    public static void discount(int[] prices) {
        int sumDiscounts = IntStream.range(1, prices.length)
            .map(i -> prices[i] - prices[i - 1])
            .reduce(prices[0], Integer::sum);
        System.out.println(sumDiscounts);
    }

    public static void discountInParallel(int[] prices) {
        int sumDiscounts = prices[0] + IntStream.range(1, prices.length)
            .parallel()
            .map(i -> prices[i] - prices[i - 1])
            .sum();
        System.out.println(sumDiscounts);
    }

    public static void case1() {
        int[] array = {100, 200, 300, 400};
        discount(array);
    }

    public static void case2() {
        int[] array = {100, 200, 300, 400};
        discountInParallel(array);
    }
}
