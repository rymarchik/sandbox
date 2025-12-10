package core.exercise.sort;

import java.util.Arrays;

public class SelectionSort {

    public static void selection(int[] arr) {
        for (int min = 0; min < arr.length - 1; min++) {
            int least = min;
            for (int j = min + 1; j < arr.length; j++) {
                if (arr[j] < arr[least]) {
                    least = j;
                }
            }
            if (least != min) {
                int tmp = arr[min];
                arr[min] = arr[least];
                arr[least] = tmp;
            }
        }
    }

    public static void testSelectionSort() {
        int[] arr = {3, 6, 1, 5, 2, 8};
        selection(arr);
        System.out.println(Arrays.toString(arr));
    }
}
