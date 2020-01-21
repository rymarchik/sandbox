package sort;

import java.util.Arrays;

public class SortUtils {

    //пузырьком
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

    //вставками
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

    //выбором
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

    //слиянием
    public static int[] merge(int[] array1) {
        int[] buffer1 = Arrays.copyOf(array1, array1.length);
        int[] buffer2 = new int[array1.length];
        return mergeInner(buffer1, buffer2, 0, array1.length);
    }

    /**
     *
     * @param buffer1 Массив для сортировки.
     * @param buffer2 Буфер. Размер должен быть равен размеру buffer1.
     * @param startIndex Начальный индекс в buffer1 для сортировки.
     * @param endIndex Конечный индекс в buffer1 для сортировки.
     */
    public static int[] mergeInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        // уже отсортирован.
        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeInner(buffer1, buffer2, middle, endIndex);

        // Слияние
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }
}