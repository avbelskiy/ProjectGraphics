package app;

import panels.PanelInput;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import static panels.PanelInput.array;

public class Algorithm {
    int number_alg;

    public static int[] bubble_sort (int[] array) {
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
        return array;
    }

    public static int[] choose_sort (int [] array) {
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
        return array;
    }

    public static int[] paste_sort (int [] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }

    public static int[] shuttle_sort (int [] array) {
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
        return array;
    }

    public static int[] shell_sort (int [] array) {
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
        return array;
    }

    public static int[] quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return array;

        if (low >= high)
            return null;

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
        return array;
    }

    public static int[] swap_sort (int [] array) {
        int left = 0; // левая граница
        int right = array.length - 1; // правая граница

        do
        {
            //Сдвигаем к концу массива "тяжелые элементы"
            for (int i = left; i < right; i++)
            {
                if(array[i] > array[i+1])
                {
                    array[i] ^= array[i+1];
                    array[i+1] ^= array[i];
                    array[i] ^= array[i+1];
                }
            }
            right--; // уменьшаем правую границу
            //Сдвигаем к началу массива "легкие элементы"
            for (int i = right; i > left ; i--)
            {
                if(array[i] < array[i-1])
                {
                    array[i] ^= array[i-1];
                    array[i-1] ^= array[i];
                    array[i] ^= array[i-1];
                }
            }
            left++; // увеличиваем левую границу
        } while (left <= right);
        return array;
    }

    public static int[] bucketSort(int[] arr) {
        int n = arr.length;
        if (n <= 0)
            return null;

        // 1) Create n empty buckets
        @SuppressWarnings("unchecked")
        Vector<Integer>[] buckets = new Vector[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<Integer>();
        }

        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int)idx].add(arr[i]);
        }

        // 3) Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
        return arr;
    }

    public static int[] count_sort( int[] array) {
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
        return array;
    }

    void sort_array() {
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
    }
}
