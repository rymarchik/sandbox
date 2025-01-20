package core.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void bubble(int[] arr) {
        int n = arr.length;

        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            n--;
        }
    }

    public static void testBubbleSort() {
        int[] arr = {3, 6, 1, 5, 2, 8};
        bubble(arr);
        System.out.println(Arrays.toString(arr));
    }
}
