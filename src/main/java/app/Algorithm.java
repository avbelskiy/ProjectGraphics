package app;

import java.util.Arrays;

public class Algorithm {

    int number_alg;
    int[] array;

    public static void bubble_sort (int[] array) {
        boolean isSorted = false;
        int buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    isSorted = false;
                    buf = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = buf;
                }
            }
        }
    }

    public static void choose_sort (int [] array) {
        for (int min = 0; min < array.length - 1; min++) {
            int least = min;
            for (int j = min + 1; j < array.length; j++) {
                if (array[j] < array[least]) {
                    least = j;
                }
            }
            int buf = array[min];
            array[min] = array[least];
            array[least] = buf;
        }
    }

    public static void paste_sort (int [] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

    public static void shuttle_sort (int [] array) {
        int left = 0;
        int right = array.length - 1;
        do {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    array[i] ^= array[i + 1];
                    array[i + 1] ^= array[i];
                    array[i] ^= array[i + 1];
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (array[i] < array[i - 1]) {
                    array[i] ^= array[i - 1];
                    array[i - 1] ^= array[i];
                    array[i] ^= array[i - 1];
                }
            }
            left++;
        } while (left <= right);
    }

    public static void shell_sort (int [] array) {
        int gap = array.length / 2;
        while (gap >= 1) {
            for (int rightt = 0; rightt < array.length; rightt++) {
                for (int c = rightt - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        int buf = array[c];
                        array[c] = array[c + 1];
                        array[c + 1] = buf;
                    }
                }
            }
            gap = gap / 2;
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    public static void swap_sort (int [] array) {
        boolean isSwapped = true;
        int start = 0;
        int end = array.length;

        while (isSwapped) {
            isSwapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    isSwapped = true;
                }
            }
            if (isSwapped)
                break;
            isSwapped = false;
            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    isSwapped = true;
                }
            }
            start = start + 1;
        }
    }

    public static int[] bucketSort(int[] arr) {
        int i, j;
        int[] bucket = new int[arr.length+1];
        Arrays.fill(bucket, 0);

        for (i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        int k=0;
        for (i = 0; i < bucket.length; i++) {
            for (j = 0; j<bucket[i]; j++) {
                arr[k++] = i;
            }
        }
        return arr;
    }

    public static void count_sort( int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array)
        {
            if (element < min)
            {
                min = element;
            }
            if (element > max)
            {
                max = element;
            }
        }
        int[] buckets = new int[max - min + 1];
        for (int element : array)
        {
            buckets[element - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < buckets.length; i++)
        {
            for (int j = buckets[i]; j > 0; j--)
            {
                array[arrayIndex++] = i + min;
            }
        }
    }

    int[] sort_array() {
        switch (number_alg) {
            case (1) ->   //метод пузырька
                    bubble_sort(array);
            case (2) -> //метод выбора
                    choose_sort(array);
            case (3) ->   //метод вставками
                    paste_sort(array);
            case (4) ->   //метод шаттла
                    shuttle_sort(array);
            case (5) ->   //метод Шелла
                    shell_sort(array);
            case (6) ->   //метод быстрой сортировки
                    quickSort(array, 0, array.length - 1);
            case (7) ->   //метод перемешивания
                    swap_sort(array);
            case (8) ->   //метод блоков (не работает)
                    bucketSort(array);
            case (9) ->   //метод подсчета
                    count_sort(array);
            default -> {
            }
        }
        return array;
    }
}
