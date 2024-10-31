package core.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeAndSortIntegerArrays {

    public static int[] mergeAndSortArrays() {
        int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 4, 6, 8};

        return mergeAndSortArrays(arr1, arr2);
    }

    public static int[] mergeAndSortArrays(int[] arr1, int[] arr2) {
        return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
            .sorted()
            .toArray();
    }
}
