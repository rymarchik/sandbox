package core.algorithms;

import java.util.Arrays;

/**
 * Given an array a, your task is to output an array b of the same length by applying the following transformation:
 * – For each i from 0 to a.length - 1 inclusive, b[i] = a[i - 1] + a[i] + a[i + 1]
 * – If an element in the sum a[i - 1] + a[i] + a[i + 1] does not exist, use 0 in its place
 * – For instance, b[0] = 0 + a[0] + a[1]
 */
public class ArrayManipulation {

    private static int[] process(int[] a) {
        int n = a.length;
        int[] r = new int[n];

        for (int i = 0; i < n; i++) {
            r[i] = a[i];
            if (i > 0) {
                r[i] += a[i - 1];
            }
            if (i < n - 1) {
                r[i] += a[i + 1];
            }
        }
        return r;
    }

    public static void testCase1() {
        int[] array = new int[]{4, 0, 1, -2, 3};
        System.out.println(Arrays.toString(process(array)));
    }

    public static void testCase2() {
        int[] array = new int[]{1};
        System.out.println(Arrays.toString(process(array)));
    }

    public static void testCase3() {
        int[] array = new int[]{};
        System.out.println(Arrays.toString(process(array)));
    }
}
