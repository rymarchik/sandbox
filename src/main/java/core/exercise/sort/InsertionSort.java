package core.exercise.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int k = i;
            while (k > 0 && arr[k] < arr[k - 1]) {
                int temp = arr[k];
                arr[k] = arr[k - 1];
                arr[k - 1] = temp;
                k--;
            }
        }
    }

    public static void testInsertionSort() {
        int[] arr = {3, 6, 1, 5, 2, 8};
        insertion(arr);
        System.out.println(Arrays.toString(arr));
    }
}
