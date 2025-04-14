package core.algorithms.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false
 * otherwise.
 */
public class UniqueNumberOfOccurrences_1207 {

    private static boolean uniqueOccurrences(int[] arr) {
        Collection<Long> occurrences = Arrays.stream(arr)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .values();

        return occurrences.size() == new HashSet<>(occurrences).size();
    }

    public static void case1() {
        int[] arr = {1, 2, 2, 1, 1, 3};
        System.out.println(uniqueOccurrences(arr)); // true
    }

    public static void case2() {
        int[] arr = {1, 2};
        System.out.println(uniqueOccurrences(arr)); // false
    }

    public static void case3() {
        int[] arr = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        System.out.println(uniqueOccurrences(arr)); // true
    }
}
