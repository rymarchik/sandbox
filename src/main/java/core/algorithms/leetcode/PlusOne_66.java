package core.algorithms.leetcode;

import java.util.Arrays;

/**
 * {2,3,4} -> {2,3,5}
 * {2,9} -> {3,0}
 * {9,9} -> {1,0,0}
 */
public class PlusOne_66 {

    private static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            digits[i]++;
            if (digits[i] < 10) {
                return digits;
            }
            digits[i] = 0;
        }
        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;
        return newNumber;
    }

    public static void case1() {
        int[] number = {2, 3, 4};
        System.out.println(Arrays.toString(plusOne(number)));
    }

    public static void case2() {
        int[] number = {2, 9};
        System.out.println(Arrays.toString(plusOne(number)));
    }

    public static void case3() {
        int[] number = {9, 9};
        System.out.println(Arrays.toString(plusOne(number)));
    }
}
